package ru.laundromat.laundromatdataservice.data.services

import ru.laundromat.laundromatdataservice.data.entities.Customer

interface CustomersService {
    fun getById(id: Int): Customer
    fun saveUser(user: Customer)
    fun updateUser(id: Int, name: String, phone: String, email: String, address: String)
    fun deleteUser(user: Customer)
    fun getAll(): List<Customer>
}