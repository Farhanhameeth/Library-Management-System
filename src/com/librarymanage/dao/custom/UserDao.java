package com.librarymanage.dao.custom;

import com.librarymanage.dao.CrudDao;
import com.librarymanage.entity.UserEntity;

public interface UserDao extends CrudDao<UserEntity, String> {
    boolean isUserExist(String email) throws Exception;
}
