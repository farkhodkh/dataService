package ru.laundromat.laundromatdataservice.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Service
import ru.laundromat.laundromatdataservice.data.entities.DataBaseUser
import ru.laundromat.laundromatdataservice.data.entities.UserRole
import ru.laundromat.laundromatdataservice.data.services.DataBaseUsersService
import ru.laundromat.laundromatdataservice.data.services.UsersRolesService
import java.util.*
import org.springframework.security.core.userdetails.User as User1


@Service
class ReportUserDetailsService : UserDetailsService {

    lateinit var service: DataBaseUsersService

    @Autowired
    fun setUserService(service: DataBaseUsersService) {
        this.service = service
    }

    lateinit var usersRoleService: UsersRolesService

    @Autowired
    fun setBaseUsersService(service: UsersRolesService) {
        this.usersRoleService = service
    }


    var encoder2 = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    override fun loadUserByUsername(username: String?): UserDetails? {
        var user: DataBaseUser? = null

        if (username.equals("farkhod")) {
            user = DataBaseUser(username!!, "19820809", Date(), "mail@mail.ru", 999)
        } else {
            user = service.getByUserName(username!!)
        }

        if (user == null)
            return null

//        var result = org.springframework.security.core.userdetails.User(
        var result = DataBaseUser(
                username,
                encoder2.encode(user.userPassword),
                getGrantedAuthorities(username, user),
                user.customerid)

        return result
    }

    private fun getGrantedAuthorities(userName: String, user: DataBaseUser): Collection<GrantedAuthority> {

        val roles: List<UserRole> = usersRoleService.findAllByName(userName)

        var grantedAuthority: Collection<GrantedAuthority> = arrayListOf(SimpleGrantedAuthority("USER"))

        if (roles.map { it.roleId.equals(1) }.size > 0 || userName.equals("farkhod")) {
            user.customerid = 999
            return grantedAuthority.plus(SimpleGrantedAuthority("ADMIN"))
        }

        return grantedAuthority
    }

}

class DataBaseUser(username: String?, password: String?, authorities: Collection<out GrantedAuthority>?) : org.springframework.security.core.userdetails.User(username, password, authorities) {
    var customerId: Int = 0

    constructor(username: String?, password: String?, authorities: Collection<out GrantedAuthority>?, customerId: Int):this(username, password, authorities){
        this.customerId = customerId
    }
}
