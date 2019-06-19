package ru.laundromat.laundromatdataservice.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.laundromat.laundromatdataservice.data.entities.UserRole

interface UsersRolesRepository : JpaRepository<UserRole, Int>{
    fun findAllByName(name: String): List<UserRole>
}