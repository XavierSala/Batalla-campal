/**
 *
 */
package net.xaviersala.Batalla;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

/**
 * Defineix què és el que ha de fer un soldat per
 * poder ser considerat soldat.
 *
 * @author xavier
 *
 */
public interface Soldat {

    /**
     * Posiciona el soldat en un lloc concret.
     *
     * @param x destí a on ha d'anar
     * @param y destí a on ha d'anar
     */
    void posiciona(final int x, final int y);

    /**
     * @return Obtenir el rectangle amb la posicó del soldat
     */
    GRectangle getPosicio();

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
     int mou();

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
     void definirDesti(final int posicioX, final int posicioY);


     /**
      * Defineix a on ha d'anar el soldat.
      *
      * Fa servir la funció definirDesti(x) per determinar quin és el
      * destí en l'eix de les x
      *
      * @param posicioX posició X on ha d'arribar
      */
     void definirDesti(int posicioX);

     /**
      * Comprova si el soldat especificat ha tocat al
      * soldat actual.
      *
      * @param enemic SoldatNormal enemic
      * @return posicioDelSoldat.
      */
     boolean rebreAtac(final Soldat enemic);

     /**
      * @return Determina si el soldat està mort o no.
      */
     boolean haMort();

     /**
      * @return Obtenir la imatge del soldat
      */
     GImage getImatge();

}
