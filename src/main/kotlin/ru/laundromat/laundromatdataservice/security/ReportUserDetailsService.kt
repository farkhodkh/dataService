package ru.laundromat.laundromatdataservice.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories


@Service
class ReportUserDetailsService : UserDetailsService {
    var encoder = passwordEncoder()
    var encoder2 = PasswordEncoderFactories.createDelegatingPasswordEncoder()
    //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    override fun loadUserByUsername(username: String?): UserDetails {
//        throw UsernameNotFoundException("User Name $username Not Found");

//        val userDetails: UserDetails  =  org.springframework.security
//                .core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                enabled,
//                accountNonExpired,
//                credentialsNonExpired,
//                accountNonLocked,
//                getAuthorities("ADMIN"))

        return org.springframework.security.core.userdetails.User(
                "admin",
                encoder2.encode("123456"),
                getGrantedAuthorities("admin"))
    }

    private fun getGrantedAuthorities(user: String): Collection<GrantedAuthority> {

        var grantedAuthority: Collection<GrantedAuthority> = arrayListOf(SimpleGrantedAuthority("ADMIN"))

//        if (user.equals("admin")) {
//            grantedAuthority.to(SimpleGrantedAuthority("ADMIN"))
//        }
        return grantedAuthority
    }

    fun passwordEncoder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder();
    }
}