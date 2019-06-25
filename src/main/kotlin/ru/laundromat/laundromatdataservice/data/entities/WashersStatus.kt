package ru.laundromat.laundromatdataservice.data.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "washersstatus")
class WashersStatus(@Id
                    @GeneratedValue(strategy = GenerationType.AUTO)
                    val Id: Int,
                    @Column(name = "starttime")
                    val startTime: Date,
                    @Column(name = "customerid")
                    val customerId: Int,
                    @Column(name = "laundromatid")
                    val laundromatId: Int,
                    @Column(name = "laundromatnumber")
                    val laundromatNumber: Int,
                    @Column(name = "whashernumber")
                    val whasherNumber: Int,
                    @Column(name = "whasherstatus")
                    val whasherStatus: Int,
                    @Column(name = "statusdescription")
                    val StatusDescription: String
)