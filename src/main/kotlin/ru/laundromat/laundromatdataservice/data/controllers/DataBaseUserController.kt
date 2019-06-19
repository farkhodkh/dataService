package ru.laundromat.laundromatdataservice.data.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.laundromat.laundromatdataservice.data.entities.DataBaseUser
import ru.laundromat.laundromatdataservice.data.services.CustomersService
import ru.laundromat.laundromatdataservice.data.services.DataBaseUsersService
import ru.laundromat.laundromatdataservice.data.services.UsersAndCustomersService
import ru.laundromat.laundromatdataservice.security.UserAccessChecker
import java.util.*

@Controller
class DataBaseUserController {
    lateinit var service: DataBaseUsersService
    lateinit var customersService: CustomersService
    lateinit var usersCustomersService: UsersAndCustomersService

    @Autowired
    fun setUserService(service: CustomersService) {
        this.customersService = service
    }

    @Autowired
    fun setBaseUsersService(service: DataBaseUsersService) {
        this.service = service
    }

    @Autowired
    fun setUsersAndCustomersService(service: UsersAndCustomersService) {
        this.usersCustomersService = service
    }


    @GetMapping("/users")
    fun usersList(module: Model): String {
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }

        var users = usersCustomersService.getAll()

        module.addAttribute("users", users)
        return "users/list"
    }

    @GetMapping("/newUser")
    fun newUser(module: Model): String {
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }

        var customers = customersService.getAll()
        module.addAttribute("customers", customers)
        return "users/new"
    }

    @PostMapping("/saveUser")
    fun saveUser(
            @RequestParam userName: String,
            @RequestParam userPassword: String,
            @RequestParam userEMail: String,
            @RequestParam customerId: Int
    ): String {
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }

        var user = DataBaseUser(userName, userPassword, Date(), userEMail, customerId)
        service.saveUser(user)
        return "redirect:/users"
    }

    @GetMapping("/deleteUser/{id}")
    fun deleteDataBaseUser(@PathVariable id: Int): String {
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }

        service.deleteUser(service.getById(id))
        return "redirect:/users"
    }

    @GetMapping("/editUser/{id}")
    fun editUser(@PathVariable id: Int, model: Model): String {

        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }

        var customers = customersService.getAll()
        val user = service.getById(id)

        model.addAttribute("customers", customers)
        model.addAttribute("user", user)
        return "users/edit"
    }

    @GetMapping("/grantRoleForUser")
    fun grantRoleForUser(@RequestParam id: Int){
        if (!UserAccessChecker.isAdminRoleGranted()) {
            //return "redirect:/error"
        }else {
            var user = service.getById(id)
        }
    }

    @PostMapping("/updateUser")
    fun saveNote(@RequestParam id: Int,
                 @RequestParam userName: String,
                 @RequestParam userPassword: String,
                 @RequestParam userEMail: String,
                 @RequestParam customerId: Int
//                 @RequestParam(value = "done", required = false) done: Boolean
    ): String {
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }

        service.updateUser(id, userName, userPassword, userEMail, customerId)
        return "redirect:/users"
    }

}