package com.acidtango.boilerplate.users.infrastructure.persistence;

import com.acidtango.boilerplate.users.domain.IUserRepository;
import com.acidtango.boilerplate.users.domain.User;

import java.util.ArrayList;

public class UserRepositoryMemory implements IUserRepository {

    private ArrayList<User> users;

    public UserRepositoryMemory(){
        this.users = new ArrayList<>();
    }

    @Override
    public void save(User user) {
        this.users.add(user);
    }
}
