package ru.laundromat.laundromatdataservice.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

@Controller
class WasherStatusControler {
    @RequestMapping("/washerstatus")
    fun washerControlReport(httpServletRequest: HttpServletRequest): ModelAndView {
        val moduleData = mapOf("bean" to WasherStatusControler(),
                                "laundromatNums" to 1..10)
        return ModelAndView("washerstatus", moduleData)
    }

    class WasherStatusBean(var selectedLaundromatNum: Int = 1, var laundromatNumber: Int = 1, var report: String = "")
}