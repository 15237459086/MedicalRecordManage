package com.kurumi.controller;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kurumi.util.Captcha;

@Controller
@RequestMapping("/captcha")
public class CaptchaController {

	@GetMapping("/verification_code")
	public void verificationCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setHeader("Expires", "-1");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setHeader("Pragma", "-1");  
        Captcha captcha = Captcha.Instance();  
        // 将验证码输入到session中，用来验证  
        String code = captcha.getString();  
        request.getSession().setAttribute("verificationCode", code);  
        // 输出打web页面  
        ImageIO.write(captcha.getImage(), "jpg", response.getOutputStream());  
	}
}
