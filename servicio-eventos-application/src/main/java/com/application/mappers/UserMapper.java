package com.application.mappers;

import com.domain.models.UserModel;
import com.infrastructure.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserModel userToUserModel(User user);
    User userModelToUser(UserModel userModel);
}
