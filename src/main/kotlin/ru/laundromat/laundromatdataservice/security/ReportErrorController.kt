package ru.laundromat.laundromatdataservice.security

import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

@Component
class ReportErrorController: ErrorController{
    lateinit var errorAttributes: ErrorAttributes

    override fun getErrorPath(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @RequestMapping(value = ["/error"], produces = arrayOf("text/html"))
    fun errorHtml(httpServletRequest: HttpServletRequest): ModelAndView {
        return ModelAndView("403")
    }
}
