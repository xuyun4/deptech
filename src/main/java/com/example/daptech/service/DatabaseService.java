package com.example.daptech.service;

import jakarta.servlet.http.HttpServletResponse;

public interface DatabaseService{

    void getCnDatabase(HttpServletResponse response);

    void getUsDatabase(HttpServletResponse response);
}
