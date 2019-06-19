package ru.laundromat.laundromatdataservice.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.laundromat.laundromatdataservice.data.entities.StatInfo

interface StatInfoRepository : JpaRepository<StatInfo, Int>{
    fun getBycustomerId(customerId: Int): List<StatInfo>
}