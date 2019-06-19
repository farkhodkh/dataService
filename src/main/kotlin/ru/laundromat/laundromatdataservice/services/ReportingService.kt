package ru.laundromat.laundromatdataservice.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.laundromat.laundromatdataservice.data.entities.StatInfo
import ru.laundromat.laundromatdataservice.data.repository.StatInfoRepository
import ru.laundromat.laundromatdataservice.data.services.LaundromatService
import ru.laundromat.laundromatdataservice.security.DataBaseUser
import ru.laundromat.laundromatdataservice.security.UserAccessChecker.Companion.isAdminRoleGranted

@Service
class ReportingService {
    @Autowired
    lateinit var service: LaundromatService

    @Autowired
    lateinit var statisticService: StatInfoRepository

    fun all_statistics(laundromat_number: Int, washer_number: Int): String {
        val statInfoList: List<StatInfo>
        var filteredList: List<StatInfo>

        var dbUser: DataBaseUser = SecurityContextHolder.getContext().authentication.principal as DataBaseUser
        val customerId = dbUser.customerId

        if (isAdminRoleGranted() || customerId == 999) {
            statInfoList = statisticService.findAll()
            if (laundromat_number == 0 && washer_number == 0) {
                filteredList = statInfoList
            } else if (washer_number == 0) {
                filteredList = statInfoList.filter { it.laundromatNumber == laundromat_number }
            } else {
                filteredList = statInfoList.filter { it.laundromatNumber == laundromat_number && it.washerNumber == washer_number }
            }
        } else {
            statInfoList = statisticService.getBycustomerId(customerId)
            if (laundromat_number == 0 && washer_number == 0) {
                filteredList = statInfoList
            } else if (washer_number == 0) {
                filteredList = statInfoList.filter { it.laundromatNumber == laundromat_number }
            } else {
                filteredList = statInfoList.filter { it.laundromatNumber == laundromat_number && it.washerNumber == washer_number }
            }
        }

        var reportBody = ""

        filteredList.forEach {
            reportBody += "<tr>\n" +
                    "<td style=\"width: 127px; text-align: center;\">${it.Id}</td>\n" +
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
                "        <td style=\"width: 200px; text-align: center;\">Дата</td>\n" +
                "        <td style=\"width: 94px; text-align: center;\">Номер прач.</td>\n" +
                "        <td style=\"width: 94px; text-align: center;\">Номер машинки</td>\n" +
                "        <td style=\"width: 94px; text-align: center;\">Сумма</td>\n" +
                "        <td style=\"width: 94px; text-align: center;\">E-Mail</td>\n" +
                "        <td style=\"width: 94px; text-align: center;\">Телефон</td></tr>\n"

        val reportFooter = "</tbody></table>"

        return "${reportHeader}${reportBody}${reportFooter}"
    }

//    fun all_statistics(): String {
//        val statInfoList = statisticService.findAll()
//        val htmlBookings = statInfoList.map {
//            "<tr>" +
//                    "<td>${it.Id}</td>" +
//                    "<td>${it.operationTime}</td>" +
//                    "<td>${it.washerNumber}</td>" +
//                    "<td>${it.cheque}</td>" +
//                    "<td>${it.customerEmal}</td>" +
//                    "<td>${it.customerPhone}</td>" +
//                    "</tr>"
//        }
//        val reportHeader = "<table><tr><th>Performance</th><th>Seat</th><th>Customer</th></tr>"
//        val reportFooter = "</table>"
//        return "${reportHeader}${htmlBookings.joinToString()}${reportFooter}"
//    }

//    fun gutCustomersLaundromatsList(customerId: Int): List<Laundromat> {
//        var laundromatsList: MutableList<Laundromat>
//
//        if (customerId.equals(999))
//            laundromatsList = service.getAll().toMutableList()
//        else
//            laundromatsList = service.getByCustomerId(customerId).toMutableList()
//
//        laundromatsList.add(0, Laundromat(0, customerId, "", "", Date()))
//
//        return laundromatsList.toList()
//    }
}