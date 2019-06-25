package ru.laundromat.laundromatdataservice.data.services

import ru.laundromat.laundromatdataservice.data.entities.WashersStatus

interface WashersStatusService {
    fun getById(id: Int): WashersStatus
    fun saveWasherStatus(whaserStatus: WashersStatus)
    fun deleteWasherStatus(whaserStatus: WashersStatus)
    fun getAll(): List<WashersStatus>
    fun getByCustomerId(customerId: Int): List<WashersStatus>
    fun getByCustomerLaundromatId(laundromatId: Int): List<WashersStatus>
    fun findAllCustomerLaundromatWasherStatus(customerId: Int, laundromatId: Int): List<WashersStatus>
}