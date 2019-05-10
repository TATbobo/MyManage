package com.tucker.manage.properties;

import lombok.Data;

@Data
public class ImageProperties {

    /**
     * 验证码宽度
     */
    private int width = 67;
    /**
     * 验证码高度
     */
    private int height = 23;
    /**
     * 验证码长度
     */
    private int length = 4;
    /**
     * 验证码过期时间
     */
    private int expireIn = 60;

    /**
     * 需要验证码的url字符串，用英文逗号隔开
     */
    private String url;

}
