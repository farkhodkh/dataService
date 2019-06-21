package ru.laundromat.laundromatdataservice.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

@Controller
class WasherStatusControler {

    @RequestMapping("/washerstatus")
    fun washerControlReport(httpServletRequest: HttpServletRequest): ModelAndView {
        val moduleData = mapOf("bean" to WasherStatusControler(),
                                "laundromatNums" to 1..10,
                                "isActive" to true)
        return ModelAndView("washerstatus", moduleData)
    }

    @ResponseBody
    @RequestMapping("/getWasherStatus", method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json"))
    fun getWasherStatus(@PathVariable("laundromatId") laundromatId: String,
                        @PathVariable("washerId")  washerId: String,
                        model: Model): Map<Int, Boolean>{

        var messageObject = mutableMapOf<Int, Boolean>()

        messageObject.put(1, true)
        messageObject.put(2, true)

        return messageObject;
    }

    class WasherStatusBean(var selectedLaundromatNum: Int = 1, var laundromatNumber: Int = 1, var report: String = "")
}