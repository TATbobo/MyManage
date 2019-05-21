package com.tucker.manage.filter;

import com.tucker.manage.controller.ValidateCodeController;
import com.tucker.manage.handler.MyAuthenticationSuccessHandler;
import com.tucker.manage.image.ImageCode;
import com.tucker.manage.properties.SecurityProperties;
import com.tucker.manage.properties.ValidateCodeException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import org.apache.commons.lang3.StringUtils;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{
    private static final String SUBMIT_FORM_DATA_PATH = "/";

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private SessionAuthenticationStrategy sessionAuthenticationStrategy;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(), ",");
        urls.addAll(Arrays.asList(configUrls));
        // 登录的链接是必须要进行验证码验证的
        urls.add(SUBMIT_FORM_DATA_PATH);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url : urls) {
            // 如果实际访问的URL可以与用户在YML配置文件中配置的相同，那么就进行验证码校验
            if (antPathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }
        if (action) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 验证码校验逻辑
     *
     * @param request 请求
     * @throws ServletRequestBindingException 请求异常
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        logger.info(request.getParameter("Username"));
        logger.info(request.getParameter("Password"));
        // 从session中获取图片验证码
        ImageCode imageCodeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
        // 从请求中获取用户填写的验证码
        String imageCodeInRequest = (String) sessionStrategy.getAttribute(request, MyAuthenticationSuccessHandler.code);
        if (StringUtils.isBlank(imageCodeInRequest)) {
            System.out.println("验证码为空");
            throw new ValidateCodeException("验证码不能为空");
        }
        if (null == imageCodeInSession) {
            System.out.println("验证码不存在");
            throw new ValidateCodeException("验证码不存在");
        }
        if (imageCodeInSession.isExpired()) {
            System.out.println("验证码已过期");
            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(imageCodeInRequest, imageCodeInSession.getCode())) {
            System.out.println("验证码不匹配");
            throw new ValidateCodeException("验证码不匹配");
        }
        // 验证成功，删除session中的验证码
        logger.info("验证码成功");
        logger.info("登陆成功");
        sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
        sessionStrategy.removeAttribute(request, MyAuthenticationSuccessHandler.code);
    }

}
