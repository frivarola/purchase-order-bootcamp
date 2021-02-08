package com.desafio.purchaseOrder.dto;

public class ArticleDTO {

    private Integer id;
    private String name;
    private String category;
    private String brand;
    private Double price;
    private Integer quantity;
    private Boolean freeShip;
    private String prestige;

    public ArticleDTO() {
    }

    public ArticleDTO(String name) {
        this.name = name;
    }

    public ArticleDTO(String name, String category, String brand, Double price, Integer quantity, Boolean freeShip, String prestige) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.freeShip = freeShip;
        this.prestige = prestige;
    }


    public ArticleDTO(Integer id, String name, String category, String brand, Double price, Integer quantity, Boolean freeShip, String prestige) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.freeShip = freeShip;
        this.prestige = prestige;
    }

    public ArticleDTO(String category, Boolean freeShip) {
        this.category = category;
        this.freeShip = freeShip;
    }

    public ArticleDTO(String category, String brand) {
        this.category = category;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getFreeShip() {
        return freeShip;
    }

    public void setFreeShip(Boolean freeShip) {
        this.freeShip = freeShip;
    }

    public String getPrestige() {
        return prestige;
    }

    public void setPrestige(String prestige) {
        this.prestige = prestige;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}