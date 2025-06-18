/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import entidades.Habitats;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ailin
 */
public class ControladorHabitats {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ControladorHabitats() {
        emf = Persistence.createEntityManagerFactory("arkmino");
        em = emf.createEntityManager();
    }

    public boolean crearHabitat(Habitats habitat) {
        try {
            em.getTransaction().begin();
            em.persist(habitat);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public Habitats buscarHabitatPorNombre(String nombre) {
        try {
            return em.createQuery("SELECT h FROM Habitats h WHERE h.texto_Habitat = :nombre", Habitats.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Habitats buscarHabitatPorId(int id) {
        try {
            return em.find(Habitats.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean actualizarHabitat(Habitats habitat) {
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
        }
    }

    public boolean eliminarHabitat(int id) {
        EntityManager emLocal = emf.createEntityManager();
        try {
            emLocal.getTransaction().begin();
            Habitats habitat = emLocal.find(Habitats.class, id);
            if (habitat != null) {
                emLocal.remove(habitat);
                emLocal.getTransaction().commit();
                return true;
            }
            emLocal.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            if (emLocal.getTransaction().isActive()) {
                emLocal.getTransaction().rollback();
            }
            return false;
        } finally {
            emLocal.close();
        }
    }

    public List<Habitats> obtenerTodos() {
        return em.createQuery("SELECT h FROM Habitats h", Habitats.class).getResultList();
    }

    public void eliminarTodos() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Habitats").executeUpdate();
        em.getTransaction().commit();
    }

    public void cerrar() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
