package dev.jpfsgs.quizzapp.user.dto.mapper;

import dev.jpfsgs.quizzapp.user.dto.request.RegisterUserDTO;
import dev.jpfsgs.quizzapp.user.dto.response.UserDTO;
import dev.jpfsgs.quizzapp.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
    User toUser(RegisterUserDTO registerUserDTO);
}
