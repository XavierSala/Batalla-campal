package net.xaviersala.Batalla;

import java.awt.Image;

import acm.graphics.GImage;

/**
 * SoldatNormal.
 *
 * @author xavier
 *
 */
public class SoldatNormal extends SoldatGlobal {

    /**
     * Imatge per defecte del soldat.
     */
    private static final String DEFAULT_IMAGE = "soldat.png";
    /**
     * Velocitat màxima a la que es pot moure un soldat.
     */
    private static final int VELOCITATMAXIMA = 10;

    /**
     * Crea un soldat genèric.
     * El sistema li atorna una imatge per defecte
     */
    public SoldatNormal() {
        this(new GImage(DEFAULT_IMAGE).getImage());
    }

    /**
     * Constructor de soldats principal.
     *
     * @param im Imatge del soldat
     */
    public SoldatNormal(final Image im) {
        super(im);
    }

    /**
     * Construeix un soldat i li dóna una imatge
     * i a més el posiciona.
     *
     * @param im Imatge
     * @param x posició on ha d'anar
     * @param y posició on ha d'anar
     */
    public SoldatNormal(final Image im, final int x, final int y) {
        this(im);
        posiciona(x, y);
    }

    /**
     * Mou el soldat cap al seu objectiu.
     *
     * Les variables direccioX i direccioY marquen en quina
     * direcció s'ha de moure el soldat per arribar fins al
     * destí. Quan aquestes dues variables siguin zero el
     * soldat ja ha arribat a lloc
     *
     * @return retorna un 0 si el soldat no s'ha mogut
     * o un 1 si s'ha mogut
     */
    @Override
    public final int mou() {

        int dirX = getDireccioX();
        int dirY = getDireccioY();

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

}
