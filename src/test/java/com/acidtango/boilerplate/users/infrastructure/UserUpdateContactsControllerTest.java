package com.acidtango.boilerplate.users.infrastructure;

import com.acidtango.boilerplate.TestInjectionConfiguration;
import com.acidtango.boilerplate.UserFixtures;
import com.acidtango.boilerplate.shared.domain.DomainErrorCode;
import com.acidtango.boilerplate.users.domain.primitives.UserPrimitives;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.ContactRequestDTO;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.CreateUserRequestDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestInjectionConfiguration.class)
public class UserUpdateContactsControllerTest {

    public static final UserPrimitives PEDRO_PRIMITIVES = UserFixtures.pedroPrimitives;
    private final String FIRST_CONTACT_NAME = "Manuel";
    private final String SECOND_CONTACT_NAME = "Julio";
    private final List<ContactRequestDTO> contacts = new ArrayList<>(
            List.of(
                    new ContactRequestDTO(FIRST_CONTACT_NAME, "Martín", "+34123456789"),
                    new ContactRequestDTO(SECOND_CONTACT_NAME, "Pérez", "+52123456789")
            ));
    @LocalServerPort
    int port;
    private String createdUserUUID;

    @BeforeEach
    public void createUser() {
        CreateUserRequestDTO request = new CreateUserRequestDTO(
                PEDRO_PRIMITIVES.fullName().name(),
                PEDRO_PRIMITIVES.fullName().surname(),
                UserFixtures.pedro().getPhoneNumber().toString(),
                PEDRO_PRIMITIVES.contacts().stream().map(e -> new ContactRequestDTO(
                        e.fullName().name(),
                        e.fullName().surname(),
                        e.phoneNumber().prefix() + e.phoneNumber().digits()
                )).toList()
        );

        createdUserUUID = RestAssured.given()
                .basePath("/api/v1")
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(request)
                .post("/users")
                .then().statusCode(HttpStatus.CREATED.value()).extract().body().path("userId");

    }

    @Test
    @DisplayName("Updates a a user correctly")
    public void getUser() {

        ValidatableResponse response = RestAssured.given()
                .basePath("/api/v1")
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(contacts)
                .put("/users/" + createdUserUUID + "/contacts")
                .then();

        response.statusCode(HttpStatus.OK.value());
        response.body("contacts.size()", equalTo(2));
        response.body("contacts.get(0).name", equalTo(FIRST_CONTACT_NAME));
        response.body("contacts.get(1).name", equalTo(SECOND_CONTACT_NAME));
    }

    @Test
    @DisplayName("Fails if user is Not Found")
    public void userNotFound() {
        final String FAKE_UUID = "29a18c73-2574-4d18-b48b-5e036e423f7b";
        ValidatableResponse response = RestAssured.given()
                .basePath("/api/v1")
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(contacts)
                .put("/users/" + FAKE_UUID + "/contacts")
                .then();

        response.statusCode(HttpStatus.NOT_FOUND.value());
        response.body("domainErrorCode", equalTo(DomainErrorCode.USER_NOT_FOUND.toString()));
    }

    @Test
    @DisplayName("Fails if phone is not allowed")
    public void contactPhoneNotValid() {
        final String NOT_VALID_PHONE = "+56123456789";
        ValidatableResponse response = RestAssured.given()
                .basePath("/api/v1")
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(contacts.stream().map(e -> new ContactRequestDTO(
                        e.name(),
                        e.surname(),
                        NOT_VALID_PHONE)
                ))
                .put("/users/" + createdUserUUID + "/contacts")
                .then();

        response.statusCode(HttpStatus.BAD_REQUEST.value());
        response.body("domainErrorCode", equalTo(DomainErrorCode.NOT_ALLOWED_PHONE_ERROR.toString()));
    }
}
