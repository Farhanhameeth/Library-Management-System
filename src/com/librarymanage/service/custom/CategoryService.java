package com.librarymanage.service.custom;

import com.librarymanage.dto.CategoryDto;
import com.librarymanage.service.SuperService;

import java.util.ArrayList;

public interface CategoryService extends SuperService {
    String save(CategoryDto categoryDto) throws Exception;
    String update(CategoryDto categoryDto) throws Exception;
    String delete(String categoryId) throws Exception;
    CategoryDto getCategory(String categoryId) throws Exception;
    ArrayList<CategoryDto> getAll() throws Exception;
    String getLastID() throws Exception;
}
