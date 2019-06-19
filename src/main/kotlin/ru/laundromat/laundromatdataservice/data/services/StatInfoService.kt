package ru.laundromat.laundromatdataservice.data.services

import ru.laundromat.laundromatdataservice.data.entities.StatInfo

interface StatInfoService {
    fun getById(id: Int): StatInfo
    fun saveStatInfo(statInfo: StatInfo)
    fun updateStatInfo(id: Int, name: String, phone: String, email: String, address: String)
    fun deleteStatInfo(statInfo: StatInfo)
    fun getAll(): List<StatInfo>
    fun getBycustomerId(customerId: Int): List<StatInfo>
}