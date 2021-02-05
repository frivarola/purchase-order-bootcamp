package com.desafio.purchaseOrder.repository;

import com.desafio.purchaseOrder.dto.ArticleDTO;
import com.desafio.purchaseOrder.dto.ArticleOrderDTO;
import com.desafio.purchaseOrder.dto.requestDTO.PurchaseRequestDTO;

public interface PurchaseOrderRepository {

    void addOrder(PurchaseRequestDTO order);

}
