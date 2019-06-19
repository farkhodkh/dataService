package ru.laundromat.laundromatdataservice.data.services

import ru.laundromat.laundromatdataservice.data.entities.DataBaseUser

interface DataBaseUsersService {
    fun getById(id: Int): DataBaseUser
    fun getByUserName(name: String): DataBaseUser?
    fun saveUser(user: DataBaseUser)
    fun updateUser(id: Int, userName: String, userPassword: String, userEMail: String, customerId: Int)
    fun deleteUser(user: DataBaseUser)
    fun getAll(): List<DataBaseUser>
}
