package ru.laundromat.laundromatdataservice.data.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "databaseusers")
class DataBaseUser(
        @Id
        @GeneratedValue
        var id: Int,
        @Column(name = "username")
        var userName: String,
        @Column(name = "userpassword")
        var userPassword: String,
        @Column(name = "datacreated")
        var dateCreated: Date,
        @Column(name = "activated")
        var activated: Boolean,
        @Column(name = "useremail")
        var userEMail: String,
        @Column(name = "customerid")
        var customerid: Int
) {
        constructor(userName: String, userPassword: String, dateCreated: Date, userEMail: String, customerid: Int) : this(0, userName, userPassword, dateCreated, true, userEMail, customerid)
}
