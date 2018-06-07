package com.kurumi.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kurumi.config.MyConfig;
import com.kurumi.pojo.RespondResult;
import com.kurumi.util.MD5Util;

@Controller
public class HomeController {

	@Autowired
	private MyConfig myConfig;
	
	@GetMapping("/")
	public String home(){
		
		
		return "pigeonholed_index.default";
	}
	
	/*@RequiresRoles("admin")*/
	@GetMapping("/index")
	public String index(){
		return "index.default";
	}
	
	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	
	@GetMapping("/login_out")
	public String loginOut(){
		Subject subject=SecurityUtils.getSubject();
		Session session = subject.getSession();
		session.removeAttribute("roles");
		session.removeAttribute("authoritys");
		session.removeAttribute("currentUser");
		SecurityUtils.getSubject().logout();
		
		return "redirect:/login"; 
	}
	
	
	@PostMapping("/login_check")
	@ResponseBody
	public RespondResult loginCheck(String loginName,String password,String verificationCode,HttpServletRequest request){
		RespondResult respondResult = null;
		try{
			
			String sessionVerificationCode = (String) request.getSession().getAttribute("verificationCode");
			if(verificationCode !=null){
				boolean result = verificationCode.equalsIgnoreCase(sessionVerificationCode);
				if(!result){
					respondResult = new RespondResult(true, RespondResult.errorCode, "验证码输入错误", "验证码输入错误");
				}else{
					Subject subject=SecurityUtils.getSubject();
					Session session = subject.getSession();
					session.setAttribute("remoteLoginUrl", myConfig.getRemoteLoginUrl());
					session.setAttribute("currentVersion", myConfig.getCurrentVersion());
					UsernamePasswordToken token = new UsernamePasswordToken(loginName, MD5Util.getMD5(password));
					SecurityUtils.getSubject().login(token);
					respondResult = new RespondResult(true, RespondResult.successCode, "登陆成功", "");
				}
				
			}else{
				respondResult = new RespondResult(true, RespondResult.errorCode, "验证码不能为空", "验证码不能为空");
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			if(e instanceof UnknownAccountException){
				respondResult = new RespondResult(true, RespondResult.errorCode, "用户名或密码错误", "用户名或密码错误");
			}else if(e instanceof AuthenticationException){
				respondResult = new RespondResult(true, RespondResult.errorCode, "登陆失败！远程认证连接失败", "登陆失败！远程认证连接失败");
			}else{
				respondResult = new RespondResult(false, RespondResult.errorCode, e.getMessage(),e.getMessage());
			}
			
			
		}
		
		return respondResult;
		
		
	}
}
