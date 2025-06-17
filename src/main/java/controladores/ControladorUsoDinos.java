/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import entidades.Uso_Dinos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ailin
 */
public class ControladorUsoDinos {

    private final EntityManagerFactory emf;

    public ControladorUsoDinos() {
        emf = Persistence.createEntityManagerFactory("dinosauriosPU");
    }

    public boolean crearUsoDino(Uso_Dinos usoDino) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usoDino);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error al crear uso de dino: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public Uso_Dinos buscarUsoDinoPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Uso_Dinos.class, id);
        } finally {
            em.close();
        }
    }

    public boolean actualizarUsoDino(Uso_Dinos usoDino) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usoDino);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar uso de dino: " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public boolean eliminarUsoDino(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Uso_Dinos usoDino = em.find(Uso_Dinos.class, id);
            if (usoDino != null) {
                em.remove(usoDino);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error al eliminar uso de dino: " + e.getMessage());
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
