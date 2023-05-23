package org.subin.bootBoard.configs;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.subin.bootBoard.models.member.LoginFailureHandler;
import org.subin.bootBoard.models.member.LoginSuccessHandler;

import java.io.IOException;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/member/login")
                .usernameParameter("userId")
                .passwordParameter("userPw")
                .successHandler(new LoginSuccessHandler())
                .failureHandler(new LoginFailureHandler())
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/member/login");

        http.authorizeHttpRequests()
                .requestMatchers("/mypage/**").authenticated()  // 회원전용 url
                //.requestMatchers("/admin/**").hasAuthority("ADMIN") // 관리자 전용 url
                .anyRequest().permitAll();  // 그외 모든 페이지는 모든 회원이 접근 가능

        //에러 시 정해진 페이지로 이동
        http.exceptionHandling()
            .authenticationEntryPoint((req, res, e) -> {
                String URI = req.getRequestURI();

                if(URI.indexOf("/admin") != -1) {   // 관리자 페이지
                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "NOT AUTHORIZED");

                } else { // 회원전용 페이지
                    String redirectURL = req.getContextPath() + "/member/login";
                    res.sendRedirect(redirectURL);
                }
            });


        // 아이프레임 - 같은 도메인 내에서만 iframe 내 통신 가능하도록 한다.
        http.headers().frameOptions().sameOrigin();


        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return w -> w.ignoring().requestMatchers(
                "/css/**",
                "/js/**",
                "/images/**",
                "/errors/**"
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
