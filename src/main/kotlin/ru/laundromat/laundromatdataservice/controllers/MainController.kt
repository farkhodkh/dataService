package ru.laundromat.laundromatdataservice.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import java.io.IOException
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest

@Controller
class MainController {
    @RequestMapping("/login")
    fun homePage(): ModelAndView {
        return ModelAndView("login")
    }

    @GetMapping("/error")
    fun error(): ModelAndView{
        return ModelAndView("error")
    }

    @RequestMapping("/exit")
    fun exit(request: HttpServletRequest, response: HttpServletResponse) {
        SecurityContextLogoutHandler().logout(request, null, null)
        try {
            response.sendRedirect(request.getHeader("referer"))
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
