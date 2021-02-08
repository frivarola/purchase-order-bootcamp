package com.desafio.purchaseOrder.controller;

import com.desafio.purchaseOrder.dto.requestDTO.PurchaseRequestDTO;
import com.desafio.purchaseOrder.dto.responseDTO.CartResponseDTO;
import com.desafio.purchaseOrder.dto.responseDTO.PurchaseOrderResponseDTO;
import com.desafio.purchaseOrder.exceptions.PurchaseOrderException;
import com.desafio.purchaseOrder.service.impl.PurchaseOrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(path = "/cart/{username}")
    CartResponseDTO getCartByUser(@PathVariable String username){
        try {
            return purchaseOrderService.getCartUser(username);
        } catch (PurchaseOrderException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro carrito para el usuario: " + username, e);
        }
    }
}
