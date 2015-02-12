package com.elega9t.sample.springboot.controller;

import com.elega9t.sample.springboot.Application;
import com.elega9t.sample.springboot.ErrorCode;
import com.elega9t.sample.springboot.TestUtilities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@IntegrationTest({"server.port=0"})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorityControllerIT {

    @Value("${local.server.port}")
    private int port;

    private RestTemplate template;
    private URL base;

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
        this.base = new URL("http://localhost:" + port + "/api/");
    }

    @Test
    public void canGetAllAuthorities() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "authority", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(TestUtilities.resourceAsString("/json/authorities.json")));
    }

    @Test
    public void canGetAuthorityById() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "authority/1", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(TestUtilities.resourceArrayObjectAsString("/json/authorities.json", "id", "1")));
    }

    @Test
    public void returns404_whenAuthorityNotFound() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "authority/5", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertThat(response.getBody(),
                containsString(String.format("%s: %s", ErrorCode.AUTHORITY_NOT_FOUND.errorCode, ErrorCode.AUTHORITY_NOT_FOUND.message)));
    }

}
