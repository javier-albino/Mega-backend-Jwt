package com.irojas.demojwt.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.irojas.demojwt.model.ProductRepository;
import com.irojas.demojwt.model.Product;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // Método para crear un nuevo producto
    public Product createProduct(ProductRequest request) {
        Product product = Product.builder()
            .nombre(request.getNombre())
            .descripcion(request.getDescripcion())
            .build();
        return productRepository.save(product);
    }

    // Método para obtener un producto por su ID
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    // Método para actualizar un producto existente
    public Product updateProduct(Integer id, ProductRequest request) {
        return productRepository.findById(id)
            .map(product -> {
                product.setNombre(request.getNombre());
                product.setDescripcion(request.getDescripcion());
                return productRepository.save(product);
            })
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    // Método para eliminar un producto por su ID
    public void deleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado para eliminar");
        }
        productRepository.deleteById(id);
    }

    // Método para listar todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
