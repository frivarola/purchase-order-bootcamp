package com.desafio.purchaseOrder.dto.responseDTO;

import com.desafio.purchaseOrder.dto.ArticleDTO;
import java.util.ArrayList;

public class ReceiptDTO {
    private Integer id;
    private String status;
    private ArrayList<ArticleDTO> articles;

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
}
