package ru.laundromat.laundromatdataservice.data.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.laundromat.laundromatdataservice.data.entities.Customer
import ru.laundromat.laundromatdataservice.data.services.CustomersService
import ru.laundromat.laundromatdataservice.security.UserAccessChecker
import java.util.*

@Controller
class CustomersController {
    lateinit var service: CustomersService

    @Autowired
    fun setUserService(service: CustomersService) {
        this.service = service
    }

    @GetMapping("/customers")
    fun usersList(module: Model): String {
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }

        var customers = service.getAll()
        module.addAttribute("customers", customers)
        return "customers/list"
    }

    @GetMapping("/newCustomer")
    fun newUser(): String {
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }

        return "customers/new"
    }

    @PostMapping("/saveCustomer")
    fun saveUser(
            @RequestParam name: String,
            @RequestParam phone: String,
            @RequestParam email: String,
            @RequestParam address: String
    ): String {
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }

        var user = Customer(name, phone, email, address, Date())
        service.saveUser(user)
        return "redirect:/customers"
    }

    @GetMapping("/deleteCustomer/{id}")
    fun deleteDataBaseUser(@PathVariable id: Int): String{
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }

        service.deleteUser(service.getById(id))
        return "redirect:/customers"
    }

    @GetMapping("/editCustomer/{id}")
    fun editUser(@PathVariable id: Int, model: Model): String {
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }

        val customer = service.getById(id)
        model.addAttribute("customer", customer)
        return "customers/edit"
    }

    @PostMapping("/updateCustomer")
    fun saveNote(@RequestParam id: Int,
                 @RequestParam name: String,
                 @RequestParam phone: String,
                 @RequestParam email: String,
                 @RequestParam address: String
//                 @RequestParam(value = "done", required = false) done: Boolean
    ): String {
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }

        service.updateUser(id, name, phone, email, address)
        return "redirect:/customers"
    }
}