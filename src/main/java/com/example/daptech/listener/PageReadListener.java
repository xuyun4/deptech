package com.example.daptech.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageReadListener<T> extends AnalysisEventListener<T> {
    private final int startRow;
    private final int endRow;
    private final List<T> dataList = new ArrayList<>();
    private int rowIndex = 0;  // 当前读取的行号
    private int total = 0;  // 总记录数

    public PageReadListener(int startRow, int endRow) {
        this.startRow = startRow;
        this.endRow = endRow;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {

        // 统计总记录数
        total++;

        // 判断当前行是否在分页范围内
        if (rowIndex >= startRow && rowIndex < endRow) {
            dataList.add(data);  // 将数据添加到当前页的数据列表中
        }
        rowIndex++;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 分析完成后的操作（如果有需要可以在这里处理）
    }

    public int getTotalPages(int pageSize) {
        return (int) Math.ceil((double) total / pageSize);
    }

}