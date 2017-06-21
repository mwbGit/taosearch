package com.taosearch.service.dataoke.api;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;

/**
 *  Created by mwb on 2017/3/31 0031.
 */
public class ProductMO {

    @JSONField(name = "ID")
    private String taoKeId;
    @JSONField(name = "GoodsID")
    private String productId;
    @JSONField(name = "Title")
    private String name;
    @JSONField(name = "Pic")
    private String picture;
    @JSONField(name = "Cid")
    private Integer typeId;
    @JSONField(name = "Org_Price")
    private BigDecimal reservePrice;
    @JSONField(name = "Price")
    private BigDecimal discountPrice;
    @JSONField(name = "IsTmall")
    private Integer isTmall;
    @JSONField(name = "Sales_num")
    private Integer sales;
    @JSONField(name = "Dsr")
    private BigDecimal descriptionScore;
    @JSONField(name = "SellerID")
    private String storeId;
    @JSONField(name = "Commission_jihua")
    private BigDecimal general;
    @JSONField(name = "Commission_queqiao")
    private BigDecimal magpie;
    @JSONField(name = "Jihua_link")
    private String planUrl;
    @JSONField(name = "Quan_price")
    private BigDecimal couponAmount;
    @JSONField(name = "Quan_time")
    private String couponEndTime;
    @JSONField(name = "Quan_surplus")
    private Integer couponSurplusNumber;
    @JSONField(name = "Quan_receive")
    private Integer couponReceiveNumber;
    @JSONField(name = "Quan_condition")
    private String condition;

    public String getTaoKeId() {
        return taoKeId;
    }

    public void setTaoKeId(String taoKeId) {
        this.taoKeId = taoKeId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public BigDecimal getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(BigDecimal reservePrice) {
        this.reservePrice = reservePrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getIsTmall() {
        return isTmall;
    }

    public void setIsTmall(Integer isTmall) {
        this.isTmall = isTmall;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public BigDecimal getDescriptionScore() {
        return descriptionScore;
    }

    public void setDescriptionScore(BigDecimal descriptionScore) {
        this.descriptionScore = descriptionScore;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getGeneral() {
        return general;
    }

    public void setGeneral(BigDecimal general) {
        this.general = general;
    }

    public BigDecimal getMagpie() {
        return magpie;
    }

    public void setMagpie(BigDecimal magpie) {
        this.magpie = magpie;
    }

    public String getPlanUrl() {
        return planUrl;
    }

    public void setPlanUrl(String planUrl) {
        this.planUrl = planUrl;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(String couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public Integer getCouponSurplusNumber() {
        return couponSurplusNumber;
    }

    public void setCouponSurplusNumber(Integer couponSurplusNumber) {
        this.couponSurplusNumber = couponSurplusNumber;
    }

    public Integer getCouponReceiveNumber() {
        return couponReceiveNumber;
    }

    public void setCouponReceiveNumber(Integer couponReceiveNumber) {
        this.couponReceiveNumber = couponReceiveNumber;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
