package com.lunsir.subject.common.entity;

import com.google.common.collect.Collections2;
import lombok.Data;
import org.mapstruct.ap.shaded.freemarker.template.utility.CollectionUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author lunSir
 * @create 2024-03-04 21:24
 */
@Data
public class PageResult<T> implements Serializable {

    // 当前页数
    private Integer pageNo;
    // 每一页的条数
    private Integer pageSize;
    // 总条数
    private Integer total;
    // 总页数
    private Integer totalPage;
    // 返回具体的数据
    private List<T> result = Collections.emptyList();

    private Integer start;

    private Integer end;

    public void setResult(List<T> result){
        this.result = result;
        if(result != null && result.size() > 0){
            setTotal(result.size());
        }
    }

    /**
     * 设置total，totalPage，start，end；
     * @param resultSize
     */
    public void setTotal(Integer resultSize){
        this.total = resultSize;
        if (pageSize > 0){
            //计算totalPage
            this.totalPage = (this.total / this.pageSize)
                    + (this.total % this.pageSize == 0 ? 0 : 1);
        }else {
            this.totalPage = 0;
        }
        // 计算start
        this.start = (pageSize > 0 ? this.pageSize * (this.pageNo - 1) : 0) + 1;
        // 计算end
        this.end = (this.start - 1) + this.pageSize * (this.pageNo > 0 ? 1 : 0);
    }

    public void setPageSize(Integer pageSize){
        this.pageSize = pageSize;
    }

    public void setPageNo(Integer pageNo){
        this.pageNo = pageNo;
    }

}
