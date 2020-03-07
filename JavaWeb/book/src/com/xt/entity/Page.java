package com.xt.entity;

import java.util.List;

// 分页模型：<T> 是具体的模块的javaBean 类
public class Page<T> {
    public static final int PAGE_SIZE = 4;
    private Integer pageNo; // 当前页码
    private Integer pageTotal; // 总页码
    private Integer pageTotalCount; // 总记录数
    private  Integer pageSize = PAGE_SIZE; // 每页显示数量
    private List<T> items; // 当前页数据
    private String url; // 路径

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        // 数据边界的有效检查
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
