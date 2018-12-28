package com.imooc.service.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemModel {

    private Integer id;

    /**
     * the name of this product
     */
    @NotBlank(message = "商品名称不能为空")
    private String title;

    /**
     * the price of this product
     */
    @NotNull(message = "商品价格不能不填")
    @Min(value = 0, message = "商品价格不能小于0")
    private BigDecimal price;

    /**
     * How many products provided
     */
    @NotNull(message = "商品库存不能为空")
    private Integer stock;

    private String description;

    /**
     * How many products have already sold out
     */
    private Integer sales;

    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
