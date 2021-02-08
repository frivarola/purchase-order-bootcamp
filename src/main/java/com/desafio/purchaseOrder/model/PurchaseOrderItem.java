package com.desafio.purchaseOrder.model;

public class PurchaseOrderItem {
    private Integer id;
    private String name;
    private Integer quantity;
    private Double discount;
    private Double unitPrice;
    private Double totalPrice;

    public PurchaseOrderItem() {
    }

    public PurchaseOrderItem(Integer id, String name, Integer quantity, Double discount, Double unitPrice, Double totalPrice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.discount = discount;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public PurchaseOrderItem(Integer id, String name, Integer quantity, Double discount, Double unitPrice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.discount = discount;
        this.unitPrice = unitPrice;
        this.totalPrice = (unitPrice * quantity) - ((unitPrice * quantity) * (discount / 100));
    }

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
