package com.desafio.purchaseOrder.model;

import java.util.ArrayList;

public class PurchaseOrder {
    String userName;
    ArrayList<PurchaseOrderItem> items;
    Double total;

    public PurchaseOrder(String userName, ArrayList<PurchaseOrderItem> items) {
        this.userName = userName;
        this.items = items;
        this.total = calculateTotal();
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    private Double calculateTotal(){
        Double total = 0.0;
        for (PurchaseOrderItem it: items) {
            total += it.getTotalPrice();
        }
        return total;
    }
}
