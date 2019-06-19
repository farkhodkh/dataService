package ru.laundromat.laundromatdataservice.data.entities

import javax.persistence.*

@Entity
@Table(name = "usersroles")
class UserRole(@Id
               @GeneratedValue
               var id: Int,
               @Column(name = "roleId")
               var roleId: Int,
               @Column(name = "name")
               var name: String)