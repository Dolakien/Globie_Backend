package com.product.globie.service;

import com.product.globie.payload.DTO.BookMarkDTO;

import java.util.List;

public interface BookMarkService {
    List<BookMarkDTO> getAllBookMarkOfUser(int uId);

    void addBookMark(int pId);

    void deleteBookMark(int bId);
}
