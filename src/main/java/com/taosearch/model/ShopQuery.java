package com.taosearch.model;

import org.apache.commons.lang.StringUtils;

import java.util.List;

public class ShopQuery {
    private int page;
    private int rows;
    private Long shop_id;
    private String name;
    private String phone;
    private String qq;
    private String wechat;
    private Integer price_min;
    private Integer price_max;
    private Integer settlement_min;
    private Integer settlement_max;
    private List<Long> shop_ids;
    private String user_id;
    private String orderColumn;
    private String order;

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Integer getPrice_min() {
        return price_min;
    }

    public void setPrice_min(Integer price_min) {
        this.price_min = price_min;
    }

    public Integer getPrice_max() {
        return price_max;
    }

    public void setPrice_max(Integer price_max) {
        this.price_max = price_max;
    }

    public Integer getSettlement_min() {
        return settlement_min;
    }

    public void setSettlement_min(Integer settlement_min) {
        this.settlement_min = settlement_min;
    }

    public Integer getSettlement_max() {
        return settlement_max;
    }

    public void setSettlement_max(Integer settlement_max) {
        this.settlement_max = settlement_max;
    }

    public int getOffest() {
        return Math.max((page - 1) * rows, 0);
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<Long> getShop_ids() {
        return shop_ids;
    }

    public void setShop_ids(List<Long> shop_ids) {
        this.shop_ids = shop_ids;
    }

    public int getPage() {
        return page;
    }

    public int getRows() {
        return rows;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderBy() {
        if (StringUtils.isNotBlank(this.order) && StringUtils.isNotBlank(this.orderColumn)) {
            return this.orderColumn + " " + this.order;
        }
        return null;
    }
}
