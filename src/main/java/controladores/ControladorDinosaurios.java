/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

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
 * Clase que maneja todas las operaciones de la base de datos para los
 * dinosaurios. (Crear, buscar, actualizar, eliminar)
 */
public class ControladorDinosaurios {

    private final EntityManagerFactory emf;

    public ControladorDinosaurios() {
        this.emf = Persistence.createEntityManagerFactory("arkmino");
    }

    public boolean crearDino(Dinosaurios dino) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(dino);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al crear dinosaurio: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    public Dinosaurios buscarDinoPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Dinosaurios.class, id);
        } finally {
            em.close();
        }
    }

    public Dinosaurios buscarDinosaurioPorNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Dinosaurios> query = em.createQuery(
                    "SELECT d FROM Dinosaurios d LEFT JOIN FETCH d.habitat WHERE d.nombre = :nombre",
                    Dinosaurios.class);
            query.setParameter("nombre", nombre);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public boolean actualizarDino(Dinosaurios dino) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(dino);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al actualizar: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    public boolean eliminarDinosaurio(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Dinosaurios dino = em.find(Dinosaurios.class, id);
            if (dino != null) {
                em.remove(dino);
                em.getTransaction().commit();
                return true;
            }
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    public List<Dinosaurios> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT d FROM Dinosaurios d", Dinosaurios.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void eliminarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Dinosaurios").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar todos: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
