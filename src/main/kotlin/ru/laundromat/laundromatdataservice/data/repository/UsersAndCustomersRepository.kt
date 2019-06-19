package ru.laundromat.laundromatdataservice.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.laundromat.laundromatdataservice.data.entities.UsersWithCustomers


interface UsersAndCustomersRepository : JpaRepository<UsersWithCustomers, Int>