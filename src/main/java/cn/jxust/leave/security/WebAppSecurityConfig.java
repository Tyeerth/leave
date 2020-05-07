package cn.jxust.leave.security;

import cn.jxust.leave.exceptionResolver.VerifyCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author tyeerth
 * @date 2020/5/1 - 15:39
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoderService passwordEncoder;
    @Autowired
    private CustomizeAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private CustomizeAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private CustomizeLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    @Autowired
    private VerifyCodeFilter verifyCodeFilter;
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                     .authorizeRequests()
                      .antMatchers("/index","/login","/student/login","/bootstrap/**","/css/**","/getCode")
                       .permitAll()
//                        .antMatchers("/student/**")
//                        .hasAuthority("1")
//                    .anyRequest()
//                    .authenticated()
                .and()
                    .formLogin()
                    .successHandler(authenticationSuccessHandler)//登录成功处理逻辑
                    . failureHandler(authenticationFailureHandler)//登录失败处理逻辑
            .and()
                .exceptionHandling()					// 指定异常处理器
                .authenticationEntryPoint(authenticationEntryPoint)//匿名用户访问无权限资源时的异常处理
                .and()
                .logout()
                .permitAll()//允许所有用户
                .logoutSuccessHandler(logoutSuccessHandler)//登出成功处理逻辑
                .deleteCookies("JSESSIONID")//登出之后删除cookie// 开启退出功能

//            .and().sessionManagement().//限制同一账号只能一个用户使用
//                maximumSessions(1)
//             .expiredSessionStrategy(sessionInformationExpiredStrategy)//会话信息过期策略会话信息过期策略(账号被挤下线)
        ;
        /* 添加验证码过滤器 */
        security.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
