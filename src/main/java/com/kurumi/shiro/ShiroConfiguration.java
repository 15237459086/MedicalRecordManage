package com.kurumi.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration {
	  private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();  
	  
	    @Bean(name = "ShiroRealmImpl")  
	    public MyShiroRealm getShiroRealm() {  
	        return new MyShiroRealm();  
	    }  
	  
//	    @Bean(name = "shiroEhcacheManager")  
//	    public EhCacheManager getEhCacheManager() {  
//	        EhCacheManager em = new EhCacheManager();  
//	        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");  
//	        return em;  
//	    }  
	  
	    @Bean(name = "lifecycleBeanPostProcessor")  
	    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {  
	        return new LifecycleBeanPostProcessor();  
	    }  
	  
	    //去掉下面这段代码解决AOP二次代理问题
//	    @Bean  
//	    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {  
//	        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();  
//	        daap.setProxyTargetClass(true);  
//	        return daap;  
//	    }  
	    

	    @Bean(name = "securityManager")  
	    public DefaultWebSecurityManager getDefaultWebSecurityManager() {  
	        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();  
	        dwsm.setRealm(getShiroRealm());  
	        //dwsm.setCacheManager(getEhCacheManager());  
	        return dwsm;  
	    }  
	  
	    //shiro注解
	    @Bean  
	    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {  
	        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();  
	        aasa.setSecurityManager(getDefaultWebSecurityManager());  
	        return new AuthorizationAttributeSourceAdvisor();  
	    }  
	  
	    
	    

	    @Bean(name = "shiroFilter")  
	    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {  
	        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();  
	        shiroFilterFactoryBean  
	                .setSecurityManager(getDefaultWebSecurityManager());  
	       //没有登录跳到登录页面
//	        shiroFilterFactoryBean.setLoginUrl("/login");  
	        
	    	// 未授权界面;
	        //shiroFilterFactoryBean.setUnauthorizedUrl("/user/error");
	        //不拦截请求
	        /*filterChainDefinitionMap.put("/login", "anon");
	        filterChainDefinitionMap.put("/login_check", "anon");
	        filterChainDefinitionMap.put("/captcha/**", "anon");
	        filterChainDefinitionMap.put("/assets/**", "anon");
	        filterChainDefinitionMap.put("/**", "authc");*/
	        filterChainDefinitionMap.put("/**", "anon");
	        shiroFilterFactoryBean  
	                .setFilterChainDefinitionMap(filterChainDefinitionMap);  
	        return shiroFilterFactoryBean;  
	    }  
	    
	    
	    
}
