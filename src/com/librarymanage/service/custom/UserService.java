package com.librarymanage.service.custom;

import com.librarymanage.service.SuperService;
import com.librarymanage.dto.UserDto;

public interface UserService extends SuperService {
    String save(UserDto userDto)throws Exception;
    UserDto getUser(String email)throws Exception;
    boolean isUserExist(String email) throws Exception;
}
