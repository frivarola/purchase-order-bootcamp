package com.desafio.purchaseOrder.service.impl;

import com.desafio.purchaseOrder.dto.PurchaseOrderDTO;
import com.desafio.purchaseOrder.dto.responseDTO.CartResponseDTO;
import com.desafio.purchaseOrder.exceptions.SearchEngineException;
import com.desafio.purchaseOrder.model.PurchaseOrder;
import com.desafio.purchaseOrder.model.PurchaseOrderItem;
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

import java.lang.reflect.Array;
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
    public PurchaseOrderResponseDTO addPurchaseOrder(PurchaseRequestDTO purchaseRequest) throws PurchaseOrderException {
        PurchaseOrderResponseDTO response = new PurchaseOrderResponseDTO();

        try {
            ArrayList<PurchaseOrderItem> purchaseOrderItems = getPurchaseOrderItems(purchaseRequest.getArticlesOrder());
            PurchaseOrderDTO order = new PurchaseOrderDTO(purchaseRequest.getUserName(), purchaseOrderItems);

            if (purchaseOrderDB.existOrderUser(order.getUserName())) {
                purchaseOrderDB.addArticleToOrder(order);
            } else {
                purchaseOrderDB.addOrder(order);
            }

            ReceiptDTO receipt = new ReceiptDTO();
            receipt.setArticles(purchaseOrderItems);
            receipt.setTotal(calculateSubTotal(purchaseOrderItems));

            StatusCodeDTO statusCode = new StatusCodeDTO(HttpStatus.OK.value(), "La solicitud se completo con exito.");
            response.setStatusCode(statusCode);
            response.setReceipt(receipt);

        } catch (SearchEngineException e) {
            e.printStackTrace();
            StatusCodeDTO statusCode = new StatusCodeDTO(HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage());
            response.setStatusCode(statusCode);
        } catch (PurchaseOrderException oe) {
            oe.printStackTrace();
            throw new PurchaseOrderException(oe.getMessage());
        }

        return response;
    }

    @Override
    public CartResponseDTO getCartUser(String username) throws PurchaseOrderException {
        CartResponseDTO response = new CartResponseDTO();
        ArrayList<PurchaseOrderItem> itemsCart = (ArrayList<PurchaseOrderItem>) purchaseOrderDB.getPurchaseOrderItemsByUser(username);

        if (itemsCart != null) {
            PurchaseOrder order = new PurchaseOrder(username, itemsCart);
            response.setPurchaseOrder(order);
        } else {
            throw new PurchaseOrderException("No se encontro carrito para el usuario " + username);
        }

        return response;
    }

    private ArticleDTO searchArticle(ArticleOrderDTO art) throws PurchaseOrderException, SearchEngineException {
        ArticleDTO articleDetail = null;
        try {
            articleDetail = searchEngine.getArticleById(art.getProductId());

            if (articleDetail != null) {
                if (articleDetail.getQuantity() >= art.getQuantity()) {
                    return articleDetail;
                }
                throw new PurchaseOrderException("No hay stock para el producto con id " + art.getProductId());
            } else {
                throw new PurchaseOrderException("No se encontro articulo con id " + art.getProductId());
            }

        } catch (SearchEngineException e) {
            e.printStackTrace();
            throw new SearchEngineException("Servidor search-engine no disponible");
        }

    }

    private Double calculateSubTotal(ArrayList<PurchaseOrderItem> articles) {
        return articles.stream().mapToDouble(PurchaseOrderItem::getTotalPrice).sum();
    }

    private ArrayList<PurchaseOrderItem> getPurchaseOrderItems(ArrayList<ArticleOrderDTO> articlesOrder) throws
            PurchaseOrderException, SearchEngineException {

        ArrayList<PurchaseOrderItem> purchaseOrderItems = new ArrayList<>();

        for (ArticleOrderDTO order : articlesOrder) {
            ArticleDTO detail = searchArticle(order);
            PurchaseOrderItem it = new PurchaseOrderItem(detail.getId(), detail.getName(), order.getQuantity(), order.getDiscount(), detail.getPrice());
            purchaseOrderItems.add(it);
        }

        return purchaseOrderItems;
    }

}
