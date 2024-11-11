package com.irojas.demojwt.Product;

import com.irojas.demojwt.model.Categoria;
import com.irojas.demojwt.model.CategoriaRepository;
import com.irojas.demojwt.model.Product;
import com.irojas.demojwt.model.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoriaRepository categoriaRepository; // Agregar el repositorio de categoria

    // Método para crear un nuevo producto
    public Product createProduct(ProductRequest request) {
        // Buscar la categoría por ID
        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        // Crear el producto con la categoría asociada
        Product product = Product.builder()
            .nombre(request.getNombre())
            .descripcion(request.getDescripcion())
            .categoria(categoria) // Asocia la categoría al producto
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

                // Actualiza la categoría del producto si se proporciona una nueva ID de categoría
                if (request.getCategoriaId() != null) {
                    Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
                    product.setCategoria(categoria);
                }

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
