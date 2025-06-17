/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import entidades.Habitats;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ailin
 */
public class ControladorHabitats {

    private final EntityManagerFactory emf;

    public ControladorHabitats() {
        emf = Persistence.createEntityManagerFactory("dinosauriosPU");
    }

    public boolean crearHabitat(Habitats habitat) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(habitat);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error al crear habitat: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public Habitats buscarHabitatPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Habitats.class, id);
        } finally {
            em.close();
        }
    }

    public boolean actualizarHabitat(Habitats habitat) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(habitat);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar habitat: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public boolean eliminarHabitat(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Habitats habitat = em.find(Habitats.class, id);
            if (habitat != null) {
                em.remove(habitat);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error al eliminar habitat: " + e.getMessage());
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
            emf.close();
        }
    }
}
