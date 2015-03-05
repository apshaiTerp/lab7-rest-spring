package com.ac.cs5551.lab7.test;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.ac.cs5551.lab7.rest.LoginController;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

/**
 * @author ac010168
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-mvc-configuration.xml")
@WebAppConfiguration
public class LoginControllerTest {

  @Autowired
  private WebApplicationContext wac;

  @Before
  public void setup() {
    //NOOP
  }
  
  @After
  public void tearDown() {
    //NOOP
  }
  
  @Test
  public void testLoginService() {
    RestAssuredMockMvc.standaloneSetup(new LoginController());
    
    given().
      param("name", "ASE").
      param("pass", "123").
    when().
      get("/login").
    then().
      assertThat().
        statusCode(200).
        body("message", equalTo("Welcome, ASE!"));

    given().
      param("name", "apshaiTerp").
      param("pass", "apshai2xander").
    when().
      get("/login").
    then().
      assertThat().
        statusCode(200).
        body("message", equalTo("Welcome, apshaiTerp!"));
  
    given().
      param("name", "Adam").
      param("pass", "apshai1").
    when().
      get("/login").
    then().
      assertThat().
        statusCode(200).
        body("message", equalTo("Welcome, Adam!"));
  
    given().
      param("name", "Nobody").
      param("pass", "nothing").
    when().
      get("/login").
    then().
      assertThat().
        statusCode(200).
        body("message", equalTo("Error:  User Not Found!"));

    given().
      param("name", "apshaiTerp").
      param("pass", "notmypassword").
    when().
      get("/login").
    then().
      assertThat().
        statusCode(200).
        body("message", equalTo("Error:  Password Incorrect!"));
  }
}
