package com.desafio.purchaseOrder.dto.requestDTO;

import com.desafio.purchaseOrder.dto.ArticleDTO;
import com.desafio.purchaseOrder.dto.ArticleOrderDTO;

import java.util.ArrayList;

public class PurchaseRequestDTO {

    private String userName;
    private ArrayList<ArticleOrderDTO> articlesOrder;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<ArticleOrderDTO> getArticlesOrder() {
        return articlesOrder;
    }

    public void setArticlesOrder(ArrayList<ArticleOrderDTO> articlesOrder) {
        this.articlesOrder = articlesOrder;
    }
}
