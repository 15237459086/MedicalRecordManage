package com.kurumi.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 安全认证处理
 * @author lyh
 *
 */
public class SecurityHandlerInterceptor implements HandlerInterceptor {

	
	//在处理器适配器执行前调用 为各种处理器适配 通俗的讲意思就是说在执行controller的方法之前执行
	//返回true或者是false，true代表的是执行后面的逻辑，即执行后面的处理器或者拦截器
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		if(response.getStatus() == HttpStatus.NOT_FOUND.value()){
			return true;
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> currentUser = (Map<String, Object>) request.getSession().getAttribute("currentUser");
		
		if (null != currentUser) {
			return true;
		}
		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}

	//在执行完适配的调用方法后，生成视图之前执行，官方是这样解析的：
	// but before the DispatcherServlet renders the view.
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	//在所有的操作以后执行
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
