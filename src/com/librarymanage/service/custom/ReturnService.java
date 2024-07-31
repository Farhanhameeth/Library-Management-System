package com.librarymanage.service.custom;

import com.librarymanage.dto.ReturnDto;
import com.librarymanage.service.SuperService;

import java.util.ArrayList;

public interface ReturnService extends SuperService {
    String save(ReturnDto returnDto) throws Exception;
    ArrayList<ReturnDto> getAll() throws Exception;
    String getLastId() throws Exception;

    String returnBook(ReturnDto returnDto) throws Exception;
}
