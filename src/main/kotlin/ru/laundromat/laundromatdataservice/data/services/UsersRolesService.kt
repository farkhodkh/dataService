package ru.laundromat.laundromatdataservice.data.services

import ru.laundromat.laundromatdataservice.data.entities.UserRole

interface UsersRolesService {
    fun getAll(): List<UserRole>
    fun findAllByName(name: String): List<UserRole>
}