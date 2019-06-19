package ru.laundromat.laundromatdataservice.data.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "laundromats")
class Laundromat(
        @Id
        @GeneratedValue
        var id: Int,
        @Column(name = "number")
        var number: Int,
        @Column(name = "customerid")
        var customerId: Int,
        @Column(name = "address")
        var address: String,
        @Column(name = "phone")
        var phone: String,
        @Column(name = "datacreated")
        var datacreated: Date)
{
    constructor(number: Int, customerId: Int, address: String, phone: String, datacreated: Date):this(0, number, customerId, address, phone, datacreated)
}
