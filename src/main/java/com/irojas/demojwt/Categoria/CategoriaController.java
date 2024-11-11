package com.irojas.demojwt.Categoria;

import com.irojas.demojwt.model.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173") // Cambia el origen según el frontend
@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/listar")
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        try {
            List<Categoria> categorias = categoriaService.getAllCategorias();
            if (categorias.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content si no hay categorías
            }
            return ResponseEntity.ok(categorias);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }
}
