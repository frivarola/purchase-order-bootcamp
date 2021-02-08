package com.desafio.purchaseOrder.repository;

import com.desafio.purchaseOrder.dto.PurchaseOrderDTO;
import com.desafio.purchaseOrder.model.PurchaseOrderItem;

import java.util.ArrayList;

public interface PurchaseOrderRepository {

    void addArticleToOrder(PurchaseOrderDTO order);
    void addOrder(PurchaseOrderDTO order);
    boolean existOrderUser(String user);
    ArrayList<PurchaseOrderItem> getOrderByUser(String user);

}
