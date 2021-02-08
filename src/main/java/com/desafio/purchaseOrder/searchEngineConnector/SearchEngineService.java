package com.desafio.purchaseOrder.searchEngineConnector;

import com.desafio.purchaseOrder.dto.ArticleDTO;
import com.desafio.purchaseOrder.exceptions.SearchEngineException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SearchEngineService {
    private final SearchEngineHTTPClient apiArticles;
    private static String apiUrl = "/api/v1/articles";

    public SearchEngineService(SearchEngineHTTPClient apiArticles) {
        this.apiArticles = apiArticles;
    }

    public ArrayList<ArticleDTO> getAllArticles() throws SearchEngineException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<ArticleDTO>> tf = new TypeReference<>() {
        };
        ArrayList<ArticleDTO> result = null;

        try {
            result = mapper.readValue(apiArticles.get(apiUrl), tf);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("Ocurrio un error al leer el JSON del servicio searchEngine");
            throw new SearchEngineException("Ocurrio un error al intentar buscar los articulos");
        }

        if (result.isEmpty()) {
            throw new SearchEngineException("No se encontraron detalle de los articulos para los ids provistos.");
        }
        return result;

    }

    public ArticleDTO getArticleById(Integer id) throws SearchEngineException {

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<ArticleDTO>> tf = new TypeReference<>() {
        };
        ArrayList<ArticleDTO> result = null;

        try {

            result = mapper.readValue(apiArticles.get(apiUrl + "?id=" + id), tf);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("Ocurrio un error al leer el JSON del servicio searchEngine");
            throw new SearchEngineException("Ocurrio un error al intentar buscar el articulo.");
        }

        return result.get(0);

    }

    public boolean articleHasStock(Integer id, Integer requestStock) throws SearchEngineException {

        ArticleDTO article = getArticleById(id);
        if (article.getQuantity() >= requestStock) {
            return true;
        } else {
            return false;
        }

    }
}
