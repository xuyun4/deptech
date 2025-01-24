package com.example.daptech.service;

import com.example.daptech.entity.PhoneCn;
import com.example.daptech.response.PageResult;
import jakarta.servlet.http.HttpServletResponse;

public interface DatabaseService{

    void storeCnDatabase(HttpServletResponse response);

    void storeUsDatabase(HttpServletResponse response);

    PageResult<PhoneCn> getCnDatabase(int pageNum, int pageSize);
}
