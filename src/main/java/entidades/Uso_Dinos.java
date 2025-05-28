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
@Table(name = "Uso_Dinos")
public class Uso_Dinos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Uso;

    @ManyToOne
    @JoinColumn(name = "id_Dino")
    private Dinosaurios dinosaurio;

    private String metodo_Usado;

    private String tiempo;

    public Uso_Dinos() {}

    public int getId_Uso() { return id_Uso; }
    public void setId_Uso(int id_Uso) { this.id_Uso = id_Uso; }

    public Dinosaurios getDinosaurio() { return dinosaurio; }
    public void setDinosaurio(Dinosaurios dinosaurio) { this.dinosaurio = dinosaurio; }

    public String getMetodo_Usado() { return metodo_Usado; }
    public void setMetodo_Usado(String metodo_Usado) { this.metodo_Usado = metodo_Usado; }

    public String getTiempo() { return tiempo; }
    public void setTiempo(String tiempo) { this.tiempo = tiempo; }
}
