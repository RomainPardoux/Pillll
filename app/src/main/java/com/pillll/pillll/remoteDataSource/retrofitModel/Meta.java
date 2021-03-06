package com.pillll.pillll.remoteDataSource.retrofitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO used to retrieve JSON Meta data from Pillll server
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class Meta {

    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("current_items")
    @Expose
    private Integer currentItems;
    @SerializedName("total_items")
    @Expose
    private Integer totalItems;
    @SerializedName("offset")
    @Expose
    private Integer offset;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCurrentItems() {
        return currentItems;
    }

    public void setCurrentItems(Integer currentItems) {
        this.currentItems = currentItems;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
