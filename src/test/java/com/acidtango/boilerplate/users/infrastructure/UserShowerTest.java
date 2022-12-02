package com.acidtango.boilerplate.users.infrastructure;

import com.acidtango.boilerplate.TestInjectionConfiguration;
import com.acidtango.boilerplate.UserFixtures;
import com.acidtango.boilerplate.shared.domain.DomainErrorCode;
import com.acidtango.boilerplate.users.domain.primitives.ContactPrimitives;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.ContactRequestDTO;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.CreateUserRequestDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestInjectionConfiguration.class)
public class UserShowerTest {


    public static final String NAME = UserFixtures.pedroPrimitives.fullName().name();
    public static final String SURNAME = UserFixtures.pedroPrimitives.fullName().surname();
    public static final String PHONE_NUMBER =
            UserFixtures.pedroPrimitives.phoneNumber().prefix() + UserFixtures.pedroPrimitives.phoneNumber().digits();
    public static final List<ContactPrimitives> CONTACTS = UserFixtures.pedroPrimitives.contacts();
    @LocalServerPort
    int port;

    private String createdUserUUID;

    @BeforeEach
    public void createUser() {
        CreateUserRequestDTO request = new CreateUserRequestDTO(
                NAME,
                SURNAME,
                PHONE_NUMBER,
                CONTACTS.stream().map(e -> new ContactRequestDTO(
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
    public void get_user() {

        ValidatableResponse response = RestAssured.given()
                .basePath("/api/v1")
                .port(this.port)
                .contentType(ContentType.JSON)
                .get("/users/" + createdUserUUID)
                .then();
        response.statusCode(HttpStatus.OK.value());
        response.body("name", equalTo(NAME));
        response.body("surname", equalTo(SURNAME));
        response.body("phoneNumber", equalTo(PHONE_NUMBER));
        String contacts =
                response.extract().body().path("contacts").toString();
        response.body("contacts.size()", equalTo(1));

    }

    @Test
    public void fails_if_user_not_found() {
        final String FAKE_UUID = "29a18c73-2574-4d18-b48b-5e036e423f7b";
        ValidatableResponse response = RestAssured.given()
                .basePath("/api/v1")
                .port(this.port)
                .contentType(ContentType.JSON)
                .get("/users/" + FAKE_UUID)
                .then();

        response.statusCode(HttpStatus.NOT_FOUND.value());
        response.body("domainErrorCode", equalTo(DomainErrorCode.USER_NOT_FOUND.toString()));


    }


}
