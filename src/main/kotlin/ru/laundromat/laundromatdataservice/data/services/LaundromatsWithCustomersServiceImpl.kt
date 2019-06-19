package ru.laundromat.laundromatdataservice.data.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.laundromat.laundromatdataservice.data.entities.LaundromatsWithCustomers
import ru.laundromat.laundromatdataservice.data.repository.LaundromatsWithCustomersRepository

@Service
class LaundromatsWithCustomersServiceImpl: LaundromatsWithCustomersService {
    lateinit var repository: LaundromatsWithCustomersRepository

    @Autowired
    fun setProductRepository(repository: LaundromatsWithCustomersRepository) {
        this.repository = repository
    }

    override fun getAll(): List<LaundromatsWithCustomers> {
        return repository.findAll()
    }

}