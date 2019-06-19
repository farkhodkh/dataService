package ru.laundromat.laundromatdataservice.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.laundromat.laundromatdataservice.data.entities.LaundromatsWithCustomers

interface LaundromatsWithCustomersRepository: JpaRepository<LaundromatsWithCustomers, Int>