package com.desafio.purchaseOrder.repository.impl;

import com.desafio.purchaseOrder.dto.ArticleOrderDTO;
import com.desafio.purchaseOrder.dto.requestDTO.PurchaseRequestDTO;
import com.desafio.purchaseOrder.repository.PurchaseOrderRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class PurchaseOrderRepositoryImpl implements PurchaseOrderRepository {
    private static HashMap<String, ArrayList<ArticleOrderDTO>> keyValueDB;
    private static Integer id = 0;
    static {
        keyValueDB = new HashMap<>();
    }


    private void addArticleToOrder(String userName, ArrayList<ArticleOrderDTO> order) {
        ArrayList<ArticleOrderDTO> orderUpdate = keyValueDB.get(userName);
        orderUpdate.addAll(order);
        keyValueDB.replace(userName, orderUpdate);
    }

    @Override
    public void addOrder(PurchaseRequestDTO order) {
        if(keyValueDB.containsKey(order.getUserName())){
            addArticleToOrder(order.getUserName(), order.getArticlesOrder());
        } else {
            keyValueDB.put(order.getUserName(), order.getArticlesOrder());
        }

    }

    @Override
    public boolean existOrderUser(String user) {
        return keyValueDB.containsKey(user);
    }

    @Override
    public ArrayList<ArticleOrderDTO> getOrderByUser(String user) {
        return keyValueDB.get(user);
    }

}
