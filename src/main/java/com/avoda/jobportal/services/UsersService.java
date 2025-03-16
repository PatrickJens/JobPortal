package com.avoda.jobportal.services;


import com.avoda.jobportal.entity.Users;
import com.avoda.jobportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    //Save
    public Users addNew(Users users){
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        return usersRepository.save(users);
    }

    //Check if email already exists
    public Optional<Users> getUserByEmail(String email){
        return usersRepository.findByEmail(email);
    }

}
