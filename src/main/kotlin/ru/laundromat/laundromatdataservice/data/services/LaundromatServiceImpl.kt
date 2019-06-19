package ru.laundromat.laundromatdataservice.data.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.laundromat.laundromatdataservice.data.entities.Laundromat
import ru.laundromat.laundromatdataservice.data.repository.LaundromatsRepository

@Service
class LaundromatServiceImpl: LaundromatService {

    override fun updateLaundromat(id: Int, number: Int, address: String, customerId: Int, phone: String) {
        var laundromat = repository?.getOne(id)!! as Laundromat
        laundromat.number = number
        laundromat.address = address
        laundromat.customerId = customerId
        laundromat.phone = phone

        repository?.save(laundromat)
    }

    private var repository: LaundromatsRepository? = null

    @Autowired
    fun setProductRepository(repository: LaundromatsRepository) {
        this.repository = repository
    }


    override fun getById(id: Int): Laundromat {
        return repository?.getOne(id)!!
    }

    override fun saveLaundromat(laundromat: Laundromat) {
        repository?.save(laundromat)
    }


    override fun deleteLaundromat(laundromat: Laundromat) {
        repository?.delete(laundromat)
    }

    override fun getAll(): List<Laundromat> {
        return repository?.findAll()!!
    }

    override fun getByCustomerId(customerId: Int): List<Laundromat> {
        return repository?.getByCustomerId(customerId)!!
    }
}
