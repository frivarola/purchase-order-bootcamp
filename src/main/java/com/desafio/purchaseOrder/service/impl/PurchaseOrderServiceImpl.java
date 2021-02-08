package com.desafio.purchaseOrder.service.impl;

import com.desafio.purchaseOrder.SearchEngineConnector.SearchEngineService;
import com.desafio.purchaseOrder.dto.ArticleDTO;
import com.desafio.purchaseOrder.dto.ArticleOrderDTO;
import com.desafio.purchaseOrder.dto.requestDTO.PurchaseRequestDTO;
import com.desafio.purchaseOrder.dto.responseDTO.PurchaseOrderResponseDTO;
import com.desafio.purchaseOrder.dto.responseDTO.ReceiptDTO;
import com.desafio.purchaseOrder.repository.PurchaseOrderRepository;
import com.desafio.purchaseOrder.service.PurchaseOrderService;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderDB;
    private final SearchEngineService searchEngine;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderDB, SearchEngineService searchEngine) {
        this.purchaseOrderDB = purchaseOrderDB;
        this.searchEngine = searchEngine;
    }

    @Override
    public PurchaseOrderResponseDTO addPurchaseOrder(PurchaseRequestDTO purchaseOrder) {
        if (purchaseOrderDB.existOrderUser(purchaseOrder.getUserName())) {
            purchaseOrderDB.addArticleToOrder(purchaseOrder.getUserName(), purchaseOrder.getArticlesOrder());
        } else {
            purchaseOrderDB.addOrder(purchaseOrder);
        }
        PurchaseOrderResponseDTO response = new PurchaseOrderResponseDTO();
        ReceiptDTO receipt = makeReceiptDTO(purchaseOrder);
        response.setReceipt(makeReceiptDTO(purchaseOrder));

        return response;
    }

    private Double calculateTotal(ArrayList<ArticleDTO> articles) {
        return articles.stream().mapToDouble(ArticleDTO::getPrice).sum();
    }

    private ReceiptDTO makeReceiptDTO(PurchaseRequestDTO order) {
        ReceiptDTO receipt = new ReceiptDTO();
        receipt.setArticles(getArticles(order.getArticlesOrder()));
        Double total = calculateTotal(receipt.getArticles());
        receipt.setTotal(total);
        return receipt;
    }

    private ArrayList<ArticleDTO> getArticles(ArrayList<ArticleOrderDTO> articlesOrder) {
        ArrayList<ArticleDTO> articles = new ArrayList<>();
        articlesOrder.forEach((ArticleOrderDTO art) -> articles.add(searchEngine.getArticleById(art.getProductId())));
        return articles;
    }
}
