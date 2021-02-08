package com.desafio.purchaseOrder.dto.responseDTO;

import com.desafio.purchaseOrder.model.PurchaseOrderItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReceiptDTO {
    private static Integer iid = 1;
    private String id;
    private String status;
    private ArrayList<PurchaseOrderItem> articles;
    private Double total;


    public ReceiptDTO() {
        this.id = generateID();
        this.status = "PENDING";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<PurchaseOrderItem> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<PurchaseOrderItem> articles) {
        this.articles = articles;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    private String generateID(){
        Calendar c = new GregorianCalendar();
        StringBuilder builder = new StringBuilder();
        builder.append(c.get(Calendar.YEAR));
        builder.append(c.get(Calendar.MONTH));
        builder.append(c.get(Calendar.DAY_OF_MONTH));
        builder.append(iid);
        iid ++;

        return builder.toString();
    }
}

