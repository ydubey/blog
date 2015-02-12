package com.elega9t.sample.springboot.controller;

import com.elega9t.sample.springboot.model.view.UserView;
import com.elega9t.sample.springboot.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "User", description = "User CRUD Operations")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "/api/user", method = RequestMethod.GET)
  @ApiOperation(value = "Returns a list of all users", httpMethod = "GET")
  public List<UserView> getAll() {
    return userService.getAll();
  }

  @RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET)
  @ApiOperation(value = "Returns a user based on his id", httpMethod = "GET")
  @ApiResponses(value = {
      @ApiResponse(code = 404, message = "When requested user is not found.")
  })
  public UserView getById(@PathVariable("id") Long id) {
    return userService.getById(id);
  }

}
