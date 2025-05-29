/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import entidades.Dino_Habitat;
import entidades.Dinosaurios;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author ailin
 */
public class ControladorHabitatDino {

    private EntityManagerFactory emf;

    public ControladorHabitatDino() {
        emf = Persistence.createEntityManagerFactory("dinosauriosPU");
    }

    public boolean crearHabitat(Dino_Habitat habitat) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(habitat);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    // Obtener habitat por dinosaurio
    public Dino_Habitat obtenerHabitatPorDino(Dinosaurios dino) {
        EntityManager em = emf.createEntityManager();
        Dino_Habitat habitat = null;
        try {
            TypedQuery<Dino_Habitat> query = em.createQuery(
                    "SELECT h FROM Dino_Habitat h WHERE h.dinosaurio = :dino", Dino_Habitat.class);
            query.setParameter("dino", dino);
            habitat = query.getSingleResult();
        } catch (Exception e) {
            // Puede lanzar NoResultException si no encuentra nada
            // Puedes manejarlo para devolver null si quieres
            // e.printStackTrace();
        } finally {
            em.close();
        }
        return habitat;
    }

    // Actualizar habitat
    public boolean actualizarHabitat(Dino_Habitat habitat) {
        EntityManager em = emf.createEntityManager();
        boolean exito = false;
        try {
            em.getTransaction().begin();
            em.merge(habitat);
            em.getTransaction().commit();
            exito = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
        return exito;
    }

}
