package com.example.demojwt.Applicationuser;

import com.example.demojwt.Applicationuser.domain.ApplicationUser;
import com.example.demojwt.Applicationuser.repository.ApplicationUserRepository;
import com.example.demojwt.Utils.ApplicationUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ApplicationUserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ApplicationUserRepository userRepository;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public ResponseEntity<ApplicationUserResponse> getUsers() {
        List<ApplicationUser> users = userRepository.findAll();
        ApplicationUserResponse res = new ApplicationUserResponse();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Users fetched successfully");
        res.setData(users);

        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}",method = RequestMethod.GET)
    public ResponseEntity<ApplicationUserResponse> getUser(@PathVariable Long userId) {
        Optional<ApplicationUser> opt = userRepository.findById(userId);
        ApplicationUserResponse res = new ApplicationUserResponse();

        if (opt.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("User fetched successfully");
            res.setData(opt.get());
        } else {
            res.setStatusCode(HttpStatus.NOT_FOUND.value());
            res.setMessage("User not found");
            res.setData(opt.get());
        }

        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public ResponseEntity<ApplicationUserResponse> addUser(@RequestBody ApplicationUser user) {
        ApplicationUser user1 = userRepository.save(user);
        ApplicationUserResponse res = new ApplicationUserResponse();
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("User added successfully");
        res.setData(user1);

        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }
}
