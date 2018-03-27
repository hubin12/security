package com.mrbeard.config;

import com.mrbeard.services.CustomUserService;
import com.mrbeard.utils.MyPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) //启用Security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService() { // 注册UserDetailsService 的bean
        return new CustomUserService();
    }

    /**
     * 配置.忽略的静态文件，不加的话，登录之前页面的css,js不能正常使用，得登录之后才能正常.
     * WEB安全
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略URL
        web.ignoring().antMatchers("/**/*.js", "/lang/*.json",
                "/**/*.css", "/**/*.js", "/**/*.map", "/**/*.html", "/**/*.png");
        //web.privilegeEvaluator(new DefaultWebInvocationPrivilegeEvaluator(customerFilterSecurityInterceptor()));
    }


    /**
     * HTTP请求安全处理
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()     //通过authorizeRequests()方法来开始请求权限配置
                .anyRequest().authenticated() //对http所有的请求必须通过授权认证才可以访问
                .and()
                .formLogin()
                .loginPage("/login")  //通过formlogin方法登录，并设置登录url为/login
                .loginProcessingUrl("/login/form") //login页面form提交action
                .failureForwardUrl("/login_error") //指定登录失败后跳转到/login?error页面
                .permitAll()//登陆时不需要验证
                .and()
                .logout()
                .permitAll();//登出时也不需要验证
    }

    /**
     * 身份验证管理生成器
     *
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //并根据传入的AuthenticationManagerBuilder中的userDetailsService方法来接收我们自定义的认证方法。
        //且该方法必须要实现UserDetailsService这个接口。
        /*auth
                //.userDetailsSevice(new CustomUserService())
                .inMemoryAuthentication()
                .passwordEncoder(new MyPasswordEncoder())
                .withUser("user")
                .password("password")
                .roles("USER");*/
        //.userDetailsService(new CustomUserService())
        //密码使用BCryptPasswordEncoder()方法验证，因为这里使用了BCryptPasswordEncoder()
        // 方法验证。所以在注册用户的时候在接收前台明文密码之后也需要使用
        // BCryptPasswordEncoder().encode(明文密码)方法加密密码。

        //.passwordEncoder(new MyPasswordEncoder());
        auth
                .userDetailsService(customUserService()).passwordEncoder(new MyPasswordEncoder());

    }

}
