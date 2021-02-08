package com.desafio.purchaseOrder.service.impl;

import com.desafio.purchaseOrder.exceptions.SearchEngineException;
import com.desafio.purchaseOrder.searchEngineConnector.SearchEngineService;
import com.desafio.purchaseOrder.dto.ArticleDTO;
import com.desafio.purchaseOrder.dto.ArticleOrderDTO;
import com.desafio.purchaseOrder.dto.requestDTO.PurchaseRequestDTO;
import com.desafio.purchaseOrder.dto.responseDTO.PurchaseOrderResponseDTO;
import com.desafio.purchaseOrder.dto.responseDTO.ReceiptDTO;
import com.desafio.purchaseOrder.dto.responseDTO.StatusCodeDTO;
import com.desafio.purchaseOrder.exceptions.PurchaseOrderException;
import com.desafio.purchaseOrder.repository.PurchaseOrderRepository;
import com.desafio.purchaseOrder.service.PurchaseOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        PurchaseOrderResponseDTO response = null;

        try {
            ArrayList<ArticleDTO> articlesOrder = getArticles(purchaseOrder.getArticlesOrder());
            ReceiptDTO receipt = new ReceiptDTO();

            receipt.setArticles(articlesOrder);
            receipt.setTotal(calculateTotal(articlesOrder));

            StatusCodeDTO statusCode = new StatusCodeDTO(HttpStatus.NOT_FOUND.value(), "No se encontraron los articulos solicitados.");
            response.setStatusCode(statusCode);

            if (purchaseOrderDB.existOrderUser(purchaseOrder.getUserName())) {
                purchaseOrderDB.addArticleToOrder(purchaseOrder.getUserName(), purchaseOrder.getArticlesOrder());
            } else {
                purchaseOrderDB.addOrder(purchaseOrder);
            }

        } catch (SearchEngineException | PurchaseOrderException e) {
            e.printStackTrace();
            StatusCodeDTO statusCode = new StatusCodeDTO(HttpStatus.NOT_FOUND.value(), e.getMessage());
            response.setStatusCode(statusCode);
        }

        return response;
    }

    private ArticleDTO searchArticleById(ArticleOrderDTO art) throws SearchEngineException, PurchaseOrderException {
        ArticleDTO articleDetail = null;
        articleDetail = searchEngine.getArticleById(art.getProductId());

        if (articleDetail != null) {
            if(articleDetail.getQuantity() < art.getQuantity()){
                return articleDetail;
            }
            throw new PurchaseOrderException("No hay stock para el producto con id " + art.getProductId());
        } else {
            throw new PurchaseOrderException("No se encontro articulo con id " + art.getProductId());
        }

    }

    private Double calculateTotal(ArrayList<ArticleDTO> articles) {
        return articles.stream().mapToDouble(ArticleDTO::getPrice).sum();
    }

    private ArrayList<ArticleDTO> getArticles(ArrayList<ArticleOrderDTO> articlesOrder) throws
            PurchaseOrderException, SearchEngineException {
        ArrayList<ArticleDTO> articles = new ArrayList<>();
        for (ArticleOrderDTO art : articlesOrder) {
            articles.add(searchArticleById(art));
        }
        return articles;
    }
}
