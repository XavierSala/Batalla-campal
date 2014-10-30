package net.xaviersala.Batalla;

import java.util.List;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

/**
 * Hello world.
 *
 */
public class App  extends GraphicsProgram {
    /**
     * Número de soldats forts.
     */
    private static final int NUMEROFORTS = 2;
    /**
     * Número d'herois a crear.
     */
    private static final int NUMEROHEROIS = 5;
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
    private static final int AMPLADAPANTALLA = 1000;
    /**
     * Número de soldats de cada exèrcit.
     */
    private static final int NUMEROSOLDATS = 30;
    /**
     *
     */
    private static final long serialVersionUID = 8241782044916223928L;

    /**
     * Executar el programa.
     */
    public final void run() {
        setSize(AMPLADAPANTALLA, ALTURAPANTALLA);
        CampdeBatalla waterloo = new CampdeBatalla(AMPLADAPANTALLA,
                ALTURAPANTALLA);

        String[] noms = {"soldat.png"};
        String[] herois1 = {"soldat-hero.png"};
        String fort1 = "soldat-hero2.png";
        String rei1 = "soldat-cap.png";

        Exercit exercit = creaExercit("Imperi", NUMEROSOLDATS,
                noms, herois1, fort1, rei1);
        waterloo.afegirExercit(exercit, MARGEINICIAL, getWidth());


        String[] noms2 = {"guerrer1.png", "guerrer2.png", "guerrer3.png",
                "guerrer4.png", "guerrer5.png", "guerrer6.png"};
        String[] herois2 = {"guerrer-hero.png", "guerrer-hero2.png"};
        String fort2 = "guerrer-dur.png";
        String rei2 = "guerrer-cap.png";

        exercit = creaExercit("TrencaOssos", NUMEROSOLDATS,
                noms2, herois2, fort2, rei2);
        waterloo.afegirExercit(exercit, getWidth(), MARGEINICIAL);

        clicaPerComencar();

        waterloo.batalla();

    }

   /**
    * Crea un exèrcit a partir de les dades que se li introdueixen.
    *
    * @param nom Nom de l'exèrcit
    * @param numSoldats Número de soldats
    * @param fitxers Fitxers d'imatges a fer servir en aquest exèrcit
    * @param herois Fitxers d'imatges amb els herois
    * @param fort Soldat fort
    * @param rei1 rei de l'exèrcit
    * @return Retorna l'exèrcit creat
    */
    public final Exercit creaExercit(final String nom,
            final int numSoldats, final String[] fitxers,
            final String[] herois, final String fort, final String rei1) {



        Exercit x = new Exercit(nom, AMPLADAPANTALLA);

//        // Canó
//         x.allistarSoldat(new SoldatCano(
//                new GImage("cano.png").getImage(), "bala.png",
//                AMPLADAPANTALLA, ALTURAPANTALLA));

        // Apuntar el rei
        x.allistarSoldat(new SoldatCap(
                new GImage(rei1).getImage(), ALTURAPANTALLA));
        // Allistar fort
        for (int i = 0; i < NUMEROFORTS; i++) {
            x.allistarSoldat(new SoldatFort(
                    new GImage(fort).getImage()));
        }
        // Allistar Herois
        for (int i = 0; i < NUMEROHEROIS; i++) {
            int tria = Aleatori.obtenir(herois.length);
            x.allistarSoldat(new SoldatHeroi(
                    new GImage(herois[tria]).getImage()));
        }
        // Allistar soldats
        GImage[] imatges = new GImage[fitxers.length];
        int i = 0;
        for (String fitxer: fitxers) {
            imatges[i] = new GImage(fitxer);
            i++;
        }

        SoldatNormal deplom = null;
        for (int s = 0; s < numSoldats; s++) {
            if (fitxers.length == 0) {
                deplom = new SoldatNormal();
            } else {
                int quina = Aleatori.obtenir(imatges.length);
                deplom = new SoldatNormal(
                        imatges[quina].getImage());
            }
            x.allistarSoldat(deplom);
        }

        // Pintar l'exèrcit...
        pintaImatges(x);

        return x;

    }

    /**
     * Pintar l'exèrcit per pantalla.
     * @param ex exèrcit a pintar
     */
    private void pintaImatges(final Exercit ex) {

        List<Soldat> imatges = ex.getSoldats();
        for (Soldat un : imatges) {
            add(un.getImatge());
        }
    }


    /**
     * Clica per començar.
     */
    private void clicaPerComencar() {
        GLabel label = new GLabel("Clica per començar");
        double x = (getWidth() - label.getWidth()) / 2;
        double y = (getHeight() + label.getAscent()) / 2;
        add(label, x, y);
        waitForClick();
        remove(label);
    }

}
