package net.xaviersala.Batalla;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Camp de batalla.
 *
 * @author xavier
 *
 */
public class CampdeBatalla {
    /**
     * Temps d'espera entre passades.
     */
    private static final int TEMPSESPERA = 80;

    /**
     * Número de files del terreny.
     */
    private static final int FILESTERRENY = 6;

    /**
     * Numero de files del camp de batalla.
     */
    private int filesTerreny;

    /**
     * Llista d'exèrcits.
     */
    private List<Exercit> exercits;

    /**
     * Pantalla on pintar.
     */
    private App pantalla;

    /**
     * Crea el camp de batalla i l'associa a el Canvas actual.
     *
     * @param pant Canvas on pintar els soldats
     */

    public CampdeBatalla(final App pant) {
        pantalla = pant;
        filesTerreny = FILESTERRENY;
        exercits = new ArrayList<Exercit>();
    }

    /**
     * Afegir un exèrcit al camp de batalla.
     *
     * @param ex Exercit que es vol afegir
     * @param posicioi Posició inicial de l'exèrcit
     * @param posiciof Posició final de l'exèrcit
     */
    public final void afegirExercit(final Exercit ex,
            final int posicioi, final int posiciof) {
        if (ex != null) {
            exercits.add(ex);
            ex.setMidaCampBatalla(pantalla.getWidth());
            ex.setPosicio(posicioi, posiciof);
            ex.soldatsFormacio(filesTerreny);
            pintaImatges(exercits.size() - 1);
        }
    }

    /**
     * Pinta les imatges d'un exèrcit en el Canvas.
     *
     * @param numExercit Número d'exèrcit
     */
    private void pintaImatges(final int numExercit) {

        List<Soldat> imatges = exercits.get(numExercit).getSoldats();
        for (Soldat un : imatges) {
            pantalla.add(un.getImatge());
        }
    }

    /**
     * Comença la batalla entre els exèrcits.
     *
     * Bàsicament es fan atacar els exèrcits i es comproven
     * els morts després de cada avançada.
     *
     * També ha de comprovar si un exèrcit ha vist reduits els
     * seus soldats de manera que no pugui omplir les files. Si
     * això passa es redueixen les files perquè els exèrcits es
     * trobin més fàcilment
     */
    public final void batalla() {
        Random r = new Random();

        while (exercits.get(0).getNumeroDeSoldats() > 0
            && exercits.get(1).getNumeroDeSoldats() > 0) {

            exercits.get(0).soldatsAtacar();
            exercits.get(1).rebreAtac(exercits.get(0));
            exercits.get(1).soldatsAtacar();
            exercits.get(0).rebreAtac(exercits.get(1));
            pantalla.pause(TEMPSESPERA);

            // Comprovar si s'han de reduïr les files

            int minim = Math.min(exercits.get(0).getNumeroDeSoldats(),
                                 exercits.get(1).getNumeroDeSoldats());

            if (minim < FILESTERRENY) {
                exercits.get(0).setFilesExercit(minim);
                exercits.get(1).setFilesExercit(minim);
            }

        }

    }
}
