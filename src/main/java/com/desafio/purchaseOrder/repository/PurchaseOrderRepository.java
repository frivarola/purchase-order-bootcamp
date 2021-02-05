package com.desafio.purchaseOrder.repository;

import com.desafio.purchaseOrder.dto.ArticleOrderDTO;
import com.desafio.purchaseOrder.dto.requestDTO.PurchaseRequestDTO;

import java.util.ArrayList;

public interface PurchaseOrderRepository {

    void addOrder(PurchaseRequestDTO order);
    boolean existOrderUser(String user);
    ArrayList<ArticleOrderDTO> getOrderByUser(String user);

}
