package com.tucker.manage.properties;

import com.tucker.manage.filter.ValidateCodeFilter;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "com.tucker.manage")
public class SecurityProperties {

    private BrowserProperties browser=new BrowserProperties();

    private ValidateCodeProperties code=new ValidateCodeProperties();

}
