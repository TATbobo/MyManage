package com.tucker.manage.image;

import lombok.AllArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@AllArgsConstructor
public class ImageCode {

        //图形属性
        private BufferedImage image;
        //验证码
        private String code;
        //时间参数
        private LocalDateTime expireTime;

        public ImageCode(BufferedImage image,String code,int expireIn) {

            this.image = image;

            this.code = code;
            //设置验证码过期时间(30秒)
            this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
        }
        public boolean isExpired(){
            //判断验证码是否过期
            return LocalDateTime.now().isAfter(expireTime);
        }
}

