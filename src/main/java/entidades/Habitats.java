/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Ailin
 */
@Entity
@Table(name = "Habitats")
public class Habitats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Habitat")
    private int id_Habitat;

    @Column(name = "texto_Habitat", nullable = false, unique = true)
    private String texto_Habitat;

    @OneToMany(mappedBy = "habitat")
    private List<Dino_Habitat> historialDinos;

    // Getters y setters
    public int getId_Habitat() { return id_Habitat; }
    public void setId_Habitat(int id_Habitat) { this.id_Habitat = id_Habitat; }

    public String getTexto_Habitat() { return texto_Habitat; }
    public void setTexto_Habitat(String texto_Habitat) { this.texto_Habitat = texto_Habitat; }

    public List<Dino_Habitat> getHistorialDinos() { return historialDinos; }
    public void setHistorialDinos(List<Dino_Habitat> historialDinos) { this.historialDinos = historialDinos; }

    @Override
    public String toString() {
        return "Habitats{" +
                "texto_Habitat='" + texto_Habitat + '\'' +
                '}';
    }
}