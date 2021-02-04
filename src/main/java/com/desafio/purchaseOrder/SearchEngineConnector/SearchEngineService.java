package com.desafio.purchaseOrder.SearchEngineConnector;

import com.desafio.purchaseOrder.dto.ArticleDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class SearchEngineService {
    private final SearchEngineHTTPClient apiArticles;
    private static String apiUrl = "/api/v1/articles";

    public SearchEngineService(SearchEngineHTTPClient apiArticles) {
        this.apiArticles = apiArticles;
    }

    public ArrayList<ArticleDTO> getAllArticles(){
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<ArticleDTO>> tf = new TypeReference<>(){};
        ArrayList<ArticleDTO> result = null;

        try {
            result = mapper.readValue(apiArticles.get(apiUrl),tf);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("Ocurrio un error al leer el JSON del servicio searchEngine");
        }

        return result;

    }
}
