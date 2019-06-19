package ru.laundromat.laundromatdataservice.data.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.laundromat.laundromatdataservice.data.entities.DataBaseUser
import ru.laundromat.laundromatdataservice.data.repository.DataBaseUserRepository

@Service
class DataBaseUsersServiceImpl: DataBaseUsersService {
    override fun getByUserName(name: String): DataBaseUser? {
        return repository?.getByUserName(name)
    }

    private var repository: DataBaseUserRepository? = null

    @Autowired
    fun setProductRepository(repository: DataBaseUserRepository) {
        this.repository = repository
    }

    override fun getById(id: Int): DataBaseUser {
        return repository?.getOne(id)!!
    }

    override fun saveUser(user: DataBaseUser) {
        repository?.save(user)
    }

    override fun updateUser(id: Int, userName: String, userPassword: String, userEMail: String, customerId: Int) {
        var dbUser = repository?.getOne(id)!!
        dbUser.userName = userName
        dbUser.userPassword = userPassword
        dbUser.userEMail = userEMail
        dbUser.customerid = customerId

        repository?.save(dbUser)
    }

    override fun deleteUser(user: DataBaseUser) {
        repository?.delete(user)
    }

    override fun getAll(): List<DataBaseUser> {
        return repository?.findAll()!!
    }
}