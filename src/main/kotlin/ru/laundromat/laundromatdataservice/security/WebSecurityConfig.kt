package ru.laundromat.laundromatdataservice.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
@ComponentScan("LaundromatdataserviceApplication")
class WebSecurityConfig() : WebSecurityConfigurerAdapter() {


    @Autowired
    lateinit var accessDeniedHandler: ReportAccessDeniedHandler

    @Autowired
    lateinit var userDetailsService: ReportUserDetailsService

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic().realmName("REALM_NAME")
                .and().formLogin()
                .loginPage("/login")
                .permitAll()
                .and().logout()
                .permitAll()
                .and().exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)

    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!
                .userDetailsService(userDetailsService)
    }

//    @Throws(Exception::class)
//    @Autowired
//    fun configureGlobal(auth: AuthenticationManagerBuilder) {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("123456").roles("ADMIN")
//    }
}