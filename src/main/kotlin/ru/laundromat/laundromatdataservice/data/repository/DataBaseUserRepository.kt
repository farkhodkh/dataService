package ru.laundromat.laundromatdataservice.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.laundromat.laundromatdataservice.data.entities.DataBaseUser

interface DataBaseUserRepository: JpaRepository<DataBaseUser, Int>{
    fun getByUserName(name:String): DataBaseUser?
}