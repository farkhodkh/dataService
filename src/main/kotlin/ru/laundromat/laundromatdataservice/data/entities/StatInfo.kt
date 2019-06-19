package ru.laundromat.laundromatdataservice.data.entities

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "statisticinfo")
data class StatInfo(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val Id: Int,
        @Column(name = "operationtime")
        val operationTime: Date,
        @Column(name = "customerid")
        val customerId: Int,
        @Column(name = "laundromatnumber")
        val laundromatNumber: Int,
        @Column(name = "washernumber")
        val washerNumber: Int,
        @Column(name = "cheque")
        val cheque: Double,
        @Column(name = "billing")
        val billing: Double,
        @Column(name = "customeremal")
        val customerEmal: String,
        @Column(name = "customerphone")
        val customerPhone: String
)