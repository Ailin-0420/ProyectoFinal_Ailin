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
    private List<Dinosaurios> dinosaurios;

    public int getId_Habitat() {
        return id_Habitat;
    }

    public void setId_Habitat(int id_Habitat) {
        this.id_Habitat = id_Habitat;
    }

    public String getTexto_Habitat() {
        return texto_Habitat;
    }

    public void setTexto_Habitat(String texto_Habitat) {
        this.texto_Habitat = texto_Habitat;
    }

    public List<Dinosaurios> getDinosaurios() {
        return dinosaurios;
    }

    public void setDinosaurios(List<Dinosaurios> dinosaurios) {
        this.dinosaurios = dinosaurios;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Habitats{");
        sb.append("texto_Habitat=").append(texto_Habitat + "\n");
        sb.append(", dinosaurios=").append(dinosaurios + "\n");
        sb.append('}');
        return sb.toString();
    }
}
