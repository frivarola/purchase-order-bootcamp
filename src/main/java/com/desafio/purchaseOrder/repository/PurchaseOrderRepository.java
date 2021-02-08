package com.desafio.purchaseOrder.repository;

import com.desafio.purchaseOrder.dto.PurchaseOrderDTO;
import com.desafio.purchaseOrder.model.PurchaseOrderItem;

import java.util.List;

public interface PurchaseOrderRepository {

    void addArticleToOrder(PurchaseOrderDTO order);
    void addOrder(PurchaseOrderDTO order);
    List<PurchaseOrderItem> getPurchaseOrderItemsByUser(String user);
    boolean existOrderUser(String user);

}
