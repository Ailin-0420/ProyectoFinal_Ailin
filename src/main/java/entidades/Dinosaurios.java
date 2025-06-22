/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private String tipo_DietaGeneral;
    private String preferencia_Alimento;
    private boolean domesticable;

    // Relaciones
    @OneToMany(mappedBy = "dino")
    private List<Dino_Habitat> historialHabitats;

    // Constructor vacío obligatorio
    public Dinosaurios() {}

    // Getters y setters
    public int getId_Dino() { return id_Dino; }
    public void setId_Dino(int id_Dino) { this.id_Dino = id_Dino; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo_DietaGeneral() { return tipo_DietaGeneral; }
    public void setTipo_DietaGeneral(String tipo_DietaGeneral) { this.tipo_DietaGeneral = tipo_DietaGeneral; }

    public String getPreferencia_Alimento() { return preferencia_Alimento; }
    public void setPreferencia_Alimento(String preferencia_Alimento) { this.preferencia_Alimento = preferencia_Alimento; }

    public boolean isDomesticable() { return domesticable; }
    public void setDomesticable(boolean domesticable) { this.domesticable = domesticable; }
    
    public List<Dino_Habitat> getHistorialHabitats() { return historialHabitats; }

    public void setHistorialHabitats(List<Dino_Habitat> historialHabitats) { this.historialHabitats = historialHabitats; }

    @Override
    public String toString() {
        return "Dinosaurio = '" + nombre + "'}";
    }
}
