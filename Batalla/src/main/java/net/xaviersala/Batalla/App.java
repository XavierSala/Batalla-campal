package net.xaviersala.Batalla;

import java.util.Random;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

/**
 * Hello world.
 *
 */
public class App  extends GraphicsProgram {
    /**
     * Marge per separar una mica els soldats del costat.
     */
    private static final int MARGEINICIAL = 10;
    /**
     * Altura de la pantalla.
     */
    private static final int ALTURAPANTALLA = 500;
    /**
     * Amplada de la pantalla.
     */
    private static final int AMPLADAPANTALLA = 800;
    /**
     * Número de soldats de cada exèrcit.
     */
    private static final int NUMEROSOLDATS = 12;
    /**
     *
     */
    private static final long serialVersionUID = 8241782044916223928L;

    /**
     * Executar el programa.
     */
    public final void run() {
        setSize(AMPLADAPANTALLA, ALTURAPANTALLA);
        CampdeBatalla hastings = new CampdeBatalla(this);

        String[] noms = {"soldat.png"};
        Exercit exercit = creaExercit("Imperi", NUMEROSOLDATS, noms);

        hastings.afegirExercit(exercit, MARGEINICIAL, getWidth());

        String[] noms2 = {"guerrer2.png", "guerrer3.png",
                "guerrer4.png", "guerrer5.png"};
        exercit = creaExercit("TrencaOssos", NUMEROSOLDATS, noms2);

        hastings.afegirExercit(exercit, getWidth(), MARGEINICIAL);
        hastings.batalla();

    }

   /**
    * Crea un exèrcit a partir de les dades que se li introdueixen.
    *
    * @param nom Nom de l'exèrcit
    * @param numSoldats Número de soldats
    * @param fitxers Fitxers d'imatges a fer servir en aquest exèrcit
    * @return Retorna l'exèrcit creat
    */
    public final Exercit creaExercit(final String nom,
            final int numSoldats, final String[] fitxers) {

        Random r = new Random();
        GImage[] imatges = new GImage[fitxers.length];
        int i = 0;
        for (String fitxer: fitxers) {
            imatges[i] = new GImage(fitxer);
            i++;
        }

        Exercit x = new Exercit(nom, AMPLADAPANTALLA);

        // Allistar soldats
        Soldat deplom = null;
        for (int s = 0; s < numSoldats; s++) {
            if (fitxers.length == 0) {
                deplom = new Soldat();
            } else {
                int quina = r.nextInt(imatges.length);
                deplom = new Soldat(
                        imatges[quina].getImage());
            }
            x.allistarSoldat(deplom);
        }

        return x;

    }
}
