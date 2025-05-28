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
public class ControladorDinosaurios {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("arkmino");

    // Añadir un dinosaurio
    public boolean crearDino(Dinosaurios dino) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(dino);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.err.println("Error al guardar: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    // Buscar por id
    public Dinosaurios buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        Dinosaurios dino = em.find(Dinosaurios.class, id);
        em.close();
        return dino;
    }

    // Modificar dinosaurio
    public void modificar(Dinosaurios dino) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(dino);
        em.getTransaction().commit();
        em.close();
    }

    // Eliminar por id
    public boolean eliminarDinosaurioPorId(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TU_PERSISTENCE_UNIT");
        EntityManager em = emf.createEntityManager();
        try {
            Dinosaurios dino = em.find(Dinosaurios.class, id);
            if (dino != null) {
                em.getTransaction().begin();
                em.remove(dino);
                em.getTransaction().commit();
                return true;
            }
        } finally {
            em.close();
            emf.close();
        }
        return false;
    }

    public Dinosaurios obtenerDinoPorId(int id) {
        EntityManager em = emf.createEntityManager();
        Dinosaurios dino = null;

        try {
            dino = em.find(Dinosaurios.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return dino;
    }

    public boolean actualizarDino(Dinosaurios dino) {
        EntityManager em = emf.createEntityManager();
        boolean exito = false;

        try {
            em.getTransaction().begin();
            em.merge(dino);
            em.getTransaction().commit();
            exito = true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return exito;
    }

    public void insertar(Dinosaurios dino) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
