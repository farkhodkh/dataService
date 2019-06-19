package ru.laundromat.laundromatdataservice.data.apicontrollers

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.laundromat.laundromatdataservice.data.entities.StatInfo
import ru.laundromat.laundromatdataservice.data.services.StatInfoService
import ru.laundromat.laundromatdataservice.security.DataBaseUser
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale

@Controller
class MainApiController {
    lateinit var service: StatInfoService

    @Autowired
    fun setUserService(service: StatInfoService) {
        this.service = service
    }

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @PostMapping("/api/statistics/washerdata")
    fun staticInfoReceiver(@RequestParam data: String): ResponseEntity<String> {

        var statInfo: StatInfo

        try {
            statInfo = readData(data)
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }

        try {
            service.saveStatInfo(statInfo)
        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
        }

        return ResponseEntity.ok("DONE, line id: ${statInfo.Id}")
    }

    private fun readData(data: String): StatInfo {
        var dbUser: DataBaseUser = SecurityContextHolder.getContext().authentication.principal as DataBaseUser

        val jsonNode = objectMapper.readTree(data)
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)

        val strOperationTime = jsonNode.get("operationTime")

        if (strOperationTime == null)
            throw WasherDataReadException("Не указан параметр operationTime")

        var operationTime: Date

        try {
            operationTime = format.parse(jsonNode.get("operationTime").asText())
        } catch (ex: Exception) {
            throw WasherDataReadException("Не верный формат параметра operationTime")
        }

        var laundromatNumber: Int

        try {
            laundromatNumber = jsonNode.get("laundromatNumber").asInt()
        } catch (ex: Exception) {
            throw WasherDataReadException("Не верный формат параметра laundromatNumber")
        }

        var washerNumber: Int

        try {
            washerNumber = jsonNode.get("washerNumber").asInt()
        } catch (ex: Exception) {
            throw WasherDataReadException("Не верный формат параметра washerNumber")
        }

        var cheque: Double

        try {
            cheque = jsonNode.get("cheque").asDouble()
        } catch (ex: Exception) {
            throw WasherDataReadException("Не верный формат параметра cheque")
        }

        var billing: Double

        try {
            billing = jsonNode.get("billing").asDouble()
        } catch (ex: Exception) {
            throw WasherDataReadException("Не верный формат параметра billing")
        }

        var statInfo: StatInfo = try {
            StatInfo(
                    0,
                    operationTime,
                    dbUser.customerId,
                    laundromatNumber,
                    washerNumber,
                    cheque,
                    billing,
                    jsonNode.get("customerEmal").asText(),
                    jsonNode.get("customerPhone").asText()
            )
        } catch (e: Exception) {
            throw WasherDataReadException("Ошибка ввода контактной информации")
        }

        return statInfo
    }
}

class WasherDataReadException(message: String) : Exception(message)