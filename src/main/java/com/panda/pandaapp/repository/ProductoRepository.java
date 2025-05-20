package com.panda.pandaapp.repository;

import com.panda.pandaapp.model.Producto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repositorio para acceder a los datos de los productos.
 * Extiende JpaRepository para tener acceso a métodos CRUD básicos.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    Optional<Producto> findById(Long id_producto);

    boolean existsById(Long id_producto);

    void deleteById(Long id_producto);
    
}