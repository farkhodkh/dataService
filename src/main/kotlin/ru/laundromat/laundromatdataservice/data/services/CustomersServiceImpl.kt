package ru.laundromat.laundromatdataservice.data.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.laundromat.laundromatdataservice.data.entities.Customer
import ru.laundromat.laundromatdataservice.data.repository.CustomersRepository

@Service
class CustomersServiceImpl : CustomersService {
    private var repository: CustomersRepository? = null

    @Autowired
    fun setProductRepository(repository: CustomersRepository) {
        this.repository = repository
    }

    override fun getById(id: Int): Customer {
        return repository?.getOne(id)!!
    }

    override fun saveUser(customer: Customer) {
        repository?.save(customer)
    }

    override fun updateUser(id: Int, name: String, phone: String, email: String, address: String) {
        var dbUser = repository?.getOne(id)!!
        dbUser.name = name
        dbUser.phone = phone
        dbUser.email = email
        dbUser.address = address

        repository?.save(dbUser)
    }

    override fun deleteUser(customer: Customer) {
        repository?.delete(customer)
    }

    override fun getAll(): List<Customer> {
        return repository?.findAll()!!
    }

}