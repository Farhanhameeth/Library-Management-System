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

    public  <T> T getService(ServiceType type) {
        switch (type) {
            case USER:
                return (T) new UserServiceImpl();
            case CATEGORY:
                return (T) new CategoryServiceImpl();
            case BOOK:
                return (T) new BookServiceImpl();
            case MEMBER:
                return (T) new MemberServiceImpl();
            case BORROWING:
                return (T) new BorrowingServiceImpl();
            case RETURN:
                return (T) new ReturnServiceImpl();
            default:
                return null;
        }
    }
}
