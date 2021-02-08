package com.desafio.purchaseOrder.controller;

import com.desafio.purchaseOrder.dto.requestDTO.PurchaseRequestDTO;
import com.desafio.purchaseOrder.dto.responseDTO.PurchaseOrderResponseDTO;
import com.desafio.purchaseOrder.exceptions.PurchaseOrderException;
import com.desafio.purchaseOrder.service.impl.PurchaseOrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/v1/purchase-request")
public class PurchaseOrderController {
    private final PurchaseOrderServiceImpl purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderServiceImpl purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping()
    PurchaseOrderResponseDTO purchaseOrderRequest(@RequestBody PurchaseRequestDTO purchaseOrderRequest){
        try {
            return purchaseOrderService.addPurchaseOrder(purchaseOrderRequest);
        } catch (PurchaseOrderException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro articulo para el id provisto.");
        }
    }
}
