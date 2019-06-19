package ru.laundromat.laundromatdataservice.data.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.laundromat.laundromatdataservice.data.entities.UserRole
import ru.laundromat.laundromatdataservice.data.repository.UsersRolesRepository

@Service
class UsersRolesServiceImpl : UsersRolesService {

    private var repository: UsersRolesRepository? = null

    @Autowired
    fun setProductRepository(repository: UsersRolesRepository) {
        this.repository = repository
    }

    override fun getAll(): List<UserRole> {
        return repository?.findAll()!!
    }

    override fun findAllByName(name: String): List<UserRole> {
        return repository?.findAllByName(name)!!
    }
}