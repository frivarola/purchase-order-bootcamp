package com.desafio.purchaseOrder.dto.responseDTO;

import com.desafio.purchaseOrder.model.PurchaseOrder;

public class CartResponseDTO {
    PurchaseOrder purchaseOrder;

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
}
