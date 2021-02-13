package com.arifinmn.projectapi.securities;

import com.arifinmn.projectapi.securities.AuthTokenFilter;
import com.arifinmn.projectapi.securities.jwt.AuthEntryPointJwt;
import com.arifinmn.projectapi.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {

        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/rooms/**").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/api/schedules/**").permitAll()
                .antMatchers("/api/tickets/**").permitAll()

                //TICKETS AUTH
//                .antMatchers(HttpMethod.GET, "/api/tickets/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
//                .antMatchers(HttpMethod.POST, "/api/tickets/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/api/tickets/**").hasAnyAuthority("ROLE_ADMIN")
//                .antMatchers(HttpMethod.PUT, "/api/tickets/**").hasAnyAuthority("ROLE_ADMIN")

                //ScheduleService AUTH
//                .antMatchers(HttpMethod.GET, "/api/schedules/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
//                .antMatchers(HttpMethod.POST, "/api/schedules/**").hasAnyAuthority("ROLE_ADMIN")
//                .antMatchers(HttpMethod.PUT, "/api/schedules/**").hasAnyAuthority("ROLE_ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/api/schedules/**").hasAnyAuthority("ROLE_ADMIN")
//                .antMatchers("/api/schedules/**").permitAll()

                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}