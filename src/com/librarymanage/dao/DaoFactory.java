package com.librarymanage.dao;

import com.librarymanage.Enum.DaoType;
import com.librarymanage.dao.custom.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public <T> T getDao(DaoType type){
        switch (type){
            case USER:
                return (T) new UserDaoImpl();
            case CATEGORY:
                return (T) new CategoryDaoImpl();
            case BOOK:
                return (T) new BookDaoImpl();
            case MEMBER:
                return (T) new MemberDaoImpl();
            case BORROWING:
                return (T) new BorrowingDaoImpl();
            case RETURN:
                return (T) new ReturnDaoImpl();
            default:
                return null;
        }
    }
}
