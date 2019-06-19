package ru.laundromat.laundromatdataservice.data.entities

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users_ws_customer")
class UsersWithCustomers(@Id
//                 @GeneratedValue
                 var id: Int,
                         @Column(name = "username")
                 var userName: String,
                         @Column(name = "activated")
                 var activated: Boolean,
                         @Column(name = "customerid")
                 var customerid: Int,
                         @Column(name = "datacreated")
                 var dateCreated: Date,
                         @Column(name = "useremail")
                 var userEMail: String,
                         @Column(name = "customername")
                 var customerName: String
)