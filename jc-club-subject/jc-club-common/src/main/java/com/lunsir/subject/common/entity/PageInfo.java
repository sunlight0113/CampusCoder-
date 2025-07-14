package com.lunsir.subject.common.entity;

import lombok.Data;

/**
 * 分页请求实体
 * @author lunSir
 * @create 2024-03-04 21:20
 */
@Data
public class PageInfo {

    // 当前页数
    private Integer pageNo = 1;
    // 每一页的条数
    private Integer pageSize = 20;
    public Integer getPageNo(){
        if (pageNo == null || pageNo < 1){
            return 1;
        }
        return pageNo;
    }
    public Integer getPageSize(){
        if (pageSize == null || pageSize > Integer.MAX_VALUE || pageSize < 1){
            return 20;
        }
        return pageSize;
    }
}
