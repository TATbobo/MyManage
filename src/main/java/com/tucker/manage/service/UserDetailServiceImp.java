package com.tucker.manage.service;


import com.tucker.manage.bean.User;
import com.tucker.manage.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("UserDetailService1")
public class UserDetailServiceImp implements UserDetailsService {


    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        User user= (User) userMapper.getUserByName(name);
        System.out.println(user);
        if(user==null){
            throw new UsernameNotFoundException(name);
        }
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),authorities);
    }
}
