package net.xaviersala.Batalla;

import java.awt.Image;
import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

/**
 * Soldat.
 *
 * @author xavier
 *
 */
public class Soldat {

    /**
     * Imatge per defecte del soldat.
     */
    private static final String DEFAULT_IMAGE = "soldat.png";
    /**
     * Velocitat màxima a la que es pot moure un soldat.
     */
    private static final int VELOCITATMAXIMA = 10;
    /**
     * Imatge del soldat.
     */
    private GImage imatge;
    /**
     * Generador de números aleatòris.
     */
    private Random r;

    /**
     * Defineix el destí del soldat.
     */
    private int desti;
    /**
     * Direcció en la que es mou el soldat.
     */
    private int direccio;

    /**
     * Crea un soldat genèric.
     * El sistema li atorna una imatge per defecte
     */
    public Soldat() {
        this(new GImage(DEFAULT_IMAGE).getImage());
    }

    /**
     * Constructor de soldats principal.
     *
     * @param im Imatge del soldat
     */
    public Soldat(final Image im) {
        imatge = new GImage(im);
        r = new Random();

        desti = (int) imatge.getX();
        direccio = 1;
    }

    /**
     * Construeix un soldat i li dóna una imatge
     * i a més el posiciona.
     *
     * @param im Imatge
     * @param x posició on ha d'anar
     * @param y posició on ha d'anar
     */
    public Soldat(final Image im, final int x, final int y) {
        this(im);
        posiciona(x, y);
    }

    /**
     * Posiciona el soldat en la posició.
     * @param x posició x
     * @param y posició y
     */
    public final void posiciona(final int x, final int y) {
        imatge.setLocation(x, y);
        direccio = calculaDireccio();
    }

    /**
     * @return obtenir la imatge del soldat
     */
    public final GImage getImatge() {
        return imatge;
    }

    /**
     * Canviar la imatge del soldat.
     * @param dibuix Imatge a posar.
     */
    public final void setImatge(final GImage dibuix) {
        this.imatge = dibuix;

    }

    /**
     * @return Obtenir el rectangle amb la posicó del soldat
     */
    public final GRectangle getPosicio() {
        return imatge.getBounds();
    }

    /**
     * Mou el soldat en la direcció especificada.
     *
     * @return retorna un 0 si no es mou o un 1 si es mou
     */
    public final int mou() {

        imatge.move(direccio * r.nextInt(VELOCITATMAXIMA), 0);
        if (haArribat()) {
            direccio = 0;
            return 0;
        }
        return 1;
    }

    /**
     * Defineix quin és el destí del soldat.
     *
     * @param posicioFinal posició x de destí
     */
    public final void definirDesti(final int posicioFinal) {
        desti = posicioFinal - (int) imatge.getWidth();
        int direccioActual = direccio;
        direccio = calculaDireccio();
        if (direccioActual != direccio) {
            flipHorizontal();
        }
    }

    /**
     * Funció per calcular la direcció a partir de l'objectiu i la
     * posició en la que s'està.
     *
     * Per evitar problemes quan estigui en el lloc ha d'aturar-se
     *
     * @return Calcula la direcció a partir de l'objectiu i la posició
     */
    private int calculaDireccio() {
        int x = getPosicioXInt();

        if ((desti - x) == 0) {
            return direccio;
        }
        return Math.abs(desti - x) / (desti - x);
    }

    /**
     * @return Retorna la posició del soldat convertit a enters.
     */
    private int getPosicioXInt() {
        return (int) imatge.getX();
    }

    /**
     * @return retorna si el soldat ha arribat al destí.
     */
    private boolean haArribat() {
        return (direccio != calculaDireccio());
    }

    /**
     * Gira la imatge
     * @param imatge
     * @return
     */
    private void flipHorizontal() {
        int[][] array = imatge.getPixelArray();
        int height = array.length;
        int width = array[0].length;

        for (int y = 0; y < height; y++) {
            for (int x1 = 0; x1 < width / 2; x1++) {
                int x2 = width - x1 - 1;
                int temp = array[y][x1];
                array[y][x1] = array[y][x2];
                array[y][x2] = temp;
            }
        }
        imatge.setImage(new GImage(array).getImage());
    }

}
