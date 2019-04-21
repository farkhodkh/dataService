package ru.laundromat.laundromatdataservice.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.laundromat.laundromatdataservice.data.StatisticRepository
import ru.laundromat.laundromatdataservice.domain.StatInfo
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Service
class ReportingService {

    @Autowired
    lateinit var statisticService: StatisticRepository
    var laundromat_number: Int = 0
    var washer_number: Int = 0


    fun allTestDate(laundromat_number: Int, washer_number: Int): String {
        val calendar: Calendar = Calendar.getInstance()

        val localDateTime: LocalDateTime = LocalDateTime.now()

        val statInfoList = arrayListOf<StatInfo>()
        var filteredList = listOf<StatInfo>()

        statInfoList.add(StatInfo(1, localDateTime.toLocalDate(), localDateTime.toLocalTime(), 1, 1, BigDecimal(75), BigDecimal(0), "mail@mail.ru", "+7111111111"))
        statInfoList.add(StatInfo(2, localDateTime.toLocalDate(), localDateTime.toLocalTime(), 1, 1, BigDecimal(175), BigDecimal(0), "mail1@mail.ru", "+7222222222"))
        statInfoList.add(StatInfo(2, localDateTime.toLocalDate(), localDateTime.toLocalTime(), 1, 1, BigDecimal(175), BigDecimal(0), "mail1@mail.ru", "+7222222222"))
        statInfoList.add(StatInfo(2, localDateTime.toLocalDate(), localDateTime.toLocalTime(), 1, 2, BigDecimal(175), BigDecimal(0), "mail1@mail.ru", "+7222222222"))
        statInfoList.add(StatInfo(2, localDateTime.toLocalDate(), localDateTime.toLocalTime(), 1, 3, BigDecimal(175), BigDecimal(0), "mail1@mail.ru", "+7222222222"))
        statInfoList.add(StatInfo(3, localDateTime.toLocalDate(), localDateTime.toLocalTime(), 5, 1, BigDecimal(85), BigDecimal(0), "mail2@mail.ru", "+7333333333"))
        statInfoList.add(StatInfo(4, localDateTime.toLocalDate(), localDateTime.toLocalTime(), 8, 3, BigDecimal(90), BigDecimal(0), "mail3@mail.ru", "+7444444444"))
        statInfoList.add(StatInfo(5, localDateTime.toLocalDate(), localDateTime.toLocalTime(), 5, 5, BigDecimal(110), BigDecimal(0), "mail4@mail.ru", "+75555555555"))
        statInfoList.add(StatInfo(6, localDateTime.toLocalDate(), localDateTime.toLocalTime(), 5, 5, BigDecimal(190), BigDecimal(0), "mail5@mail.ru", "+76666666666"))
        statInfoList.add(StatInfo(7, localDateTime.toLocalDate(), localDateTime.toLocalTime(), 10, 5, BigDecimal(70), BigDecimal(0), "mail6@mail.ru", "+77777777777"))


        if (washer_number == 0) {
            filteredList = statInfoList.filter { it.laundromatNumber == laundromat_number }
        } else {
            filteredList = statInfoList.filter { it.laundromatNumber == laundromat_number && it.washerNumber == washer_number }
        }

        var reportBody = ""

        filteredList.forEach {
            reportBody += "<tr>\n" +
                    "<td style=\"width: 127px; text-align: center;\">${it.Id}</td>\n" +
                    "<td style=\"width: 127px; text-align: center;\">${it.operationDay}</td>\n" +
                    "<td style=\"width: 127px; text-align: center;\">${it.operationTime}</td>\n" +
                    "<td style=\"width: 127px; text-align: center;\">${it.laundromatNumber}</td>\n" +
                    "<td style=\"width: 127px; text-align: center;\">${it.washerNumber}</td>\n" +
                    "<td style=\"width: 127px; text-align: center;\">${it.cheque}</td>\n" +
                    "<td style=\"width: 127px; text-align: center;\">${it.customerEmal}</td>\n" +
                    "<td style=\"width: 127px; text-align: center;\">${it.customerPhone}</td>\n" +
                    "</tr>\n"
        }

        val reportHeader = "" +
                "<table border=\"2\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "        <td style=\"width: 94px; text-align: center;\">№</td>\n" +
                "        <td style=\"width: 200px; text-align: center;\">День</td>\n" +
                "        <td style=\"width: 200px; text-align: center;\">Время</td>\n" +
                "        <td style=\"width: 94px; text-align: center;\">Номер прач.</td>\n" +
                "        <td style=\"width: 94px; text-align: center;\">Номер машинки</td>\n" +
                "        <td style=\"width: 94px; text-align: center;\">Сумма</td>\n" +
                "        <td style=\"width: 94px; text-align: center;\">E-Mail</td>\n" +
                "        <td style=\"width: 94px; text-align: center;\">Телефон</td></tr>\n"

        val reportFooter = "</tbody></table>"

        return "${reportHeader}${reportBody}${reportFooter}"
    }

    fun all_statistics(): String {
        val statInfoList = statisticService.findAll()
        val htmlBookings = statInfoList.map {
            "<tr>" +
                    "<td>${it.Id}</td>" +
                    "<td>${it.operationDay}</td>" +
                    "<td>${it.washerNumber}</td>" +
                    "<td>${it.cheque}</td>" +
                    "<td>${it.customerEmal}</td>" +
                    "<td>${it.customerPhone}</td>" +
                    "</tr>"
        }
        val reportHeader = "<table><tr><th>Performance</th><th>Seat</th><th>Customer</th></tr>"
        val reportFooter = "</table>"
        return "${reportHeader}${htmlBookings.joinToString()}${reportFooter}"
    }
}