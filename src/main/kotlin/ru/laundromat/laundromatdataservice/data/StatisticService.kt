package ru.laundromat.laundromatdataservice.data

import org.springframework.data.jpa.repository.JpaRepository
import ru.laundromat.laundromatdataservice.domain.StatInfo

interface StatisticRepository: JpaRepository<StatInfo, Long>