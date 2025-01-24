package com.example.daptech.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.daptech.entity.PhoneCn;
import com.example.daptech.entity.PhoneUs;
import com.example.daptech.listener.PageReadListener;
import com.example.daptech.mapper.DatabaseMapper;
import com.example.daptech.response.PageResult;
import com.example.daptech.service.DatabaseService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DatabaseServiceImpl implements DatabaseService {
    private final DatabaseMapper databaseMapper;

    @Override
    public void storeCnDatabase(/*HttpServletResponse response*/) {
        //查询CN数据库,写入xlsx文件,返回前端
        List<PhoneCn> phoneCnList = databaseMapper.getCnDatabase();

        /*InputStream in = this.getClass().getClassLoader().getResourceAsStream("template/CN.xlsx");
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
        }*/
        // 设置写入的文件路径
        String file = "/home/ubuntu/database/CN_Database.xlsx";

        // 使用 EasyExcel 写入数据
        EasyExcel.write(file, PhoneCn.class)  // 第二个参数是数据类型
                .sheet("Sheet1")                  // 设置sheet名称
                .doWrite(phoneCnList);             // 将数据写入文件
    }

    @Override
    public void storeUsDatabase(/*HttpServletResponse response*/) {
        List<PhoneUs> phoneUsList = databaseMapper.getUsDatabase();

/*        InputStream in = this.getClass().getClassLoader().getResourceAsStream("template/US.xlsx");
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
        }*/
        // 设置写入的文件路径
        String file = "/home/ubuntu/database/US_Database.xlsx";

        // 使用 EasyExcel 写入数据
        EasyExcel.write(file, PhoneUs.class)  // 第二个参数是数据类型
                .sheet("Sheet1")                  // 设置sheet名称
                .doWrite(phoneUsList);             // 将数据写入文件
    }

    @Override
    public PageResult<PhoneCn> getCnDatabase(int pageNum, int pageSize) {
/*        PageHelper.startPage(pageNum, pageSize);
        List<PhoneCn> phoneCnList = databaseMapper.getCnDatabase();
        Page<PhoneCn> page = (Page<PhoneCn>) phoneCnList;

        PageResult<PhoneCn> phoneCnPageResult = new PageResult<PhoneCn>();
        phoneCnPageResult.setPageNum(pageNum);
        phoneCnPageResult.setPageSize(pageSize);
        phoneCnPageResult.setTotalPages(page.getPages());
        phoneCnPageResult.setTotal(page.getTotal());
        phoneCnPageResult.setData(page.getResult());

        return phoneCnPageResult;*/

        String file = "/home/ubuntu/database/CN_Database.xlsx";

        int startRow = (pageNum - 1) * pageSize;  // 计算开始行
        int endRow = pageNum * pageSize;  // 计算结束行

        // 创建分页读取的监听器
        PageReadListener<PhoneCn> listener = new PageReadListener<>(startRow, endRow);

        // 使用 EasyExcel 读取指定页的数据
        EasyExcel.read(file, PhoneCn.class, listener)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet(0)  // 第一个 sheet
                .doRead();

        // 获取分页读取的数据
        List<PhoneCn> pageData = listener.getDataList();

        PageResult<PhoneCn> phoneCnPageResult = new PageResult<>();
        phoneCnPageResult.setPageNum(pageNum);
        phoneCnPageResult.setPageSize(pageSize);
        phoneCnPageResult.setTotalPages(listener.getTotalPages(pageSize));
        phoneCnPageResult.setTotal(listener.getTotal());
        phoneCnPageResult.setData(listener.getDataList());

        return phoneCnPageResult;
    }

    @Override
    public PageResult<PhoneUs> getUsDatabase(int pageNum, int pageSize) {
        String file = "/home/ubuntu/database/US_Database.xlsx";

        int startRow = (pageNum - 1) * pageSize;  // 计算开始行
        int endRow = pageNum * pageSize;  // 计算结束行

        // 创建分页读取的监听器
        PageReadListener<PhoneUs> listener = new PageReadListener<>(startRow, endRow);

        // 使用 EasyExcel 读取指定页的数据
        EasyExcel.read(file, PhoneUs.class, listener)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet(0)  // 第一个 sheet
                .doRead();

        // 获取分页读取的数据
        List<PhoneUs> pageData = listener.getDataList();

        PageResult<PhoneUs> phoneUsPageResult = new PageResult<>();
        phoneUsPageResult.setPageNum(pageNum);
        phoneUsPageResult.setPageSize(pageSize);
        phoneUsPageResult.setTotalPages(listener.getTotalPages(pageSize));
        phoneUsPageResult.setTotal(listener.getTotal());
        phoneUsPageResult.setData(listener.getDataList());

        return phoneUsPageResult;
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
