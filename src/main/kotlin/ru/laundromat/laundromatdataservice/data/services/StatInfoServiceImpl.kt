package ru.laundromat.laundromatdataservice.data.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.laundromat.laundromatdataservice.data.entities.StatInfo
import ru.laundromat.laundromatdataservice.data.repository.StatInfoRepository
import ru.laundromat.laundromatdataservice.data.repository.UsersAndCustomersRepository

@Service
class StatInfoServiceImpl: StatInfoService {

    lateinit var repository: StatInfoRepository

    @Autowired
    fun setProductRepository(repository: StatInfoRepository) {
        this.repository = repository
    }


    override fun getById(id: Int): StatInfo {
        return repository.getOne(id)
    }

    override fun saveStatInfo(statInfo: StatInfo) {
        repository.save(statInfo)
    }

    override fun updateStatInfo(id: Int, name: String, phone: String, email: String, address: String) {
        //
    }

    override fun deleteStatInfo(statInfo: StatInfo) {
        repository.deleteById(statInfo.Id)
    }

    override fun getAll(): List<StatInfo> {
        return repository.findAll()
    }

    override fun getBycustomerId(customerId: Int): List<StatInfo> {
        return repository.getBycustomerId(customerId)
    }
}