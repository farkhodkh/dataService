package ru.laundromat.laundromatdataservice.common

import ru.laundromat.laundromatdataservice.data.entities.DataBaseUser

enum class Constants(val value: String) {
    HomeUrl("http://localhost:3143/"),
    ReportsUrl(HomeUrl.value + "/reports"),
    WasherStatusUrl(HomeUrl.value + "/washerstatus"),
    ServerPort("3143");
    var user: DataBaseUser? = null
}