package ru.laundromat.laundromatdataservice.data.services

import ru.laundromat.laundromatdataservice.data.entities.UsersWithCustomers

interface UsersAndCustomersService {
    fun getAll(): List<UsersWithCustomers>
}