package com.desafio.purchaseOrder.repository;

import com.desafio.purchaseOrder.dto.ArticleOrderDTO;
import com.desafio.purchaseOrder.dto.requestDTO.PurchaseRequestDTO;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface PurchaseOrderRepository {

    void addArticleToOrder(String user, ArrayList<ArticleOrderDTO> articlesOrder);
    void addOrder(PurchaseRequestDTO order);
    boolean existOrderUser(String user);
    ArrayList<ArticleOrderDTO> getOrderByUser(String user);

}
