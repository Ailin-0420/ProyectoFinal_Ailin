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
    private int id_HabitatDino;

    @ManyToOne
    @JoinColumn(name = "id_Dino")
    private Dinosaurios dinosaurio;

    @Temporal(TemporalType.DATE)
    private Date fechaInsertado;

    @Column(unique = true)
    private String texto_Habitat;

    public String getTexto_Habitat() {
        return texto_Habitat;
    }

    public void setTexto_Habitat(String texto_Habitat) {
        this.texto_Habitat = texto_Habitat;
    }

    // Constructor, getters y setters
    public Dino_Habitat() {
    }

    public int getId_HabitatDino() {
        return id_HabitatDino;
    }

    public void setId_HabitatDino(int id_HabitatDino) {
        this.id_HabitatDino = id_HabitatDino;
    }

    public Dinosaurios getDinosaurio() {
        return dinosaurio;
    }

    public void setDinosaurio(Dinosaurios dinosaurio) {
        this.dinosaurio = dinosaurio;
    }

    public Date getFechaInsertado() {
        return fechaInsertado;
    }

    public void setFechaInsertado(Date fechaInsertado) {
        this.fechaInsertado = fechaInsertado;
    }
}
