package com.elega9t.sample.springboot.controller;

import com.elega9t.sample.springboot.ErrorCode;
import com.elega9t.sample.springboot.SampleApplicationException;
import com.elega9t.sample.springboot.TestUtilities;
import com.elega9t.sample.springboot.model.view.AuthorityView;
import com.elega9t.sample.springboot.service.AuthorityService;
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
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
public class AuthorityControllerTest {

  private MockMvc mvc;

  private AuthorityService mockAuthorityService;

  @Before
  public void setUp() throws Exception {
    mockAuthorityService = mock(AuthorityService.class);
    mvc = MockMvcBuilders.standaloneSetup(new AuthorityController(mockAuthorityService)).build();
  }

  @Test
  public void canGetAllAuthoritys() throws Exception {
    List<AuthorityView>
        expected = TestUtilities
        .resourceAsObject("/json/authorities.json", new TypeReference<List<AuthorityView>>() {
        });
    when(mockAuthorityService.getAll()).thenReturn(expected);

    mvc.perform(MockMvcRequestBuilders.get("/api/authority").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(is(TestUtilities.writeValueAsString(expected))))
        .andExpect(content().string(matchesJsonSchemaInClasspath("schema/authorities.json")));
  }

  @Test
  public void canGetAuthorityById() throws Exception {
    Long id = 100L;
    AuthorityView expected = TestUtilities.resourceArrayObject("/json/authorities.json", "id", "2", AuthorityView.class);
    when(mockAuthorityService.getById(id)).thenReturn(expected);

    mvc.perform(MockMvcRequestBuilders.get("/api/authority/" + id).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(is(TestUtilities.writeValueAsString(expected))))
        .andExpect(content().string(matchesJsonSchemaInClasspath("schema/authority.json")));

    verify(mockAuthorityService).getById(id);
  }

  @Test
  public void throwsException_whenAuthorityNotFound() throws Exception {
    doThrow(new SampleApplicationException(ErrorCode.AUTHORITY_NOT_FOUND)).when(mockAuthorityService).getById(anyLong());

    try {
      mvc.perform(MockMvcRequestBuilders.get("/api/authority/7").accept(MediaType.APPLICATION_JSON));
      fail("Exception was expected!");
    } catch(NestedServletException e) {
      Throwable cause = e.getCause();
      assertThat(cause, is(instanceOf(SampleApplicationException.class)));
      assertThat(((SampleApplicationException)cause).getErrorCode(), is(ErrorCode.AUTHORITY_NOT_FOUND.errorCode));
      verify(mockAuthorityService).getById(7L);
    }
  }

}