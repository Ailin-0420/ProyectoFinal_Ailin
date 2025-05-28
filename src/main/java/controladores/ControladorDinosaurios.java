/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import entidades.Dinosaurios;
import java.awt.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ailin
 */
public class ControladorDinosaurios {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("arkmino");

    public void crearDino(Dinosaurios dino) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(dino);
        em.getTransaction().commit();
        em.close();
    }

    public List<Dinosaurios> obtenerTodos() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("arkmino");
    EntityManager em = emf.createEntityManager();
        var lista = em.createQuery("SELECT d FROM Dinosaurios d", Dinosaurios.class).getResultList();
    em.close();
    emf.close();
    return lista;
    }


    public void cerrar() {
        emf.close();
    }
}

