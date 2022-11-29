package com.acidtango.boilerplate.users.infrastructure;

import com.acidtango.boilerplate.TestInjectionConfiguration;
import com.acidtango.boilerplate.UserFixtures;
import com.acidtango.boilerplate.shared.domain.DomainErrorCode;
import com.acidtango.boilerplate.users.domain.primitives.ContactPrimitives;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.ContactRequestDTO;
import com.acidtango.boilerplate.users.infrastructure.rest.dtos.CreateUserRequestDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = TestInjectionConfiguration.class)
public class UserCreatorControllerTest {


    public static final String NAME = UserFixtures.pedroPrimitives.fullName().name();
    public static final String SURNAME = UserFixtures.pedroPrimitives.fullName().surname();
    public static final String PHONE_NUMBER =
            UserFixtures.pedroPrimitives.phoneNumber().prefix() + UserFixtures.pedroPrimitives.phoneNumber().number();
    public static final List<ContactPrimitives> CONTACTS = UserFixtures.pedroPrimitives.contacts();

    @LocalServerPort
    int port;

    @Test
    @DisplayName("Creates a new user correctly")
    public void createsUser()  {
        RestAssured.given()
                .basePath("/api/v1")
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(new CreateUserRequestDTO(
                        NAME,
                        SURNAME,
                        PHONE_NUMBER,
                        CONTACTS.stream().map(e->new ContactRequestDTO(
                                e.fullName().name(),
                                e.fullName().surname(),
                                e.phoneNumber().prefix()+e.phoneNumber().number()
                        )).toList()
                ))
                .post("/users")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("name",equalTo(NAME))
                .body("surname",equalTo(SURNAME))
                .body("phoneNumber",equalTo(PHONE_NUMBER))
                .body("contacts",empty());
    }

    @Test
    @DisplayName("Fails if phone is not correct")
    public void failsForDTO()  {
        RestAssured.given()
                .basePath("/api/v1")
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(new CreateUserRequestDTO(
                        NAME,
                       SURNAME,
                        "+301234456",
                        CONTACTS.stream().map(e->new ContactRequestDTO(
                                e.fullName().name(),
                                e.fullName().surname(),
                                e.phoneNumber().prefix()+e.phoneNumber().number()
                        )).toList()
                ))
                .post("/users")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("domainErrorCode",equalTo(DomainErrorCode.NOT_ALLOWED_PHONE_ERROR.toString()));

    }


}
