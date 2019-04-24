package ru.laundromat.laundromatdataservice.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.access.AccessDeniedHandler
import java.io.IOException

@Configuration
@EnableWebSecurity
@ComponentScan("LaundromatdataserviceApplication")
class WebSecurityConfig(): WebSecurityConfigurerAdapter() {


    @Autowired
    lateinit var accessDeniedHandler: ReportAccessDeniedHandler

    @Autowired
    lateinit var userDetailsService: ReportUserDetailsService

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity){
        http
                .csrf().disable()
                .authorizeRequests()
                //.antMatchers("/", "/index", "/about").permitAll()
//                .antMatchers("/admin/**").hasAnyRole("ADMIN")
//                .antMatchers("/user/**").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)

    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userDetailsService);
    }

    @Throws(Exception::class)
    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder){
        auth.inMemoryAuthentication()
                .withUser("user").password("123456").roles("USER")
                .and()
                .withUser("admin").password("123456").roles("ADMIN")
    }

}