package edu.fu.demoshop.model;

import org.springframework.data.domain.Sort;

public class ProductPage {
    private int page= 0;
    private int pageSize= 10;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "name";

    public int getPageNumber() {
        return page;
    }

    public void setPageNumber(int pageNumber) {
        page = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }


}