/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import entidades.Dino_Habitat;
import entidades.Dinosaurios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author ailin
 */
/**
 * Maneja las operaciones de la base de datos para los habitats de dinosaurios
 */
public class ControladorHabitatDino {

    // Conexion a la base de datos (se usa en todos los metodos)
    private final EntityManagerFactory emf;

    // Constructor: Inicia la conexión con la base de datos al crear el controlador
    public ControladorHabitatDino() {
        this.emf = Persistence.createEntityManagerFactory("arkmino"); // "arkmino" = nombre de la BD
    }

    /*
     * Guarda un nuevo habitat en la base de datos
     */
    public boolean crearHabitat(Dino_Habitat habitat) {
        // Validacion simple
        if (habitat.getDino()== null) {
            System.out.println("Error: El habitat debe tener un dinosaurio asociado");
            return false;
        }

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(habitat);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar habitat: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    /**
     * Busca el habitat de un dinosaurio
     */
    public Dino_Habitat obtenerHabitatPorDino(Dinosaurios dino) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Dino_Habitat> query = em.createQuery(
                    "SELECT h FROM Dino_Habitat h WHERE h.dinosaurio = :dino",
                    Dino_Habitat.class);
            query.setParameter("dino", dino);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // No encontro resultados
        } catch (Exception e) {
            System.out.println("Error en busqueda: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public Dino_Habitat buscarPorTexto(String texto) {
    EntityManager em = emf.createEntityManager();
    try {
        TypedQuery<Dino_Habitat> query = em.createQuery(
            "SELECT h FROM Dino_Habitat h WHERE h.texto_Habitat = :texto", Dino_Habitat.class);
        query.setParameter("texto", texto);
        List<Dino_Habitat> resultados = query.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    } finally {
        em.close();
    }
}


    /*
     * Actualiza los datos de un habitat existente
     */
    public boolean actualizarHabitat(Dino_Habitat habitat) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(habitat);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    /*
     * Elimina un habitat de la base de datos
     */
    public boolean eliminarHabitat(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Dino_Habitat habitat = em.find(Dino_Habitat.class, id);
            if (habitat != null) {
                em.remove(habitat);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    /**
     * Cierra la conexion cuando ya no se necesite
     */
    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
