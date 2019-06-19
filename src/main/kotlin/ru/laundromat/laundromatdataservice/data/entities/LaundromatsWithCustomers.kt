package ru.laundromat.laundromatdataservice.data.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "laundromats_with_customers")
class LaundromatsWithCustomers(
        @Id
        var id: Int,
        @Column(name = "number")
        var number: Int,
        @Column(name = "customerid")
        var customerId: Int,
        @Column(name = "address")
        var address: String,
        @Column(name = "phone")
        var phone: String,
        @Column(name = "customername")
        var customerName: String,
        @Column(name = "datacreated")
        var datacreated: Date)