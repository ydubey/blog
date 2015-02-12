package com.elega9t.sample.springboot.controller;

import com.elega9t.sample.springboot.ErrorCode;
import com.elega9t.sample.springboot.SampleApplicationException;
import com.elega9t.sample.springboot.TestUtilities;
import com.elega9t.sample.springboot.model.view.UserView;
import com.elega9t.sample.springboot.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.List;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
public class UserControllerTest {

  private MockMvc mvc;

  private UserService mockUserService;

  @Before
  public void setUp() throws Exception {
    mockUserService = mock(UserService.class);
    mvc = MockMvcBuilders.standaloneSetup(new UserController(mockUserService)).build();
  }

  @Test
  public void canGetAllUsers() throws Exception {
    List<UserView>
        expected = TestUtilities
        .resourceAsObject("/json/users.json", new TypeReference<List<UserView>>() {
        });
    when(mockUserService.getAll()).thenReturn(expected);

    mvc.perform(MockMvcRequestBuilders.get("/api/user").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(is(TestUtilities.writeValueAsString(expected))))
        .andExpect(content().string(matchesJsonSchemaInClasspath("schema/users.json")));
  }

  @Test
  public void canGetUserById() throws Exception {
    Long id = 100L;
    UserView expected = TestUtilities.resourceArrayObject("/json/users.json", "id", "2", UserView.class);
    when(mockUserService.getById(id)).thenReturn(expected);

    mvc.perform(MockMvcRequestBuilders.get("/api/user/" + id).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(is(TestUtilities.writeValueAsString(expected))))
        .andExpect(content().string(matchesJsonSchemaInClasspath("schema/user.json")));

    verify(mockUserService).getById(id);
  }

  @Test
  public void throwsException_whenUserNotFound() throws Exception {
    doThrow(new SampleApplicationException(ErrorCode.USER_NOT_FOUND)).when(mockUserService).getById(anyLong());

    try {
      mvc.perform(MockMvcRequestBuilders.get("/api/user/7").accept(MediaType.APPLICATION_JSON));
      fail("Exception was expected!");
    } catch(NestedServletException e) {
      Throwable cause = e.getCause();
      assertThat(cause, is(instanceOf(SampleApplicationException.class)));
      assertThat(((SampleApplicationException)cause).getErrorCode(), is(ErrorCode.USER_NOT_FOUND.errorCode));
      verify(mockUserService).getById(7L);
    }
  }

}