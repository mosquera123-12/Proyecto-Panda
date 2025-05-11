package com.panda.pandaapp.repository;

import com.panda.pandaapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad Usuario.
 * Extiende JpaRepository para heredar métodos CRUD básicos como save, findAll, findById, etc.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    /**
     * Busca un usuario por su dirección de email.
     * @param email el email del usuario a buscar
     * @return un Optional que puede contener el usuario si se encuentra
     */
    Optional<Usuario> findByEmail(String email);
    
    /**
     * Verifica si existe un usuario con el email proporcionado.
     * @param email el email a verificar
     * @return true si existe un usuario con el email, false en caso contrario
     */
    boolean existsByEmail(String email);
    
    /**
     * Busca usuarios por nombre y apellido que contengan el texto proporcionado.
     * @param nombre texto a buscar en el campo nombre
     * @param apellido texto a buscar en el campo apellido
     * @return lista de usuarios que coinciden con los criterios
     */
    java.util.List<Usuario> findByNombreContainingOrApellidoContaining(String nombre, String apellido);
    
    /**
     * Busca usuarios por su rol.
     * @param rol el rol de los usuarios a buscar
     * @return lista de usuarios con el rol especificado
     */
    java.util.List<Usuario> findByRol(String rol);
    
    /**
     * Busca usuarios por su estado activo.
     * @param activo estado de los usuarios a buscar
     * @return lista de usuarios con el estado especificado
     */
    java.util.List<Usuario> findByActivo(boolean activo);
}