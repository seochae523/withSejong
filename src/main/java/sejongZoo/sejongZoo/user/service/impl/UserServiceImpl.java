package sejongZoo.sejongZoo.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sejongZoo.sejongZoo.user.dto.UserDto;
import sejongZoo.sejongZoo.user.service.UserService;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    @Override
    public UserDto changeInformation(UserDto userDto) {
        return null;
    }
}
