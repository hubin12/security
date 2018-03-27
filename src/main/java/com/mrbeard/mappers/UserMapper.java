package com.mrbeard.mappers;

import com.mrbeard.entites.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> getAllUser();

    User getOneUser(Long id);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

}
