package com.librarymanage.service;

import com.librarymanage.Enum.ServiceType;
import com.librarymanage.service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    public  SuperService getService(ServiceType type) {
        switch (type) {
            case USER:
                return new UserServiceImpl();
            case CATEGORY:
                return new CategoryServiceImpl();
            case BOOK:
                return new BookServiceImpl();
            case MEMBER:
                return new MemberServiceImpl();
            case BORROWING:
                return new BorrowingServiceImpl();
            case RETURN:
                return new ReturnServiceImpl();
            default:
                return null;
        }
    }
}
