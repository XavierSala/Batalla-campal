package net.xaviersala.Batalla;

import java.awt.Image;
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
     * Defineix el destí X del soldat.
     */
    private int destiX;
    /**
     * Defineix el destí Y del soldat.
     */
    private int destiY;
    /**
     * Direcció X en la que es mou el soldat.
     */
    private int direccioX;
    /**
     * Direcció Y en la que es mou el soldat.
     */
    private int direccioY;
    /**
     * Cap a on mira la imatge. (1) Dreta (-1) Esquerra
     */
    private int mirantA;

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

        destiX = (int) imatge.getX();
        direccioX = 1;
        mirantA = 1;
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
     *
     * Com que es mouen linealment el destí d'aquest soldat serà
     * el del lloc en que l'haguem posicionat.
     *
     * @param x posició x
     * @param y posició y
     */
    public final void posiciona(final int x, final int y) {
        imatge.setLocation(x, y);
        direccioX = calculaDireccioX();
        direccioY = 0;
        destiY = y;
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
    public final int mou() {

        imatge.move(direccioX * Aleatori.obtenir(VELOCITATMAXIMA),
                direccioY * Aleatori.obtenir(VELOCITATMAXIMA));
        direccioX = calculaDireccioX();
        direccioY = calculaDireccioY();

        // Si estem al destí les direccions seran zero...
        if (direccioX + direccioY == 0) {
            return 0;
        }
        return 1;
    }

    /**
     * Defineix a on ha d'anar el soldat.
     *
     * Calcula la direcció cap on va el soldat agafant de referència
     * el lloc on vol arribar. En cas de que la X canviï de direcció
     * s'ha de girar la imatge perquè vol anar cap a l'altre costat.
     *
     * @param posicioFinal posició x de destí
     */
    public final void definirDesti(final int posicioFinal) {
        destiX = posicioFinal - (int) imatge.getWidth() / 2;
        direccioX = calculaDireccioX();
        if (direccioX != mirantA) {
            mirantA *= -1;
            flipHorizontal();
        }
    }

    /**
     * Defineix a on ha d'anar el soldat.
     *
     * Fa servir la funció definirDesti(x) per determinar quin és el
     * destí en l'eix de les x
     *
     * Quan direccioY és zero estem en càrrega de batalla i
     * quan val una direcció és que s'està posicionant en formació
     * per tornar a atacar
     *
     * @param posicioX posició X on volem arribar
     * @param posicioY posició Y on volem arribar
     */
    public final void definirDesti(final int posicioX, final int posicioY) {

        definirDesti(posicioX);

        destiY = posicioY;
        direccioY = calculaDireccioY();
    }

    /**
     * Funció per calcular la direcció a partir de l'objectiu i la
     * posició en la que s'està.
     *
     * Per evitar problemes quan estigui en el lloc ha d'aturar-se
     *
     * @return Calcula la direcció a partir de l'objectiu i la posició
     */
    private int calculaDireccioX() {
        int x = getPosicioXInt();

        if ((destiX - x) == 0) {
            return 0;
        }

        if (Math.abs(destiX - x) <= VELOCITATMAXIMA) {
            imatge.setLocation(destiX, imatge.getY());
            return 0;
        }

        return Math.abs(destiX - x) / (destiX - x);
    }

    /**
     *
     * Si està molt a prop s'hi posa i llestos.
     *
     * @return La direcció en que s'ha de moure
     */
    private int calculaDireccioY() {
        int y = getPosicioYInt();

        if ((destiY - y) == 0) {
            return 0;
        }

        if (Math.abs(destiY - y) <= VELOCITATMAXIMA) {
            imatge.setLocation(imatge.getX(), destiY);
            return 0;
        }
        return Math.abs(destiY - y) / (destiY - y);
    }

    /**
     * @return Retorna la posició X del soldat convertit a enters.
     */
    private int getPosicioXInt() {
        return (int) imatge.getX();
    }

    /**
     * @return Retorna la posició Y del soldat convertit a enters.
     */
    private int getPosicioYInt() {
        return (int) imatge.getY();
    }

    /**
     * Gira la imatge.
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

    /**
     * @return Dades sobre el soldat.
     */
    public final String toString() {
        return imatge.getX() + " " + imatge.getY() + " -> "
                + destiX + "," + destiY + " ("
                + direccioX + "," + direccioY + ")";

    }

}
