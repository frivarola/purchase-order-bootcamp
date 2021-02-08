package com.desafio.purchaseOrder.repository.impl;

import com.desafio.purchaseOrder.dto.ArticleOrderDTO;
import com.desafio.purchaseOrder.dto.requestDTO.PurchaseRequestDTO;
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
import java.util.Map;

@Repository
public class PurchaseOrderRepositoryImpl implements PurchaseOrderRepository {
    private static Map<String, ArrayList<ArticleOrderDTO>> keyValueDB;
    private static String pathDB = "src/main/java/com/desafio/purchaseOrder/repository/orders.json";
    static {
        keyValueDB = new HashMap<>();
    }


    @Override
    public void addArticleToOrder(String userName, ArrayList<ArticleOrderDTO> order) {
        ArrayList<ArticleOrderDTO> orderUpdate = keyValueDB.get(userName);
        orderUpdate.addAll(order);
        keyValueDB.replace(userName, orderUpdate);
    }

    @Override
    public void addOrder(PurchaseRequestDTO order){
        if(keyValueDB.containsKey(order.getUserName())){
            addArticleToOrder(order.getUserName(), order.getArticlesOrder());
        } else {
            keyValueDB.put(order.getUserName(), order.getArticlesOrder());
        }

        writeDatabase();

    }

    @Override
    public boolean existOrderUser(String user) {
        return keyValueDB.containsKey(user);
    }

    @Override
    public ArrayList<ArticleOrderDTO> getOrderByUser(String user) {
        return keyValueDB.get(user);
    }

    private static Map<String, ArrayList<ArticleOrderDTO>> loadDatabase(){
        HashMap<String, ArrayList<ArticleOrderDTO>> orders = new HashMap<>();
        File fileDb = null;

        try{
            fileDb = ResourceUtils.getFile(pathDB);
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<HashMap<String, ArrayList<ArticleOrderDTO>>> tf = new TypeReference<>(){};
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
            TypeReference<HashMap<String, ArrayList<ArticleOrderDTO>>> tf = new TypeReference<>(){};
            mapper.writeValue(fileDb, keyValueDB);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
