package ru.laundromat.laundromatdataservice.data.services

import ru.laundromat.laundromatdataservice.data.entities.LaundromatsWithCustomers

interface LaundromatsWithCustomersService{
    fun getAll(): List<LaundromatsWithCustomers>
}