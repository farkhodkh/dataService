package ru.laundromat.laundromatdataservice.domain

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class StatInfo(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val Id: Long,
        val operationDay: LocalDate,
        val operationTime: LocalTime,
        val laundromatNumber: Int,
        val washerNumber: Int,
        val cheque: BigDecimal,
        val billing: BigDecimal,
        val customerEmal: String,
        val customerPhone: String
        ) {

}