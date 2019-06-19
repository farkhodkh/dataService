package ru.laundromat.laundromatdataservice.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.laundromat.laundromatdataservice.data.entities.Laundromat

interface LaundromatsRepository: JpaRepository<Laundromat, Int>{
    fun getByCustomerId(customerId: Int): List<Laundromat>
}