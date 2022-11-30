package com.acidtango.boilerplate.users.infrastructure.persistence;

import com.acidtango.boilerplate.users.domain.User;
import com.acidtango.boilerplate.users.domain.primitives.FullNamePrimitives;
import com.acidtango.boilerplate.users.domain.primitives.PhoneNumberPrimitives;
import com.acidtango.boilerplate.users.domain.primitives.UserPrimitives;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @Column(name = "phone_number_prefix")
    private String phoneNumberPrefix;

    @Column(name = "phone_number_digits")
    private String phoneNumberDigits;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ContactEntity> contacts;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public static UserEntity fromDomain(User user) {
        UserPrimitives userPrimitives = user.toPrimitives();
        UserEntity userEntity = new UserEntity();
        userEntity.userId = userPrimitives.userId();
        userEntity.createdAt = userPrimitives.createdAt();
        userEntity.name = userPrimitives.fullName().name();
        userEntity.surname = userPrimitives.fullName().surname();
        userEntity.phoneNumberPrefix = userPrimitives.phoneNumber().prefix();
        userEntity.phoneNumberDigits = userPrimitives.phoneNumber().digits();
        userEntity.contacts = user.getContacts().stream().map(contactPrimitives -> ContactEntity.fromDomain(userEntity, contactPrimitives)
        ).toList();
        return userEntity;
    }

    public User toDomain() {
        UserPrimitives userPrimitives = new UserPrimitives(
                this.userId,
                new FullNamePrimitives(this.name, this.surname),
                new PhoneNumberPrimitives(this.phoneNumberPrefix, this.phoneNumberDigits),
                this.createdAt,
                this.contacts.stream().map(ContactEntity::toPrimitives).toList());


        return User.fromPrimitives(userPrimitives);
    }
}
