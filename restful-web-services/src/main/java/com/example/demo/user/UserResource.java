package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// This class is often known as the controller or resource class
@RestController

public class UserResource {

    @Autowired
    // What Spring does is to create an instance of the UserDaoService and autowire it here.
    // this is somewhat similar but also different from instantiating an instance of the class by ourselves using 'New'.

    private UserDaoService userDaoService;

//    private UserDaoService userDaoService = new UserDaoService();

//    Retrieve all users endpoint
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

//    Retrieve one user by id endpoint
    @GetMapping("/users/{id}")
    public User findUserById (@PathVariable int id) {

        User user = userDaoService.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id: " + id);

        return  userDaoService.findOne(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User newUser = userDaoService.save(user);

        /* this step below gets the endpoint from current request, which is in the post mapping - "/users"
        , append the /{id} to the current request endpoint, and then replaces {id} with the id of the
        new user.

         */
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()  // at this point, "/users" is returned
                        .path("/{id}") // at this point "/users/{id}" is returned
                                .buildAndExpand(newUser.getId()).toUri(); // at this point "/users/4" is returned, which is used as a location is the next step

        /* this Spring feature - ResponseEntity.created(location).build()
            allows us to return an appropriate http response body and status code to the client
            http status returned = 201
            also returns a location (uri) of the newly created resource under headers.location
        */
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = userDaoService.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id: " + id);

        userDaoService.deleteById(id);

    }

}
