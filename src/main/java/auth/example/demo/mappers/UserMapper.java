package auth.example.demo.mappers;

import auth.example.demo.dto.SignUpDto;
import auth.example.demo.dto.UserDto;
import auth.example.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);
}
