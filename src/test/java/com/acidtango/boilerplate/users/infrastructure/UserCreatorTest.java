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
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestInjectionConfiguration.class)
public class UserCreatorTest {


    public static final String NAME = UserFixtures.pedroPrimitives.fullName().name();
    public static final String SURNAME = UserFixtures.pedroPrimitives.fullName().surname();
    public static final String PHONE_NUMBER =
            UserFixtures.pedroPrimitives.phoneNumber().prefix() + UserFixtures.pedroPrimitives.phoneNumber().digits();
    public static final List<ContactPrimitives> CONTACTS = UserFixtures.pedroPrimitives.contacts();
    public static final ContactPrimitives FIRST_CONTACT = CONTACTS.get(0);
    @LocalServerPort
    int port;

    @Test
    public void create_new_user() {
        CreateUserRequestDTO request = this.buildRequest(NAME, SURNAME, PHONE_NUMBER, CONTACTS);

        ValidatableResponse response = RestAssured.given()
                .basePath("/api/v1")
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(request)
                .post("/users")
                .then();

        response.statusCode(HttpStatus.CREATED.value());
        response.body("name", equalTo(NAME));
        response.body("surname", equalTo(SURNAME));
        response.body("phoneNumber", equalTo(PHONE_NUMBER));
        response.body("contacts.size()", equalTo(1));
    }

    @Test
    public void fails_if_phone_is_not_correct() {
        CreateUserRequestDTO request = this.buildRequest(NAME, SURNAME, "+301234456", CONTACTS);

        ValidatableResponse response = RestAssured.given()
                .basePath("/api/v1")
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(request)
                .post("/users")
                .then();

        response.statusCode(HttpStatus.BAD_REQUEST.value());
        response.body("domainErrorCode", equalTo(DomainErrorCode.NOT_ALLOWED_PHONE_ERROR.toString()));


    }

    private CreateUserRequestDTO buildRequest(String name, String surname, String phoneNumber, List<ContactPrimitives> contacts) {
        return new CreateUserRequestDTO(
                name,
                surname,
                phoneNumber,
                contacts.stream().map(e -> new ContactRequestDTO(
                        e.fullName().name(),
                        e.fullName().surname(),
                        e.phoneNumber().prefix() + e.phoneNumber().digits()
                )).toList()
        );
    }

}
