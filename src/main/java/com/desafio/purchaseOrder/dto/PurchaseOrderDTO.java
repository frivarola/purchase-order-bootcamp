package com.desafio.purchaseOrder.dto;

import com.desafio.purchaseOrder.model.PurchaseOrderItem;

import java.util.ArrayList;

public class PurchaseOrderDTO {
    String userName;
    ArrayList<PurchaseOrderItem> items;

    public PurchaseOrderDTO(String userName, ArrayList<PurchaseOrderItem> items) {
        this.userName = userName;
        this.items = items;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<PurchaseOrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<PurchaseOrderItem> items) {
        this.items = items;
    }
}