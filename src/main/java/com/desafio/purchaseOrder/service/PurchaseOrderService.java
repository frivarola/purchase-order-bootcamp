package com.desafio.purchaseOrder.service;

import com.desafio.purchaseOrder.dto.requestDTO.PurchaseRequestDTO;
import com.desafio.purchaseOrder.dto.responseDTO.PurchaseOrderResponseDTO;
import com.desafio.purchaseOrder.exceptions.PurchaseOrderException;

public interface PurchaseOrderService {

    PurchaseOrderResponseDTO addPurchaseOrder(PurchaseRequestDTO purchaseOrder) throws PurchaseOrderException;
}
