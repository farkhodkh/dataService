package ru.laundromat.laundromatdataservice.security

import org.springframework.security.core.context.SecurityContextHolder

class UserAccessChecker {
    companion object {
        fun isAdminRoleGranted(): Boolean {
            val authentication = SecurityContextHolder.getContext().authentication

            if (authentication != null) {
                return authentication.authorities.find { it.toString().equals("ADMIN") } != null
            } else {
                return false
            }
        }
    }
}