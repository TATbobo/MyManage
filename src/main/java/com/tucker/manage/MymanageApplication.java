package com.tucker.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@MapperScan({"com.tucker.manage.mapper","com.tucker.manage.dao"})
@SpringBootApplication
public class MymanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MymanageApplication.class, args);
    }

}
