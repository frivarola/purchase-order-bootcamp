package com.desafio.purchaseOrder.service;

import com.desafio.purchaseOrder.dto.requestDTO.PurchaseRequestDTO;
import com.desafio.purchaseOrder.dto.responseDTO.PurchaseOrderResponseDTO;

public interface PurchaseOrderService {

    PurchaseOrderResponseDTO addPurchaseOrder(PurchaseRequestDTO purchaseOrder);
}
