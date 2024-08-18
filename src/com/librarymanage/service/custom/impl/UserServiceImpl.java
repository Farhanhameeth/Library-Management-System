package com.librarymanage.service.custom.impl;

import com.librarymanage.Enum.DaoType;
import com.librarymanage.dao.DaoFactory;
import com.librarymanage.dao.custom.UserDao;
import com.librarymanage.dto.UserDto;
import com.librarymanage.entity.UserEntity;
import com.librarymanage.service.custom.UserService;

public class UserServiceImpl implements UserService {

    private final UserDao UserDao = (UserDao) DaoFactory.getInstance().getDao(DaoType.USER);

    @Override
    public String save(UserDto userDto) throws Exception {
        UserEntity entity = getUserEntity(userDto);
        return UserDao.create(entity)? "Success" : "Fail";
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity entity = UserDao.get(email);
        if (entity!= null) {
            return getUserDto(entity);
        }
        return null;
    }

    @Override
    public boolean isUserExist(String email) throws Exception {
        return UserDao.isUserExist(email);
    }

    private UserEntity getUserEntity(UserDto userDto) {
        return new UserEntity(
                userDto.getEmail(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPassword()
        );
    }

    private UserDto getUserDto(UserEntity entity) {
        return new UserDto(
                entity.getEmail(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPassword()
        );
    }


}
