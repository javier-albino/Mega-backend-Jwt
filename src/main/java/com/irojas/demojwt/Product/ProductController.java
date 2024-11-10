package com.irojas.demojwt.Product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.irojas.demojwt.model.Product;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/register")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        Product product = productService.createProduct(request);
        ProductResponse response = ProductResponse.builder()
            .id(product.getId())
            .nombre(product.getNombre())
            .descripcion(product.getDescripcion())
            .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductResponse> response = products.stream()
            .map(product -> ProductResponse.builder()
                .id(product.getId())
                .nombre(product.getNombre())
                .descripcion(product.getDescripcion())
                .build())
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Integer id, @RequestBody ProductRequest request) {
        try {
            Product updatedProduct = productService.updateProduct(id, request);
            ProductResponse response = ProductResponse.builder()
                .id(updatedProduct.getId())
                .nombre(updatedProduct.getNombre())
                .descripcion(updatedProduct.getDescripcion())
                .build();
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/uf")
    public ResponseEntity<Double> getUfValue() {
        String url = "https://mindicador.cl/api/uf";
        RestTemplate restTemplate = new RestTemplate();
        
        // Realiza la llamada y almacena la respuesta
        @SuppressWarnings("unchecked")
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        
        // Extrae la serie y maneja el mapeo de manera más segura
        @SuppressWarnings("null")
        List<?> serie = (List<?>) response.get("serie");
        @SuppressWarnings("unchecked")
        Map<String, Object> firstElement = (Map<String, Object>) serie.get(0);
        Double ufValue = ((Number) firstElement.get("valor")).doubleValue();
        
        return ResponseEntity.ok(ufValue);
    }
    


}
