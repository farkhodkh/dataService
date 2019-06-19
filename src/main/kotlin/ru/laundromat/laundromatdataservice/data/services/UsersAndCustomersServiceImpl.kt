package ru.laundromat.laundromatdataservice.data.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.laundromat.laundromatdataservice.data.entities.UsersWithCustomers
import ru.laundromat.laundromatdataservice.data.repository.UsersAndCustomersRepository

@Service
class UsersAndCustomersServiceImpl : UsersAndCustomersService {
    lateinit var repository: UsersAndCustomersRepository

    @Autowired
    fun setProductRepository(repository: UsersAndCustomersRepository) {
        this.repository = repository
    }

    override fun getAll(): List<UsersWithCustomers> {
        return repository.findAll()
    }
}