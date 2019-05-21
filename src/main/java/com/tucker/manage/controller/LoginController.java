package com.tucker.manage.controller;


import com.tucker.manage.bean.User;
import com.tucker.manage.mapper.UserMapper;
import com.tucker.manage.tool.SimpleResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

	@Autowired
	UserMapper userMapper;

	private RequestCache requestCache = new HttpSessionRequestCache();

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private Logger logger = LoggerFactory.getLogger(getClass());
	/*欢迎页*/

	@GetMapping("/")
	@ApiOperation(value = "主页接口")
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	@ApiOperation(value = "登录页接口")
	public String toLoginPage(){
		return "page-login";
	}


    /*登陆页*/
	/*@RequestMapping(value = "/logins")*/
	@ResponseBody
	public SimpleResponse requireLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(request,response);
		if(savedRequest!=null){
			String targetUrl=savedRequest.getRedirectUrl();
			logger.info("引发跳转的请求是"+targetUrl);

			if(StringUtils.endsWith(targetUrl,".html")){
				String redirectUrl = "/page-login.html";
				redirectStrategy.sendRedirect(request,response,redirectUrl);
			}
		}
		return new SimpleResponse("访问的服务需要身份认证，请引导用户到用户到登录页");
	}

	@GetMapping("/register")
	@ApiOperation(value = "注册页接口")
	public String registerPage(){return "page-register";}

	@GetMapping("/login/error")
	@ApiOperation(value = "登陆错误页接口")
	public String loginerrorPage(){return "page-loginerror";}

	@PostMapping("/register")
	@ApiOperation(value = "注册接口",notes = "访问此接口可以注册用户")
	@ApiParam(name = "user" , value = "用户对象" ,required = true )
	public String registerUser(User user){
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword().trim()));
		System.out.println(user);
		userMapper.insertUser(user);
		return "redirect:/login";
	}

}
