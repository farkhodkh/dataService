package ru.laundromat.laundromatdataservice.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

@Controller
class MainController{
    @RequestMapping("/login")
    fun homePage() : ModelAndView {

        return ModelAndView("login")
    }
}
