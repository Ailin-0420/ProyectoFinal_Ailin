/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import entidades.Tameos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ailin
 */
public class ControladorTameos {

    private final EntityManagerFactory emf;

    // Constructor: Inicia la conexión con la base de datos al crear el controlador
    public ControladorTameos() {
        this.emf = Persistence.createEntityManagerFactory("arkmino"); // "arkmino" = nombre de la BD
    }

    public boolean crearTameo(Tameos tameo) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tameo);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error al crear tameo: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public Tameos buscarTameoPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Tameos.class, id);
        } finally {
            em.close();
        }
    }

    public boolean actualizarTameo(Tameos tameo) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(tameo);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar tameo: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public boolean eliminarTameo(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Tameos tameo = em.find(Tameos.class, id);
            if (tameo != null) {
                em.remove(tameo);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error al eliminar tameo: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close(); // Cierra la conexión principal
        }
    }
}  

