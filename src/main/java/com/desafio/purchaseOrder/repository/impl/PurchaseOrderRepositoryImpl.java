package com.desafio.purchaseOrder.repository.impl;

import com.desafio.purchaseOrder.dto.ArticleOrderDTO;
import com.desafio.purchaseOrder.dto.PurchaseOrderDTO;
import com.desafio.purchaseOrder.model.PurchaseOrderItem;
import com.desafio.purchaseOrder.repository.PurchaseOrderRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PurchaseOrderRepositoryImpl implements PurchaseOrderRepository {
    private static Map<String, ArrayList<PurchaseOrderItem>> keyValueDB;
    private static String pathDB = "src/main/java/com/desafio/purchaseOrder/repository/orders.json";
    static {
        keyValueDB = new HashMap<>();
    }


    @Override
    public void addArticleToOrder(PurchaseOrderDTO order) {
        ArrayList<PurchaseOrderItem> orderUpdate = keyValueDB.get(order.getUserName());
        orderUpdate.addAll(order.getItems());
        keyValueDB.replace(order.getUserName(), orderUpdate);
    }

    @Override
    public void addOrder(PurchaseOrderDTO order){
        if(keyValueDB.containsKey(order.getUserName())){
            addArticleToOrder(order);
        } else {
            keyValueDB.put(order.getUserName(), order.getItems());
        }

        writeDatabase();

    }

    @Override
    public List<PurchaseOrderItem> getPurchaseOrderItemsByUser(String user) {
        ArrayList<PurchaseOrderItem> queryResult = keyValueDB.get(user);
        return queryResult;
    }

    @Override
    public boolean existOrderUser(String user) {
        return keyValueDB.containsKey(user);
    }

    private static Map<String, ArrayList<PurchaseOrderItem>> loadDatabase(){
        HashMap<String, ArrayList<PurchaseOrderItem>> orders = new HashMap<>();
        File fileDb = null;

        try{
            fileDb = ResourceUtils.getFile(pathDB);
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<HashMap<String, ArrayList<PurchaseOrderItem>>> tf = new TypeReference<>(){};
            orders = mapper.readValue(fileDb, tf);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return orders;
    }

    private static void writeDatabase(){
        File fileDb = null;

        try{
            fileDb = ResourceUtils.getFile(pathDB);
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<HashMap<String, ArrayList<PurchaseOrderItem>>> tf = new TypeReference<>(){};
            mapper.writeValue(fileDb, keyValueDB);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
