package com.librarymanage.dao.custom;

import com.librarymanage.dao.CrudDao;
import com.librarymanage.entity.BorrowingEntity;

import java.util.ArrayList;

public interface BorrowingDao extends CrudDao<BorrowingEntity,String> {
    ArrayList<BorrowingEntity> search(String searchText) throws Exception;
    boolean updateReturnStatus(String id) throws Exception;
}
