/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import entidades.Dinosaurios;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ailin
 */
/**
 * Clase que maneja todas las operaciones de la base de datos para los dinosaurios.
 * (Crear, buscar, actualizar, eliminar).
 */
public class ControladorDinosaurios {

    // Conexión principal a la base de datos (se crea una sola vez)
    private final EntityManagerFactory emf;

    // Constructor: Inicia la conexión con la base de datos al crear el controlador
    public ControladorDinosaurios() {
        this.emf = Persistence.createEntityManagerFactory("arkmino"); // "arkmino" = nombre de la BD
    }

    /*
     * GUARDA UN NUEVO DINOSAURIO EN LA BASE DE DATOS.
     */
    public boolean crearDino(Dinosaurios dino) {
        EntityManager em = emf.createEntityManager(); // Abre conexión
        try {
            em.getTransaction().begin(); // Inicia transacción (como un "modo edición")
            em.persist(dino); // Guarda el dinosaurio
            em.getTransaction().commit(); // Confirma los cambios
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Si hay error, cancela los cambios
            }
            System.err.println("Error al crear dinosaurio: " + e.getMessage());
            return false;
        } finally {
            em.close(); // Cierra la conexión (importante para no saturar la BD)
        }
    }

    /*
     * BUSCA UN DINOSAURIO POR SU ID.
     */
    public Dinosaurios buscarDinoPorId(int id) {
        EntityManager em = emf.createEntityManager(); // Abre conexión
        try {
            return em.find(Dinosaurios.class, id); // Busca por ID
        } finally {
            em.close(); // Cierra la conexión
        }
    }

    /*
     * ACTUALIZA LOS DATOS DE UN DINOSAURIO EXISTENTE.
     */
    public boolean actualizarDino(Dinosaurios dino) {
        EntityManager em = emf.createEntityManager(); // Abre conexión
        try {
            em.getTransaction().begin(); // Inicia transacción
            em.merge(dino); // Actualiza los datos
            em.getTransaction().commit(); // Confirma cambios
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Si hay error, deshace cambios
            }
            System.err.println("Error al actualizar: " + e.getMessage());
            return false;
        } finally {
            em.close(); // Cierra conexión
        }
    }

    /*
     * ELIMINA UN DINOSAURIO DE LA BASE DE DATOS.
     */
    public boolean eliminarDinosaurio(int id) {
        EntityManager em = emf.createEntityManager(); // Abre conexión
        try {
            em.getTransaction().begin(); // Inicia transacción
            Dinosaurios dino = em.find(Dinosaurios.class, id); // Busca el dinosaurio
            if (dino != null) {
                em.remove(dino); // Si existe, lo elimina
                em.getTransaction().commit(); // Confirma cambios
                return true;
            }
            return false; // Si no existe, retorna false
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Si hay error, cancela
            }
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        } finally {
            em.close(); // Cierra conexión
        }
    }

    /**
     * CIERRA LA CONEXIÓN CON LA BASE DE DATOS.
     * (Debe llamarse al terminar de usar el controlador).
     */
    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close(); // Cierra la conexión principal
        }
    }
}