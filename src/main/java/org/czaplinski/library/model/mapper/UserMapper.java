package org.czaplinski.library.model.mapper;

import org.czaplinski.library.model.User;
import org.czaplinski.library.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserMapper {
    public User mapToUser(UserDto userDto){
        return new User(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmailAddress(),
                userDto.getParcelLocker(),
                LocalDate.now()
        );
    }
}
