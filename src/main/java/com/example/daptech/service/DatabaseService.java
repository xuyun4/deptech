package com.example.daptech.service;

import com.example.daptech.entity.PhoneCn;
import com.example.daptech.entity.PhoneUs;
import com.example.daptech.response.PageResult;

public interface DatabaseService{

    void storeCnDatabase(/*HttpServletResponse response*/);

    void storeUsDatabase(/*HttpServletResponse response*/);

    PageResult<PhoneCn> getCnDatabase(int pageNum, int pageSize);

    PageResult<PhoneUs> getUsDatabase(int pageNum, int pageSize);
}
