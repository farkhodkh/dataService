package ru.laundromat.laundromatdataservice.data.services

import ru.laundromat.laundromatdataservice.data.entities.Laundromat

interface LaundromatService {
    fun getById(id: Int): Laundromat
    fun saveLaundromat(user: Laundromat)
    fun updateLaundromat(id: Int, number: Int, address: String, customerId: Int, phone: String)
    fun deleteLaundromat(user: Laundromat)
    fun getAll(): List<Laundromat>
    fun getByCustomerId(customerId: Int): List<Laundromat>
}