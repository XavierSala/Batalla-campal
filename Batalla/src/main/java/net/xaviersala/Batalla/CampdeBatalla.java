package net.xaviersala.Batalla;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import acm.graphics.GCanvas;
import acm.graphics.GImage;

public class CampdeBatalla {
    /**
     * Número de files del terreny
     */
    private static final int FILESTERRENY = 8;
    /**
     * Numero de files del camp de batalla.
     */
    int liniesDeBatalla;
    /**
     * Llista d'exèrcits.
     */
    List<Exercit> exercits;

    GCanvas pantalla;

    /**
     * Crea el camp de pantalla i l'associa a el Canvas actual.
     *
     * @param pantalla Canvas on pintar els soldats
     */

    public CampdeBatalla(GCanvas pant) {
        pantalla = pant;
        liniesDeBatalla = FILESTERRENY;
        exercits = new ArrayList<Exercit>();
    }

 /**
  * Crea un exèrcit a partir dels paràmetres especificats
  * @param nom Nom de l'exèrcit
  * @param numSoldats Número de soldats a crear
  * @param fitxers Imatges que composen els soldats de l'exèrcit
  */
    public void creaExercit(
            String nom,
            int numSoldats,
            String[] fitxers) {

        List<Image> fitxersImatges = carregarImatges(fitxers);
        exercits.add(new Exercit(nom, fitxersImatges, numSoldats));
        exercits.get(exercits.size()-1).formacio(5);
        pintaImatges(exercits.size()-1);
    }


/**
 * Carrega les imatges especificades en l'Array en un ArrayList
 * de objecte java.awt.Image
 *
 * @param fitxers Llista de noms d'imatge
 * @return Llista d'objectes Image
 */
private List<Image> carregarImatges(String[] fitxers) {

    List<Image> fitxersImatges = new ArrayList<Image>();

    for (String fitxer: fitxers) {
        fitxersImatges.add(new GImage(fitxer).getImage());
    }
    return fitxersImatges;
}


/**
 * Pinta les imatges d'un exèrcit en el Canvas
 * @param numExercit
 */
private void pintaImatges(int numExercit) {
    // Pintar les imatges al canvas
    List<GImage> imatges = exercits.get(numExercit).getImatges();
    for(GImage imatge: imatges) {
        pantalla.add(imatge);
    }
}

    /**
     * Lluita!
     */
    public void batalla() {

    }
}
