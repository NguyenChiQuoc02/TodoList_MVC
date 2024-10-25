package com.project.todoList.Service;


import com.project.todoList.dto.UserDto;
import com.project.todoList.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
