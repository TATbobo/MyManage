package com.tucker.manage.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lemon
 * @date 2018/4/5 下午3:08
 */
@ConfigurationProperties(prefix = "com.browser")
@Data
public class BrowserProperties {

    private String loginPage = "/page-login.html";

    private LoginType loginType = LoginType.REDIRECT;
}
