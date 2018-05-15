package com.kurumi.shiro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.alibaba.fastjson.JSONObject;
import com.kurumi.util.HttpClientUtil;

public class MyShiroRealm extends AuthorizingRealm {

	
	private static Set<String> roleSet = null;
	
	private static Set<String> permissionSet = null;
	
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
	 	/*User user = (User)SecurityUtils.getSubject().getPrincipal();*/
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        Subject subject=SecurityUtils.getSubject();
		Session session = subject.getSession();
		
		//获取用户角色
        Set<String> roleSet = new HashSet<String>();
        if(MyShiroRealm.roleSet !=null){
        	roleSet = MyShiroRealm.roleSet;
		}else{
			List<Map<String, Object>> roles =(List<Map<String, Object>>) session.getAttribute("roles");
			String currentVersion =  (String)session.getAttribute("currentVersion");
			roleSet.add(currentVersion);
			if(roles != null){
	        	for (Map<String, Object> role : roles) {
	        		roleSet.add((String)role.get("role_code"));
				}
			}
		}
        //获取用户权限
        Set<String> permissionSet = new HashSet<String>();
        if(MyShiroRealm.permissionSet != null){
        	permissionSet = MyShiroRealm.permissionSet;
        }else{
        	List<Map<String, Object>> authoritys =(List<Map<String, Object>>) session.getAttribute("authoritys");
        	if(authoritys != null){
            	for (Map<String, Object> authority : authoritys) {
            		permissionSet.add((String)authority.get("authority_code"));
    			}
    		}
        }
        
		/*if(currentRole != null){
			roleSet.add("admin");
			roleSet.add(currentRole);
	        
	        permissionSet.add("admin");
	        permissionSet.add(currentRole);
	        
		}*/
        
		info.setRoles(roleSet);

		info.setStringPermissions(permissionSet);
       
        return info;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String loginName = token.getUsername();
        String password = String.valueOf(token.getPassword());
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("loginName", loginName);
        param.put("password", password);
        Subject subject=SecurityUtils.getSubject();
		Session session = subject.getSession();
        String url = (String)session.getAttribute("remoteLoginUrl");
        session.removeAttribute("remoteLoginUrl");
        String respondJson  = HttpClientUtil.doPostJson(url, param);
        
        JSONObject jsonObject = JSONObject.parseObject(respondJson);
	    if((Boolean)jsonObject.get("success")) {	
	    	String stateCode = jsonObject.getString("stateCode");
	    	if("1".equalsIgnoreCase(stateCode)){
				Map<String, Object>  datas = (Map<String, Object>)jsonObject.getObject("data", Map.class);
	    		Map<String, Object> currentUser = (Map<String, Object>)datas.get("currentUser");
	    		session.setAttribute("currentUser", currentUser);
	    		
	    		List<Map<String, Object>> roles = (List<Map<String, Object>>)datas.get("roles");
	    		session.setAttribute("roles", roles);
	    		MyShiroRealm.roleSet = new HashSet<String>();
	    		String currentVersion =  (String)session.getAttribute("currentVersion");
				roleSet.add(currentVersion);
	    		for (Map<String, Object> role : roles) {
	        		roleSet.add((String)role.get("role_code"));
				}
	    		List<Map<String, Object>> authoritys = (List<Map<String, Object>>)datas.get("authoritys");
	    		session.setAttribute("authoritys", authoritys);
	    		MyShiroRealm.permissionSet = new HashSet<String>();
	    		for (Map<String, Object> authority : authoritys) {
	        		permissionSet.add((String)authority.get("authority_code"));
				}
	    		AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(loginName, password, getName());
				return authcInfo;
	    	}else{
	    		return null;
	    	}
	    	
			
	    } else {
	    	return null;
	    }
		
	}

}
