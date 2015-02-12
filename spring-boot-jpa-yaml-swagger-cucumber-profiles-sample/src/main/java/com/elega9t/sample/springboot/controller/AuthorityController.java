package com.elega9t.sample.springboot.controller;

import com.elega9t.sample.springboot.model.view.AuthorityView;
import com.elega9t.sample.springboot.service.AuthorityService;
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
@Api(value = "Authority", description = "Authority CRUD Operations")
public class AuthorityController {

  private AuthorityService authorityService;

  @Autowired
  public AuthorityController(AuthorityService authorityService) {
    this.authorityService = authorityService;
  }

  @RequestMapping(value = "/api/authority", method = RequestMethod.GET)
  @ApiOperation(value = "Returns a list of all authorities", httpMethod = "GET")
  public List<AuthorityView> getAll() {
    return authorityService.getAll();
  }

  @RequestMapping(value = "/api/authority/{id}", method = RequestMethod.GET)
  @ApiOperation(value = "Returns an authority based on its id", httpMethod = "GET")
  @ApiResponses(value = {
      @ApiResponse(code = 404, message = "When requested authority is not found.")
  })
  public AuthorityView getById(@PathVariable("id") Long id) {
    return authorityService.getById(id);
  }

  @RequestMapping(value = "/api/authority/{id}", method = RequestMethod.DELETE)
  @ApiOperation(value = "Deletes an authority based on its id", httpMethod = "DELETE")
  @ApiResponses(value = {
      @ApiResponse(code = 404, message = "When requested authority is not found.")
  })
  public void deleteById(@PathVariable("id") Long id) {
    authorityService.deleteById(id);
  }

}
