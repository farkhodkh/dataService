package ru.laundromat.laundromatdataservice.data.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView
import ru.laundromat.laundromatdataservice.data.entities.Laundromat
import ru.laundromat.laundromatdataservice.data.entities.WashersStatus
import ru.laundromat.laundromatdataservice.data.services.LaundromatService
import ru.laundromat.laundromatdataservice.data.services.WashersStatusService
import ru.laundromat.laundromatdataservice.security.DataBaseUser
import javax.servlet.http.HttpServletRequest

@Controller
class WasherStatusControler {
    @Autowired
    lateinit var service: LaundromatService
    @Autowired
    lateinit var washerStatusService: WashersStatusService

    @RequestMapping("/washerstatus")
    fun washerControlReport(httpServletRequest: HttpServletRequest): ModelAndView {
        val customerId = (SecurityContextHolder.getContext().authentication.principal as DataBaseUser).customerId

        val laundromatsList = gutCustomersLaundromatsList(customerId)
        val laundromatNumbers = mutableListOf<Int>()

        laundromatsList.forEachIndexed { index, value -> laundromatNumbers.add(value.number) }

        val moduleData = mapOf("bean" to WasherStatusControler(),
                "laundromatNums" to laundromatNumbers,
                "bean" to WasherStatusBean())
        return ModelAndView("washerstatus", moduleData)
    }

    @ResponseBody
    @RequestMapping("/getWasherStatus", method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json"))
    fun getWasherStatus(@RequestParam("laundromatId") laundromatId: Int,
                        model: Model): List<WashersStatus> {

        val customerId = (SecurityContextHolder.getContext().authentication.principal as DataBaseUser).customerId
        val laundromatsList = gutCustomersLaundromatsList(customerId)
        val laundromatNumbers = mutableListOf<Int>()

        laundromatsList.forEachIndexed { index, value -> laundromatNumbers.add(value.number) }

        val washerStatusList: List<WashersStatus>

        if (customerId == 999)
            washerStatusList = washerStatusService.getByCustomerLaundromatId(laundromatId)
        else
            washerStatusList = washerStatusService.findAllCustomerLaundromatWasherStatus(customerId, laundromatId)

        model.addAttribute("washerStatusList", washerStatusList)

        val moduleData = mapOf("bean" to WasherStatusControler(),
                "laundromatNums" to laundromatNumbers,
                "bean" to WasherStatusBean(),
                "washerStatusList" to washerStatusList)

        return washerStatusList
    }

    fun gutCustomersLaundromatsList(customerId: Int): List<Laundromat> {
        var laundromatsList: MutableList<Laundromat>

        if (customerId.equals(999))
            laundromatsList = service.getAll().toMutableList()
        else
            laundromatsList = service.getByCustomerId(customerId).toMutableList()

        return laundromatsList.toList()
    }

    class WasherStatusBean(var selectedLaundromatNum: Int = 1, var laundromatNumber: Int = 1, var report: String = "")
}