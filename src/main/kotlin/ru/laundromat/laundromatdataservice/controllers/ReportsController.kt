package ru.laundromat.laundromatdataservice.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import ru.laundromat.laundromatdataservice.common.Constants
import ru.laundromat.laundromatdataservice.data.entities.Laundromat
import ru.laundromat.laundromatdataservice.data.services.LaundromatService
import ru.laundromat.laundromatdataservice.security.DataBaseUser
import ru.laundromat.laundromatdataservice.services.ReportingService
import java.util.*
import kotlin.reflect.full.declaredMemberFunctions

@Controller
class ReportsController {
    @Autowired
    lateinit var reportingService: ReportingService
    @Autowired
    lateinit var service: LaundromatService

    @RequestMapping("")
    fun main() = ModelAndView("index",
            mapOf("bean" to MainPageClass(),
                    "reportsUrl" to Constants.ReportsUrl,
                    "washerStatusUrl" to Constants.WasherStatusUrl))

    private fun getListOfReports() = reportingService::class.declaredMemberFunctions.map { it.name }

    @RequestMapping("/reports")
    fun reports(): ModelAndView {
        val customerId = (SecurityContextHolder.getContext().authentication.principal as DataBaseUser).customerId

        val laundromatsList = gutCustomersLaundromatsList(customerId)
        val laundromatNumbers = mutableListOf<Int>()

        laundromatsList.forEachIndexed { index, value -> laundromatNumbers.add(value.number) }

        return ModelAndView("reports",
                mapOf("reports" to getListOfReports(),
                        "bean" to ReportSettingsBackingBean(),
                        "laundromatNums" to laundromatNumbers,
                        "washerNums" to 0..15))
    }

    @RequestMapping("/getReport")
    fun getReport(model: ReportSettingsBackingBean): ModelAndView {

        val customerId = (SecurityContextHolder.getContext().authentication.principal as DataBaseUser).customerId

        val laundromatsList = gutCustomersLaundromatsList(customerId)
        val laundromatNumbers = mutableListOf<Int>()

        laundromatsList.forEachIndexed { index, value -> laundromatNumbers.add(value.number) }

        val matchedReport = reportingService::class.declaredMemberFunctions.filter { it.name == model.report }.firstOrNull()
        val result = matchedReport?.call(reportingService, model.selectedLaundromatNum, model.selectedWasherNum) ?: ""
        val moduleData = mapOf("reports" to getListOfReports(),
                "bean" to model,
                "laundromatNums" to laundromatNumbers,
                "washerNums" to 0..15,
                "result" to result)

        return ModelAndView("reports", moduleData)
    }

    @GetMapping("/403")
    fun error403(): String {
        return "/403"
    }

    fun gutCustomersLaundromatsList(customerId: Int): List<Laundromat> {
        var laundromatsList: MutableList<Laundromat>

        if (customerId.equals(999))
            laundromatsList = service.getAll().toMutableList()
        else
            laundromatsList = service.getByCustomerId(customerId).toMutableList()

        laundromatsList.add(0, Laundromat(0, customerId, "", "", Date()))

        return laundromatsList.toList()
    }
}

class MainPageClass(var homeUrl: String = Constants.HomeUrl.toString(), var reportsUrl: String = homeUrl + "/reports", var washerstatusUrl: String = homeUrl + "/washerstatus")

class ReportSettingsBackingBean(var selectedLaundromatNum: Int = 1, var selectedWasherNum: Int = 0, var report: String = "")
