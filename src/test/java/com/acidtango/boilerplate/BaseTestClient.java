package com.acidtango.boilerplate;


import com.acidtango.boilerplate.users.infrastructure.persistence.userRepository.entities.UserEntity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.transaction.BeforeTransaction;

import javax.transaction.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestInjectionConfiguration.class)
@Transactional
public abstract class BaseTestClient {

    @LocalServerPort
    private int port;

    @Autowired
    private JpaRepository<UserEntity, String> userRepository;


    @BeforeEach
    @BeforeTransaction
    public final void clearDatabase() {
        userRepository.deleteAll();
    }


    public final RequestSpecification request() {
        return RestAssured.given()
                .basePath("/api")
                .port(this.port)
                .contentType(ContentType.JSON);
    }
}
