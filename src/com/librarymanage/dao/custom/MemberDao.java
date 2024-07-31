package com.librarymanage.dao.custom;

import com.librarymanage.dao.CrudDao;
import com.librarymanage.entity.MemberEntity;

import java.util.ArrayList;

public interface MemberDao extends CrudDao<MemberEntity,String> {
    ArrayList<MemberEntity> search(String searchText) throws Exception;
}
