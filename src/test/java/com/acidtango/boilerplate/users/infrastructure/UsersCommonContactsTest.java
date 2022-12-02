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
public class UsersCommonContactsTest {

    private final String COMMON_CONTACT_NAME = "Julio";
    private final List<ContactRequestDTO> COMMON_CONTACTS = new ArrayList<>(
            List.of(new ContactRequestDTO(
                    COMMON_CONTACT_NAME,
                    "Strauss",
                    "+34999999999")
            ));


    @LocalServerPort
    int port;

    private String firstCreatedUserUUID;
    private String secondCreatedUserUUID;

    @BeforeEach
    public void createUser() {
        firstCreatedUserUUID = this.createUser(UserFixtures.pedroPrimitives, COMMON_CONTACTS);
        secondCreatedUserUUID = this.createUser(UserFixtures.pabloPrimitives, COMMON_CONTACTS);
    }

    @Test
    public void get_user() {

        ValidatableResponse response = RestAssured.given()
                .basePath("/api/v1")
                .port(this.port)
                .contentType(ContentType.JSON)
                .get("/users/" + firstCreatedUserUUID + "/common-contacts/" + secondCreatedUserUUID)
                .then();

        response.body("commonContacts.size()", equalTo(1));
        response.body("commonContacts.get(0).name", equalTo(COMMON_CONTACT_NAME));

        response.statusCode(HttpStatus.OK.value());

    }

    @Test
    public void empty_array_if_not_common_contacts() {
        String createdUser = this.createUser(UserFixtures.pabloPrimitives, new ArrayList<>());

        ValidatableResponse response = RestAssured.given()
                .basePath("/api/v1")
                .port(this.port)
                .contentType(ContentType.JSON)
                .get("/users/" + firstCreatedUserUUID + "/common-contacts/" + createdUser)
                .then();

        response.body("commonContacts.size()", equalTo(0));
        response.statusCode(HttpStatus.OK.value());

    }

    @Test
    public void fails_if_user_not_found() {
        final String FAKE_UUID = "309b0edd-0748-4e96-b3be-167c646cf095";
        ValidatableResponse response = RestAssured.given()
                .basePath("/api/v1")
                .port(this.port)
                .contentType(ContentType.JSON)
                .get("/users/" + firstCreatedUserUUID + "/common-contacts/" + FAKE_UUID)
                .then();

        response.statusCode(HttpStatus.NOT_FOUND.value());
        response.body("domainErrorCode", equalTo(DomainErrorCode.USER_NOT_FOUND.toString()));
    }

    private String createUser(UserPrimitives primitives, List<ContactRequestDTO> commonContacts) {
        ArrayList<ContactRequestDTO> contacts = new ArrayList<>();

        List<ContactRequestDTO> userContacts = primitives
                .contacts()
                .stream().map(e -> new ContactRequestDTO(
                        e.fullName().name(),
                        e.fullName().surname(),
                        e.phoneNumber().prefix() + e.phoneNumber().digits()
                )).toList();

        contacts.addAll(userContacts);
        contacts.addAll(commonContacts);

        CreateUserRequestDTO request = new CreateUserRequestDTO(
                primitives.fullName().name(),
                primitives.fullName().surname(),
                primitives.phoneNumber().toString(),
                contacts);

        return RestAssured.given()
                .basePath("/api/v1")
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(request)
                .post("/users")
                .then().statusCode(HttpStatus.CREATED.value()).extract().body().path("userId");
    }


}
