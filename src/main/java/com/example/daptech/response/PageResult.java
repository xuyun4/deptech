package com.example.daptech.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    private int pageNum;          // 当前页码
    private int pageSize;         // 每页显示的条目数
    private int totalPages;       // 总页数
    private long total;           // 总记录数
    private List<T> Data;         // 数据列表
}
