/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ailin
 */
@Entity
@Table(name = "Dino_Habitat")
public class Dino_Habitat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_HabitatDino;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_Dino", nullable = false)
    private Dinosaurios dino;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_Habitat", nullable = false)
    private Habitats habitat;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInsertado;

    public Integer getId_HabitatDino() {
        return id_HabitatDino;
    }

    public void setId_HabitatDino(Integer id_HabitatDino) {
        this.id_HabitatDino = id_HabitatDino;
    }

    public Dinosaurios getDino() {
        return dino;
    }

    public void setDino(Dinosaurios dino) {
        this.dino = dino;
    }

    public Habitats getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitats habitat) {
        this.habitat = habitat;
    }

    public Date getFechaInsertado() {
        return fechaInsertado;
    }

    public void setFechaInsertado(Date fechaInsertado) {
        this.fechaInsertado = fechaInsertado;
    }
}
