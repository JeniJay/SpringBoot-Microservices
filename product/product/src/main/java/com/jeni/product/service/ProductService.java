package com.jeni.product.service;

import com.jeni.product.dto.RequestDTO;
import com.jeni.product.dto.ResponseDTO;
import com.jeni.product.model.Product;
import com.jeni.product.repository.ProductRepository;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    public ResponseDTO createProduct(RequestDTO requestDTO) {
        Product product=Product.builder().
                name(requestDTO.name()).price(requestDTO.price()).build();
        productRepository.save(product);
        log.info("Product is created");
        return new ResponseDTO(product.getId(), product.getName(), product.getPrice());
    }

    public List<ResponseDTO> getProduct() {

        return productRepository.findAll().stream().map(product ->
                new ResponseDTO(product.getId(),product.getName(),product.getPrice())).toList();
    }
}
