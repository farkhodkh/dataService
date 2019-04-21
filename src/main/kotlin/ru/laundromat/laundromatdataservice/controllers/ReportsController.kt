package ru.laundromat.laundromatdataservice.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import ru.laundromat.laundromatdataservice.services.ReportingService
import kotlin.reflect.full.declaredMemberFunctions
import org.springframework.web.bind.annotation.GetMapping



@Controller
class ReportsController {
    @Autowired
    lateinit var reportingService: ReportingService

    private fun getListOfReports() = reportingService::class.declaredMemberFunctions.map { it.name }

    @RequestMapping("")
    fun main() = ModelAndView("reports",
            mapOf("reports" to getListOfReports(),
                    "bean" to ReportSettingsBackingBean(),
                    "laundromatNums" to 1..10,
                    "washerNums" to 0..15))

    @RequestMapping("/getReport")
    fun getReport(model: ReportSettingsBackingBean): ModelAndView {
        val matchedReport = reportingService::class.declaredMemberFunctions.filter { it.name == model.report }.firstOrNull()
        val result = matchedReport?.call(reportingService, model.selectedLaundromatNum, model.selectedWasherNum) ?: ""
        val moduleData = mapOf("reports" to getListOfReports(),
                "bean" to model,
                "laundromatNums" to 1..10,
                "washerNums" to 0..15,
                "result" to result)

        return ModelAndView("reports", moduleData)
    }

    @GetMapping("/403")
    fun error403(): String {
        return "/403"
    }
}

class ReportSettingsBackingBean(var selectedLaundromatNum: Int = 1, var selectedWasherNum: Int = 0, var report: String = "")
