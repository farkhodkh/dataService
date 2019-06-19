package ru.laundromat.laundromatdataservice.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.PrintWriter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationEntryPoint: BasicAuthenticationEntryPoint() {
    override fun afterPropertiesSet() {
//        super.afterPropertiesSet()
        setRealmName("REALM_NAME");
    }

    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, authException: AuthenticationException?) {
        super.commence(request, response, authException)
    }

    override fun getRealmName(): String {
        return super.getRealmName()
    }

    override fun setRealmName(realmName: String?) {
        super.setRealmName(realmName)
    }
}