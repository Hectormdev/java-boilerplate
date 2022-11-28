package com.acidtango.boilerplate.users.infrastructure;

import com.acidtango.boilerplate.shared.domain.DomainErrorCode;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.CreateUserRequestDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserCreatorControllerTest {

    private final String FAKE_NAME = "Pedro";
    private final String FAKE_SURNAME = "Martín";
    private final String FAKE_PHONE_NUMBER = "+34666827783";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    int port;

    @Test
    @DisplayName("Creates a new user correctly")
    public void createsUser()  {
        RestAssured.given()
                .basePath("/api/v1")
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(new CreateUserRequestDTO(FAKE_NAME,FAKE_SURNAME,FAKE_PHONE_NUMBER))
                .post("/users")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("name",equalTo(FAKE_NAME))
                .body("surname",equalTo(FAKE_SURNAME))
                .body("phoneNumber",equalTo(FAKE_PHONE_NUMBER))
                .body("contacts",empty());
    }

    @Test
    @DisplayName("Fails if phone is not correct")
    public void failsForDTO()  {
        RestAssured.given()
                .basePath("/api/v1")
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(new CreateUserRequestDTO(FAKE_NAME,FAKE_SURNAME,"+301234456"))
                .post("/users")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("domainErrorCode",equalTo(DomainErrorCode.NOT_ALLOWED_PHONE_ERROR.toString()));

    }


}
