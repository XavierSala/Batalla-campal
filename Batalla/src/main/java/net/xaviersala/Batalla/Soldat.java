package net.xaviersala.Batalla;

import java.awt.Image;
import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class Soldat {

    private static final String DEFAULT_IMAGE = "soldat.png";
    private static final int VELOCITATMAXIMA = 10;
    GImage imatge;
    Random r;

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
    public Soldat(Image im) {
        imatge = new GImage(im);
        r = new Random();
    }

    /**
     * Construeix un soldat i li dóna una imatge
     * i a més el posiciona.
     */
    public Soldat(Image im, int x, int y) {
        this(im);
        posiciona(x, y);
    }

    /**
     * Posiciona el soldat en la posició.
     * @param x posició x
     * @param y posició y
     */
    void posiciona(int x, int y) {
        imatge.setLocation(x, y);
    }

    public GImage getImatge() {
        return imatge;
    }

    public void setImatge(GImage imatge) {
        this.imatge = imatge;

    }

    public GRectangle getPosicio() {
        return imatge.getBounds();
    }

    /**
     * Mou el soldat en la direcció especificada.
     *
     * @param direccio direcció en la que s'ha de moure
     */
    void mou(int direccio) {
        imatge.move(direccio * r.nextInt(VELOCITATMAXIMA), 0);
    }

}
