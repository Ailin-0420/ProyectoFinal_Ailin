/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ailin
 */
@Entity
@Table(name = "Habitats")
public class Habitats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Habitat;

    private String coordenadas;

    public Habitats() {}

    public int getId_Habitat() { return id_Habitat; }
    public void setId_Habitat(int id_Habitat) { this.id_Habitat = id_Habitat; }

    public String getCoordenadas() { return coordenadas; }
    public void setCoordenadas(String coordenadas) { this.coordenadas = coordenadas; }
}
