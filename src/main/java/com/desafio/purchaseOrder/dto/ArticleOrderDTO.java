package com.desafio.purchaseOrder.dto;

public class ArticleOrderDTO {
    private Integer productId;
    private Double discount;
    private Integer quantity;

    public ArticleOrderDTO(Integer productId, Double discount, Integer quantity) {
        this.productId = productId;
        this.discount = discount;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
