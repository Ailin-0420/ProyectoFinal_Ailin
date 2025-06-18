/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parteCopiaSeguridad;

import controladores.ControladorDinosaurios;
import controladores.ControladorHabitats;
import entidades.Dinosaurios;
import entidades.Habitats;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ailin
 */
public class CopiaSeguridad {
    
    public static File crearCarpetaBackup() {
        String formatoFecha = "yyyy-MM-dd_HH-mm-ss";
        String nombreCarpeta = "backup_" + new SimpleDateFormat(formatoFecha).format(new Date());
        File carpeta = new File(nombreCarpeta);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        return carpeta;
    }
    
    public static void exportarDinosauriosCSV(List<Dinosaurios> dinoList, File carpeta) throws IOException {
        File file = new File(carpeta, "dinosaurios.csv");
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("id,nombre,tipo_DietaGeneral,preferencia_Alimento,domesticable,id_Habitat\n");
            for (Dinosaurios d : dinoList) {
                fw.write(d.getId_Dino() + "," +
                        comasSaltos(d.getNombre()) + "," +
                        comasSaltos(d.getTipo_DietaGeneral()) + "," +
                        comasSaltos(d.getPreferencia_Alimento()) + "," +
                        (d.isDomesticable() ? "1" : "0") + "," +
                        (d.getHabitat() != null ? d.getHabitat().getId_Habitat() : "") + "\n");
            }
        }
    }
    
    public static void exportarHabitatsCSV(List<Habitats> habitatsList, File carpeta) throws IOException {
        File file = new File(carpeta, "habitats.csv");
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("id,texto_Habitat\n");
            for (Habitats h : habitatsList) {
                fw.write(h.getId_Habitat() + "," + comasSaltos(h.getTexto_Habitat()) + "\n");
            }
        }
    }
    
    // Método para escapar comas y saltos de línea en CSV MUY IMPORYTANTE
    private static String comasSaltos(String texto) {
        if (texto == null) return "";
        if (texto.contains(",") || texto.contains("\n")) {
            texto = texto.replace("\"", "\"\"");
            return "\"" + texto + "\"";
        }
        return texto;
    }
    
    public static void hacerBackup() throws IOException {
        ControladorDinosaurios controlador1 = new ControladorDinosaurios();
        ControladorHabitats controlador2 = new ControladorHabitats();

        List<Dinosaurios> listaDino = controlador1.obtenerTodos();
        List<Habitats> listaHab = controlador2.obtenerTodos();

        File carpetaBackup = crearCarpetaBackup();

        exportarDinosauriosCSV(listaDino, carpetaBackup);
        exportarHabitatsCSV(listaHab, carpetaBackup);

        controlador1.cerrar();
        controlador2.cerrar();
    }
    
    public static void restaurarUltimaCopia() throws IOException {
        ControladorDinosaurios cd = new ControladorDinosaurios();
        ControladorHabitats ch = new ControladorHabitats();

        // Buscar la carpeta backup más reciente
        File carpetaBackup = buscarUltimaCarpetaBackup();
        if (carpetaBackup == null) {
            System.out.println("No se encontró ninguna carpeta de backup.");
            return;
        }

        // Eliminar datos actuales
        cd.eliminarTodos();
        ch.eliminarTodos();

        // Leer habitats.csv y crear en BD
        File fileHab = new File(carpetaBackup, "habitats.csv");
        Map<Integer, Habitats> mapaHabitats = new HashMap<>(); // para relacionar dinosaurios después
        try (BufferedReader br = new BufferedReader(new FileReader(fileHab))) {
            String line = br.readLine(); // salto cabecera
            while ((line = br.readLine()) != null) {
                String[] campos = parseCSVLine(line);
                int idHab = Integer.parseInt(campos[0]);
                String textoHab = campos[1];
                Habitats h = new Habitats();
                h.setId_Habitat(idHab);
                h.setTexto_Habitat(textoHab);
                ch.crearHabitat(h);
                mapaHabitats.put(idHab, h);
            }
        }

        // Leer dinosaurios.csv y crear en BD
        File fileDino = new File(carpetaBackup, "dinosaurios.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(fileDino))) {
            String line = br.readLine(); // salto cabecera
            while ((line = br.readLine()) != null) {
                String[] campos = parseCSVLine(line);
                Dinosaurios d = new Dinosaurios();
                d.setId_Dino(Integer.parseInt(campos[0]));
                d.setNombre(campos[1]);
                d.setTipo_DietaGeneral(campos[2]);
                d.setPreferencia_Alimento(campos[3]);
                d.setDomesticable("1".equals(campos[4]));
                if (!campos[5].isEmpty()) {
                    int idHab = Integer.parseInt(campos[5]);
                    d.setHabitat(mapaHabitats.get(idHab));
                }
                cd.crearDino(d);
            }
        }

        cd.cerrar();
        ch.cerrar();
    }

    private static File buscarUltimaCarpetaBackup() {
        File dir = new File(".");
        File[] backups = dir.listFiles(f -> f.isDirectory() && f.getName().startsWith("backup_"));
        if (backups == null || backups.length == 0) return null;
        Arrays.sort(backups, Comparator.comparing(File::getName).reversed());
        return backups[0];
    }

    // Método simple para separar CSV respetando comillas dobles
    private static String[] parseCSVLine(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean entreComillas = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                entreComillas = !entreComillas;
            } else if (c == ',' && !entreComillas) {
                tokens.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        tokens.add(sb.toString());
        // Remover comillas dobles internas y doble comilla
        for (int i = 0; i < tokens.size(); i++) {
            String t = tokens.get(i);
            if (t.startsWith("\"") && t.endsWith("\"")) {
                t = t.substring(1, t.length() - 1).replace("\"\"", "\"");
                tokens.set(i, t);
            }
        }
        return tokens.toArray(new String[0]);
    }
}
