/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author ailin
 */
@Entity
@Table(name = "Dinosaurios")
public class Dinosaurios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Dino;

    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_DietaGeneral")
    private String tipo_DietaGeneral;

    private String preferencia_Alimento;

    private boolean domesticable;

    @OneToOne(mappedBy = "dinosaurio")
    private Dino_Habitat habitat;

    public Dinosaurios() {
    }

    public Dinosaurios(int id_Dino, String nombre, String tipo_DietaGeneral, String preferencia_Alimento, boolean domesticable, Dino_Habitat habitat) {
        this.id_Dino = id_Dino;
        this.nombre = nombre;
        this.tipo_DietaGeneral = tipo_DietaGeneral;
        this.preferencia_Alimento = preferencia_Alimento;
        this.domesticable = domesticable;
        this.habitat = habitat;
    } 

    public int getId_Dino() {
        return id_Dino;
    }

    public void setId_Dino(int id_Dino) {
        this.id_Dino = id_Dino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_DietaGeneral() {
        return tipo_DietaGeneral;
    }

    public void setTipo_DietaGeneral(String tipo_DietaGeneral) {
        this.tipo_DietaGeneral = tipo_DietaGeneral;
    }

    public String getPreferencia_Alimento() {
        return preferencia_Alimento;
    }

    public void setPreferencia_Alimento(String preferencia_Alimento) {
        this.preferencia_Alimento = preferencia_Alimento;
    }

    public boolean isDomesticable() {
        return domesticable;
    }

    public void setDomesticable(boolean domesticable) {
        this.domesticable = domesticable;
    }

    public Dino_Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Dino_Habitat habitat) {
        this.habitat = habitat;
    }

    @Override
    public String toString() {
        return "Dinosaurios{"
                + "id_Dino=" + id_Dino
                + ", nombre='" + nombre + '\''
                + ", tipo_DietaGeneral=" + tipo_DietaGeneral
                + ", preferencia_Alimento='" + preferencia_Alimento + '\''
                + ", domesticable=" + domesticable
                + '}';
    }

    public enum TipoDieta {
        herbívoro,
        carnívoro,
        omnívoro,
        otro
    }
}
