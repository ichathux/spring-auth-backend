package auth.example.demo.service;

import auth.example.demo.dto.CredentialDto;
import auth.example.demo.dto.SignUpDto;
import auth.example.demo.dto.UserDto;
import auth.example.demo.exception.AppException;
import auth.example.demo.mappers.UserMapper;
import auth.example.demo.model.User;
import auth.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    public UserDto login(CredentialDto credentialDto) {
        User user = userRepository.findByLogin(credentialDto.getLogin())
                .orElseThrow(
                        () -> new AppException("unknown user",
                                HttpStatus.NOT_FOUND)
                );

        if (passwordEncoder.matches(CharBuffer.wrap(credentialDto.getPassword()),
                user.getPassword()))
            return userMapper.toUserDto(user);

        throw new AppException("Invalid password",HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto signUpDto) {

        Optional<User> oUser = userRepository.findByLogin(signUpDto.getLogin());
        if(oUser.isPresent())
            throw new AppException("Login already exist", HttpStatus.BAD_REQUEST);

        User user = userMapper.signUpToUser(signUpDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.getPassword())));
        User saveUser = userRepository.save(user);
        return userMapper.toUserDto(saveUser);
    }
}
