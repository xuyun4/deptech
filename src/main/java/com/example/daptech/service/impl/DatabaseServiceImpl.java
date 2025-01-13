package com.example.daptech.service.impl;

import com.example.daptech.entity.PhoneCn;
import com.example.daptech.entity.PhoneUs;
import com.example.daptech.mapper.DatabaseMapper;
import com.example.daptech.service.DatabaseService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DatabaseServiceImpl implements DatabaseService {
    private final DatabaseMapper databaseMapper;

    @Override
    public void getCnDatabase(HttpServletResponse response) {
        //查询CN数据库,写入xlsx文件,返回前端
        List<PhoneCn> phoneCnList = databaseMapper.getCnDatabase();

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("template/CN.xlsx");
        try {
            XSSFWorkbook excel = new XSSFWorkbook(in);
            XSSFSheet sheet = excel.getSheet("Sheet1");

            XSSFRow row;
            for (int i = 0; i < phoneCnList.size(); i++) {
                PhoneCn phoneCn = phoneCnList.get(i);
                row = sheet.getRow(i + 3);
                if (row == null) {
                    row = sheet.createRow(i + 3);
                }

                setCellValue(row, 0, phoneCn.getId());
                setCellValue(row, 1, phoneCn.getPhone());
                setCellValue(row, 2, phoneCn.getType());
                setCellValue(row, 3, phoneCn.getNumber());
                setCellValue(row, 4, phoneCn.getStatus());
                setCellValue(row, 5, phoneCn.getCreateTime());
                setCellValue(row, 6, phoneCn.getUpdateTime());
                setCellValue(row, 7, phoneCn.getLocation());
                setCellValue(row, 8, phoneCn.getInformation());
            }

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=CN_Database.xlsx");

            ServletOutputStream out = response.getOutputStream();
            excel.write(out);

            out.close();
            excel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getUsDatabase(HttpServletResponse response) {
        List<PhoneUs> phoneUsList = databaseMapper.getUsDatabase();

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("template/US.xlsx");
        try {
            XSSFWorkbook excel = new XSSFWorkbook(in);
            XSSFSheet sheet = excel.getSheet("Sheet1");

            XSSFRow row;
            for (int i = 0; i < phoneUsList.size(); i++) {
                PhoneUs phoneUs = phoneUsList.get(i);
                row = sheet.getRow(i + 3);
                if (row == null) {
                    row = sheet.createRow(i + 3);
                }

                setCellValue(row, 0, phoneUs.getId());
                setCellValue(row, 1, phoneUs.getPhone());
                setCellValue(row, 2, phoneUs.getType());
                setCellValue(row, 3, phoneUs.getNumber());
                setCellValue(row, 4, phoneUs.getStatus());
                setCellValue(row, 5, phoneUs.getCreateTime());
                setCellValue(row, 6, phoneUs.getUpdateTime());
                setCellValue(row, 7, phoneUs.getLocation());
                setCellValue(row, 8, phoneUs.getInformation());
            }

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=US_Database.xlsx");

            ServletOutputStream out = response.getOutputStream();
            excel.write(out);

            out.close();
            excel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setCellValue(XSSFRow row, int cellIndex, Object value) {
        XSSFCell cell = row.getCell(cellIndex);
        if (cell == null) {
            cell = row.createCell(cellIndex);  // 创建新单元格
        }
        if (value != null) {
            cell.setCellValue(value.toString());
        } else {
            cell.setCellValue("");  // 如果值为 null，设置空字符串
        }
    }

}
