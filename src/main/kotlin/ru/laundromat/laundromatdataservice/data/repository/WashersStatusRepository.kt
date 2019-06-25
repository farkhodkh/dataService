package ru.laundromat.laundromatdataservice.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.laundromat.laundromatdataservice.data.entities.WashersStatus

interface WashersStatusRepository: JpaRepository<WashersStatus, Int>{
    fun findAllBycustomerId(customerId: Int):List<WashersStatus>

//    @Query("FROM WashersStatus where customerid = ?1 AND laundromatid = ?2" )
    @Query("FROM WashersStatus WHERE id IN (SELECT Max(id) FROM WashersStatus WHERE customerid = ?1 AND laundromatnumber = ?2 GROUP BY laundromatnumber, whashernumber)")
    fun findAllCustomerLaundromatWasherStatus(customerId: Int, laundromatId: Int):List<WashersStatus>

//    @Query("FROM WashersStatus where laundromatid = ?1" )
    @Query("FROM WashersStatus WHERE id IN (SELECT Max(id) FROM WashersStatus WHERE laundromatnumber = ?1 GROUP BY laundromatnumber, whashernumber)")
    fun findAllByLaundromatId(laundromatId: Int):List<WashersStatus>

}