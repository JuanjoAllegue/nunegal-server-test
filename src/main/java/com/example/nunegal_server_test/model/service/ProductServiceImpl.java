package com.example.nunegal_server_test.model.service;

import com.example.nunegal_server_test.config.ExternalAPIProperties;
import com.example.nunegal_server_test.model.domain.Product;
import com.example.nunegal_server_test.model.service.dto.ProductDTO;
import com.example.nunegal_server_test.model.service.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final RestTemplate restTemplate;
    private final ExternalAPIProperties externalAPIProperties;

    public ProductServiceImpl(RestTemplate restTemplate, ExternalAPIProperties externalAPIProperties) {
        this.restTemplate = restTemplate;
        this.externalAPIProperties = externalAPIProperties;
    }

    @Override
    public List<ProductDTO> getSimilarProducts(String productId) throws NotFoundException {
        List<ProductDTO> result = new ArrayList<>();

        // Check if product exists
        try {
            String urlBaseProduct = externalAPIProperties.getUrl() + "/product/" + productId;
            restTemplate.getForObject(urlBaseProduct, Product.class);
        } catch (HttpClientErrorException.NotFound notFoundEx) {
            throw new NotFoundException("Product with id " + productId + " not found.");
        }

        // Obtain similar products ids
        String urlSimilarIds = externalAPIProperties.getUrl() + "/product/" + productId + "/similarids";
        ResponseEntity<String[]> response = restTemplate.getForEntity(urlSimilarIds, String[].class);
        String[] similarIds = response.getBody();

        // Return empty list if no similar products
        if (similarIds == null) {
            return result;
        }

        // Get each product detail
        for (String id : similarIds) {
            try {
                String urlProduct = externalAPIProperties.getUrl() + "/product/" + id;
                Product product = restTemplate.getForObject(urlProduct, Product.class);
                if (product != null) {
                    result.add(new ProductDTO(product));
                }
            } catch (HttpClientErrorException.NotFound notFoundEx) {
                // Ignore not found products
                logger.warn("Product with id {} not found.", id);
            }
        }

        return result;
    }
}