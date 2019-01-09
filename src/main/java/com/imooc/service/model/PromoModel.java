package com.imooc.service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class PromoModel {

    private Integer id;
    /**
     * 秒杀活动状态
     * 1：未开始
     * 2：进行中
     * 3：已结束
     */
    private Integer status;

    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer itemId;

    /**
     * 秒杀价格：与商品价格不一样
     */
    private BigDecimal promoPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(BigDecimal promoPrice) {
        this.promoPrice = promoPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
