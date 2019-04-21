package ru.laundromat.laundromatdataservice.security

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ReportAccessDeniedHandler : AccessDeniedHandler {
    val logger: Logger = LoggerFactory.getLogger(ReportAccessDeniedHandler::class.java)

    override fun handle(httpServletRequest: HttpServletRequest?, httpServletResponse: HttpServletResponse?, accessDeniedException: AccessDeniedException?) {
        val auth: Authentication? = SecurityContextHolder.getContext().authentication
        auth
                ?: logger.info("User + ${auth!!.name} attempted to access the protected URL: ${httpServletRequest!!.requestURI}")
    }
}