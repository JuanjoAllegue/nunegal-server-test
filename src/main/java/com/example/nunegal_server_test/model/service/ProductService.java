package com.example.nunegal_server_test.model.service;

import com.example.nunegal_server_test.model.service.dto.ProductDTO;
import com.example.nunegal_server_test.model.service.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getSimilarProducts(String productId) throws NotFoundException;
}
