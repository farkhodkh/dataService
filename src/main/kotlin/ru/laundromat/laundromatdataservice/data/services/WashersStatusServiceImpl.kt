package ru.laundromat.laundromatdataservice.data.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.laundromat.laundromatdataservice.data.entities.WashersStatus
import ru.laundromat.laundromatdataservice.data.repository.WashersStatusRepository

@Service
class WashersStatusServiceImpl: WashersStatusService {

    lateinit var repository: WashersStatusRepository

    @Autowired
    fun setProductRepository(repository: WashersStatusRepository) {
        this.repository = repository
    }

    override fun getById(id: Int): WashersStatus = repository.getOne(id)

    override fun saveWasherStatus(whaserStatus: WashersStatus) {
        repository.save(whaserStatus)
    }

    override fun deleteWasherStatus(whaserStatus: WashersStatus) = repository.delete(whaserStatus)

    override fun getAll(): List<WashersStatus> = repository.findAll()

    override fun getByCustomerId(customerId: Int): List<WashersStatus> = repository.findAllBycustomerId(customerId)

    override fun findAllCustomerLaundromatWasherStatus(customerId: Int, laundromatId: Int): List<WashersStatus> =
        repository.findAllCustomerLaundromatWasherStatus(customerId, laundromatId)

    override fun getByCustomerLaundromatId(laundromatId: Int): List<WashersStatus> = repository.findAllByLaundromatId(laundromatId)
}