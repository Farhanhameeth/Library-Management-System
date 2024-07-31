package com.librarymanage.service.custom;

import com.librarymanage.dto.BookDto;
import com.librarymanage.service.SuperService;

import java.util.ArrayList;

public interface BookService extends SuperService {
    String save(BookDto bookDto) throws Exception;
    String update(BookDto bookDto) throws Exception;
    String delete(String bookId) throws Exception;
    BookDto getBook(String bookId) throws Exception;
    ArrayList<BookDto> getAll() throws Exception;
    String getLastID() throws Exception;
}
