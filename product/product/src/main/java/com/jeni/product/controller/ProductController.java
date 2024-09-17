package com.jeni.product.controller;

import com.jeni.product.dto.RequestDTO;
import com.jeni.product.dto.ResponseDTO;
import com.jeni.product.model.Product;
import com.jeni.product.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Tag(name = "Product", description = "Product API")
public class ProductController {

    @Autowired
    private ProductService productService;
    static int a;
    ProductController(this)
    {
        System.out.println("ProductController");

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO create(@RequestBody RequestDTO requestDTO)
    {
        return productService.createProduct(requestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler()
    public List<ResponseDTO> getProduct()
    {
//        try{
//            Thread.sleep(5000);
//        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return productService.getProduct();
    }


}
