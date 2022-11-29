package com.acidtango.boilerplate.users.infrastructure.persistence;

import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.primitives.UserPrimitives;
import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private List<ContactEntity> contacts;

    @Column(name="created_at")
    LocalDateTime createdAt;


    public static UserEntity fromDomain(User user) {
        UserPrimitives userPrimitives = user.toPrimitives();
        UserEntity userEntity = new UserEntity();
        userEntity.userId = userPrimitives.userId();
        userEntity.createdAt = userPrimitives.createdAt();
        userEntity.name = userPrimitives.fullName().name();
        userEntity.surname = userPrimitives.fullName().surname();
        userEntity.phoneNumber = userPrimitives.phoneNumber().prefix()+userPrimitives.phoneNumber().number();
        userEntity.contacts = userPrimitives.contacts().stream().map(contactPrimitives -> ContactEntity.fromDomain(userEntity,contactPrimitives)
        ).toList();
        return userEntity;
    }
}
