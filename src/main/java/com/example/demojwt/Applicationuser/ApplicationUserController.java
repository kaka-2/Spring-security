package com.example.demojwt.Applicationuser;

import com.example.demojwt.Applicationuser.domain.ApplicationUser;
import com.example.demojwt.Applicationuser.model.RegisterUser;
import com.example.demojwt.Applicationuser.service.ApplicationUserService;
import com.example.demojwt.Utils.ApplicationUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ApplicationUserController {

    @Autowired
    private ApplicationUserService userService;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public ResponseEntity<ApplicationUserResponse> getUsers() {
        List<ApplicationUser> users = userService.getUsers();
        ApplicationUserResponse res = new ApplicationUserResponse();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Users fetched successfully");
        res.setData(users);

        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}",method = RequestMethod.GET)
    public ResponseEntity<ApplicationUserResponse> getUser(@PathVariable Long userId) {
        Optional<ApplicationUser> opt = userService.getUser(userId);
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
    public ResponseEntity<ApplicationUserResponse> addUser(@Valid @RequestBody RegisterUser user) {

        ApplicationUser user1 = userService.addUsers(user);
        ApplicationUserResponse res = new ApplicationUserResponse();
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("User added successfully");
        res.setData(user1);

        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }
}
