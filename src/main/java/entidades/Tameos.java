/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ailin
 */
@Entity
@Table(name = "Tameos")
public class Tameos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Tameo;

    @ManyToOne
    @JoinColumn(name = "id_Dino")
    private Dinosaurios dinosaurio;

    private String metodo_Usado;

    private double tiempo;

    public Tameos() {}

    // Getters y setters
    public int getId_Tameo() { return id_Tameo; }
    public void setId_Tameo(int id_Tameo) { this.id_Tameo = id_Tameo; }

    public Dinosaurios getDinosaurio() { return dinosaurio; }
    public void setDinosaurio(Dinosaurios dinosaurio) { this.dinosaurio = dinosaurio; }

    public String getMetodo_Usado() { return metodo_Usado; }
    public void setMetodo_Usado(String metodo_Usado) { this.metodo_Usado = metodo_Usado; }

    public double getTiempo() { return tiempo; }
    public void setTiempo(double tiempo) { this.tiempo = tiempo; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tameos{");
        sb.append("metodo_Usado=").append(metodo_Usado);
        sb.append('}');
        return sb.toString();
    }
}