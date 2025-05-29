/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.Date;
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
public class Dino_Habitat{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_HabitatDino;

    @ManyToOne
    @JoinColumn(name = "id_Dino")
    private Dinosaurios dinosaurio;

    @Temporal(TemporalType.DATE)
    private Date fechaInsertado;

    private int porcentaje_Aparicion;

    private String coordenadas_Aparicion;

    public Dino_Habitat() {}

    public int getId_HabitatDino() { return id_HabitatDino; }
    public void setId_HabitatDino(int id_HabitatDino) { this.id_HabitatDino = id_HabitatDino; }

    public Dinosaurios getDinosaurio() { return dinosaurio; }
    public void setDinosaurio(Dinosaurios dinosaurio) { this.dinosaurio = dinosaurio; }

    public Date getFechaInsertado() { return fechaInsertado; }
    public void setFechaInsertado(Date fechaInsertado) { this.fechaInsertado = fechaInsertado; }

    public int getPorcentaje_Aparicion() { return porcentaje_Aparicion; }
    public void setPorcentaje_Aparicion(int porcentaje_Aparicion) { this.porcentaje_Aparicion = porcentaje_Aparicion; }

    public String getCoordenadas_Aparicion() { return coordenadas_Aparicion; }
    public void setCoordenadas_Aparicion(String coordenadas_Aparicion) { this.coordenadas_Aparicion = coordenadas_Aparicion; }
}
