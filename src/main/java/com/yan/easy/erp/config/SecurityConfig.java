package com.yan.easy.erp.config;

import com.yan.easy.erp.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userServiceImpl)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/admin/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/admin/new-page-2/edit/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/user/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/user/**").hasAuthority("ROLE_USER")
                .antMatchers(HttpMethod.POST, "/admin/new-page-2/save").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/new-page-1/work_list/save").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/new-page-1/work_list/save/save").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/upload").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/new-page-1/work_list/edit").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.GET, "/gm/**").hasAuthority("GENERAL_MANAGER")
                .antMatchers(HttpMethod.POST, "/gm/**").hasAuthority("GENERAL_MANAGER")

                .antMatchers(HttpMethod.GET, "/rd/**").hasAuthority("RD_USER")
                .antMatchers(HttpMethod.POST, "/rd/**").hasAuthority("RD_MANAGER")

                .antMatchers(HttpMethod.GET, "/account/**").hasAuthority("FD_USER")
                .antMatchers(HttpMethod.POST, "/account/**").hasAuthority("FD_MANAGER")

                .antMatchers(HttpMethod.GET, "/hr/**").hasAuthority("HR_USER")
                .antMatchers(HttpMethod.POST, "/hr/**").hasAuthority("HR_MANAGER")

                .antMatchers(HttpMethod.GET).permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true);
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout");
    }
}
