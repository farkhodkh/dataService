package ru.laundromat.laundromatdataservice.data.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "customers")
class Customer(
        @Id
        @GeneratedValue
        var id: Int,
        @Column(name = "name")
        var name: String,
        @Column(name = "phone")
        var phone: String,
        @Column(name = "email")
        var address: String,
        @Column(name = "address")
        var email: String,
        @Column(name = "datacreated")
        var datacreated: Date
) {
    constructor(name: String, phone: String, email: String, address: String, datecreated: Date) : this(0, name, phone, address, email, datecreated)
}