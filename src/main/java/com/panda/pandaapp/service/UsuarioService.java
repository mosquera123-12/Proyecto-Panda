package com.panda.pandaapp.service;

import com.panda.pandaapp.model.Usuario;
import com.panda.pandaapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que contiene la lógica de negocio para operaciones con usuarios.
 * Implementa la capa de servicio entre el controlador y el repositorio.
 */
@Service
public class UsuarioService {

    // Inyección del repositorio de usuarios
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Obtiene todos los usuarios del sistema.
     * @return Lista de todos los usuarios
     */
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Busca un usuario por su ID.
     * @param id_usuario ID del usuario a buscar
     * @return Optional que puede contener el usuario si se encuentra
     */
    public Optional<Usuario> obtenerUsuarioPorId(Long id_usuario) {
        return usuarioRepository.findById(id_usuario);
    }

    /**
     * Busca un usuario por su email.
     * @param correo Email del usuario a buscar
     * @return Optional que puede contener el usuario si se encuentra
     */
    public Optional<Usuario> obtenerUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByEmail(correo);
    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     * @param usuario Usuario a guardar
     * @return El usuario guardado
     * @throws IllegalArgumentException si ya existe un usuario con el mismo email
     */
    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {
        // Verificar si ya existe un usuario con el mismo email
        if (usuarioRepository.existsByEmail(usuario.getCorreo())) {
            throw new IllegalArgumentException("Ya existe un usuario con el email: " + usuario.getCorreo());
        }
        
        // Aquí se podría agregar lógica para encriptar la contraseña antes de guardar
        // usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        
        return usuarioRepository.save(usuario);
    }

    /**
     * Actualiza un usuario existente.
     * @param id_usuario ID del usuario a actualizar
     * @param usuarioActualizado Datos actualizados del usuario
     * @return El usuario actualizado
     * @throws IllegalArgumentException si el usuario no existe
     */
    @Transactional
    public Usuario actualizarUsuario(Long id_usuario, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id_usuario)
            .map(usuarioExistente -> {
                usuarioExistente.setNombre(usuarioActualizado.getNombre());
              
                
                // Si se proporciona un nuevo email, verificar que no exista ya
                if (!usuarioExistente.getCorreo().equals(usuarioActualizado.getCorreo())) {
                    if (usuarioRepository.existsByEmail(usuarioActualizado.getCorreo())) {
                        throw new IllegalArgumentException("Ya existe un usuario con el email: " + usuarioActualizado.getCorreo());
                    }
                    usuarioExistente.setCorreo(null);(usuarioActualizado.getCorreo());
                }
                
                // Si se proporciona una nueva contraseña, actualizarla
                // Aquí se podría agregar lógica para encriptar la contraseña
                if (usuarioActualizado.getContrasena() != null && !usuarioActualizado.getContrasena().isEmpty()) {
                    // usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
                    usuarioExistente.setContrasena(null);(usuarioActualizado.getContrasena());
                }
                
                return usuarioRepository.save(usuarioExistente);
            })
            .orElseThrow(() -> new IllegalArgumentException("No se encontró usuario con ID: " + id));
    }

    /**
     * Elimina un usuario por su ID.
     * @param id_usuario ID del usuario a eliminar
     * @throws IllegalArgumentException si el usuario no existe
     */
    @Transactional
    public void eliminarUsuario(Long id_usario) {
        if (!usuarioRepository.existsById(id_usario)) {
            throw new IllegalArgumentException("No se encontró usuario con ID: " + id_usario);
        }
        usuarioRepository.deleteById(id_usario);
    }

    /**
     * Busca usuarios por nombre o apellido.
     * @param texto Texto a buscar en nombre o apellido
     * @return Lista de usuarios que coinciden con la búsqueda
     */
    public List<Usuario> buscarUsuariosPorNombreOApellido(String texto) {
        return usuarioRepository.findByNombreContainingOrApellidoContaining(texto, texto);
    }

    /**
     * Busca usuarios por rol.
     * @param rol Rol a buscar
     * @return Lista de usuarios con el rol especificado
     */
    public List<Usuario> buscarUsuariosPorRol(String rol) {
        return usuarioRepository.findByRol(rol);
    }
}