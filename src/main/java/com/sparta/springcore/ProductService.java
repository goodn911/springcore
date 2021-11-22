package com.sparta.springcore;

import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;
import java.util.List;


@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(){
        ProductRepository productRepository = new ProductRepository();
        this.productRepository = productRepository;
    }



    public Product createProduct(ProductRequestDto requestDto) throws SQLException {
// 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);

        ProductRepository productRepository = new ProductRepository();
        productRepository.createProduct(product);

        return product;
    }

    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto) throws SQLException {

        Product product = productRepository.getProduct(id);
        if (product == null) {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
        }


        productRepository.updateMyprice(id, requestDto.getMyprice());

        return product;
    }

    public List<Product> getProducts() throws SQLException {

        List<Product> products = productRepository.getProducts();

        return products;
    }
}



