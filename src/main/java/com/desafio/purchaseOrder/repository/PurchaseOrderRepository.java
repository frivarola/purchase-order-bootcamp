package com.desafio.purchaseOrder.repository;

import com.desafio.purchaseOrder.dto.ArticleDTO;

public interface PurchaseOrderRepository {

    void addArticleToOrder(ArticleDTO a);

}
