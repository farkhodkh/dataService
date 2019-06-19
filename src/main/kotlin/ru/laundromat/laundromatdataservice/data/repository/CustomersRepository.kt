package ru.laundromat.laundromatdataservice.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.laundromat.laundromatdataservice.data.entities.Customer

interface CustomersRepository : JpaRepository<Customer, Int>