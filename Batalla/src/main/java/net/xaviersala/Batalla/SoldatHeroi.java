/**
 *
 */
package net.xaviersala.Batalla;

import java.awt.Image;

/**
 * L'heroi és un soldat que en comptes de moure's en línia recta
 * com els altres es mou entre línies canviant d'una a l'altra
 * fins que arriba al destí X.
 *
 *  - Ha de saber les files que tenim...
 *
 * @author xavier
 *
 */
public class SoldatHeroi extends SoldatGlobal {

    /**
     * Files en que forma l'exercit per defecte.
     */
    private static final int FILESPERDEFECTE = 6;

    /**
     * Valor màxim per fer la provabilitat.
     */
    private static final int VALORMAXIM = 100;

    /**
     * Provabilitat de canviar de fila.
     */
    private static final int PROVABILITATDECANVIAR = 1;

    /**
     * Velocitat màxima a la que es mou el soldat.
     */
    private static final int VELOCITATMAXIMA = 10;


    /**
     * Número de files del camp de batalla.
     */
    private int numeroDeFiles;

    /**
     * Crea l'heroi a partir del seu dibuix.
     * @param dibuix Imatge de l'heroi
     */
    public SoldatHeroi(final Image dibuix) {
        super(dibuix);
        numeroDeFiles = FILESPERDEFECTE;
    }

    /* (non-Javadoc)
     * @see net.xaviersala.Batalla.SoldatGlobal#mou()
     */

    /**
     * Mou el soldat cap al seu objectiu.
     *
     * Necessito saber:
     *   - El número de files actual
     *   - L'altura de cada fila (que és una constant)
     *
     * @return retorna un 0 si el soldat no s'ha mogut
     * o un 1 si s'ha mogut
     */
    @Override
    public final int mou() {

        int dirX = getDireccioX();
        int dirY = getDireccioY();



            if (numeroDeFiles != 1) {
                /**
                 * Vol canviar de fila, puja o baixa?
                */
                if (Aleatori.obtenir(VALORMAXIM) < PROVABILITATDECANVIAR) {
                    canviaDeFila();
                }

            }

        getImatge().move(dirX * Aleatori.obtenir(VELOCITATMAXIMA),
                dirY * Aleatori.obtenir(VELOCITATMAXIMA));

        setDireccioX(calculaDireccioX(VELOCITATMAXIMA));
        setDireccioY(calculaDireccioY(VELOCITATMAXIMA));

        // Si estem al destí les direccions seran zero...
        if (getDireccioX() + getDireccioY() == 0) {
            return 0;
        }
        return 1;

    }

    /**
     * Canvia de fila amunt o aval en funció de un valor
     * aleatòri.
     */
    private void canviaDeFila() {
        int puja = Aleatori.obtenir(2);
        if (puja == 0) {
            puja -= 1;
        }

        int destiY = getDestiY() + puja * 78;
        if (destiY < 0) {
            destiY = 0;
        }
        if (numeroDeFiles == FILESPERDEFECTE) {
            destiY = (numeroDeFiles - 1) * 78;
        }

        setDestiY(destiY);
        setDireccioY(calculaDireccioY(VELOCITATMAXIMA));
    }


}
