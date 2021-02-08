package com.desafio.purchaseOrder.dto.responseDTO;

import com.desafio.purchaseOrder.dto.ArticleDTO;
import java.util.ArrayList;
import java.util.UUID;

public class ReceiptDTO {
    private static Integer id = 1;
    private String status;
    private ArrayList<ArticleDTO> articles;
    private Double total;

    public ReceiptDTO() {
        this.id = id++;
        this.status = "PENDING";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<ArticleDTO> articles) {
        this.articles = articles;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}

