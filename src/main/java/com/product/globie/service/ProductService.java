package com.product.globie.service;

import com.product.globie.entity.Product;
import com.product.globie.payload.DTO.ProductDTO;
import com.product.globie.payload.DTO.ProductImageDTO;
import com.product.globie.payload.request.CreateProductRequest;
import com.product.globie.payload.request.UpdatePostRequest;
import com.product.globie.payload.request.UpdateProductRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProduct();

    List<ProductDTO> getProductByUserStatusProcessing();

    List<ProductDTO> getProductByUserStatusSelling();

    List<ProductDTO> getProductByUserStatusSold();

    List<ProductDTO> getAllProductStatusSelling();

    List<ProductDTO> getAllProductStatusSold();

    List<ProductDTO> getAllProductStatusProcessing();

    List<ProductDTO> getAllProductStatusSellingStore();

    List<ProductDTO> getAllProductStatusSellingUser();

    ProductDTO createProduct(CreateProductRequest productRequest);

    List<ProductDTO> filterProducts(Integer cId, String brand, String origin, Double minPrice, Double maxPrice);

    void deleteProduct(int pId);

    ProductDTO updateProduct(UpdateProductRequest productRequest, int pId);

    void updateStatusProduct(int pId);

    ProductDTO getProductDetail(int pId);

    List<ProductDTO> getProductByCategory(int cId);

    List<ProductDTO> getProductByCategoryOfUser(int cId);

    List<ProductDTO> getProductByCategoryOfStore(int cId);

    List<ProductImageDTO> uploadMultipleProductImages(MultipartFile[] multipartFiles, int productId) throws IOException;

    List<ProductImageDTO> getAllImageByProduct(int productId);

    List<ProductImageDTO> getAllImageByProductStatusTrue(int productId);

    void deleteProductImage(String imageCode) throws IOException;

    ProductImageDTO getProductImageByCode(String imageCode);

    Integer countProductSelling();

    Integer countProductSold();

    Integer countProductProcessing();

    List<ProductDTO> searchProductsByNameOrBrand(@Param("searchKeyword") String searchKeyword);

}
