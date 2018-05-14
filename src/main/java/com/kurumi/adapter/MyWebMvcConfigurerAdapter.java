package com.kurumi.adapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.kurumi.interceptor.SecurityHandlerInterceptor;

/**
 * 全局拦截器
 * @author lyh
 *
 */
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new SecurityHandlerInterceptor()).
		excludePathPatterns("/login*").
		excludePathPatterns("/captcha/*").
		/*excludePathPatterns("/user/*").*/
		addPathPatterns("/**");
		super.addInterceptors(registry);
	}

	
}
