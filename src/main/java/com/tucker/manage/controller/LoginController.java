package com.tucker.manage.controller;


import com.tucker.manage.bean.User;
import com.tucker.manage.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@Autowired
	UserMapper userMapper;
	/*欢迎页*/
	@GetMapping("/")
	public String index() {
		return "index";
	}
    /*登陆页*/
	@GetMapping("/login")
	public String loginPage() {
		return "page-login";
	}

	@GetMapping("/register")
	public String registerPage(){return "register";}

	@PostMapping("/register")
	public String registerUser(User user){
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword().trim()));
		System.out.println(user);
		userMapper.insertUser(user);
		return "redirect:/user/login";
	}

}
