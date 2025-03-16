package com.avoda.jobportal.controller;

import com.avoda.jobportal.entity.Users;
import com.avoda.jobportal.entity.UsersType;
import com.avoda.jobportal.services.UsersService;
import com.avoda.jobportal.services.UsersTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    private final UsersTypeService  usersTypeService;
    private final UsersService      usersService;

    //Dependency Inject
    @Autowired
    public UsersController(UsersTypeService usersTypeService, UsersService usersService){
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String register(Model model){
        List<UsersType> usersTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegistration( @Valid Users users, Model model ){
        System.out.println("User:: "+users);

        Optional<Users> optionalUsers = usersService.getUserByEmail(users.getEmail());

        if( optionalUsers.isPresent()){
            List<UsersType> usersTypes = usersTypeService.getAll();
            model.addAttribute("getAllTypes", usersTypes);
            model.addAttribute("user", new Users());
            model.addAttribute("error", "Email aleady exists, try to login or register with another email");
            return "register";
        }

        usersService.addNew(users);
        return "dashboard";
    }


}
