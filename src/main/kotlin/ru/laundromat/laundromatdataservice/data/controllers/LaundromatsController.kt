package ru.laundromat.laundromatdataservice.data.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.laundromat.laundromatdataservice.data.entities.Laundromat
import ru.laundromat.laundromatdataservice.data.services.CustomersService
import ru.laundromat.laundromatdataservice.data.services.LaundromatService
import ru.laundromat.laundromatdataservice.data.services.LaundromatsWithCustomersService
import ru.laundromat.laundromatdataservice.security.UserAccessChecker
import java.util.*


@Controller
class LaundromatsController {
    lateinit var service: LaundromatService
    lateinit var customersService: CustomersService
    lateinit var laundromatsAndCustomersService: LaundromatsWithCustomersService

    @Autowired
    fun setUserService(service: LaundromatService) {
        this.service = service
    }

    @Autowired
    fun setUserService(service: CustomersService) {
        this.customersService = service
    }

    @Autowired
    fun setUserService(service: LaundromatsWithCustomersService) {
        this.laundromatsAndCustomersService = service
    }


    @GetMapping("/laundromats")
    fun laundromatsList(module: Model): String {
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }
//        var laundromats = service.getAll()
        var laundromats = laundromatsAndCustomersService.getAll()
        module.addAttribute("laundromats", laundromats)
        return "laundromats/list"
    }

    @GetMapping("/newLaundromat")
    fun newLaundromat(module: Model): String {
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }

        var customers = customersService.getAll()
        module.addAttribute("customers", customers)

        return "Laundromats/new"
    }

    @PostMapping("/saveLaundromat")
    fun saveLaundromat(
            @RequestParam number: Int,
            @RequestParam address: String,
            @RequestParam phone: String,
            @RequestParam customerId: Int
    ): String {
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }

        var laundromat = Laundromat(number, customerId, address, phone, Date())
        service.saveLaundromat(laundromat)
        return "redirect:/laundromats"
    }

    @GetMapping("/deleteLaundromat/{id}")
    fun deleteDataBaseUser(@PathVariable id: Int): String{
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }

        service.deleteLaundromat(service.getById(id))
        return "redirect:/laundromats"
    }

    @GetMapping("/editLaundromat/{id}")
    fun editUser(@PathVariable id: Int, model: Model): String {
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }
        val laundromat = service.getById(id)
        var customers = customersService.getAll()
        model.addAttribute("customers", customers)
        model.addAttribute("laundromat", laundromat)
        return "laundromats/edit"
    }

    @PostMapping("/updateLaundromat")
    fun saveNote(@RequestParam id: Int,
                 @RequestParam number: Int,
                 @RequestParam address: String,
                 @RequestParam phone: String,
                 @RequestParam customerId: Int
//                 @RequestParam(value = "done", required = false) done: Boolean
    ): String {
        if (!UserAccessChecker.isAdminRoleGranted()) {
            return "redirect:/error"
        }

        service.updateLaundromat(id, number, address, customerId, phone)
        return "redirect:/customers"
    }

}