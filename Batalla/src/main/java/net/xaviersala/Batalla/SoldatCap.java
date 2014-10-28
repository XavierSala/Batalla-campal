package net.xaviersala.Batalla;

import java.awt.Image;

/**
 * Defineix un soldat que és el cap de l'exèrcit.
 *
 * La idea és que no ataqui mai sinó que es quedi
 * pel darrera dels seus soldats
 *
 * @author xavier
 *
 */
public class SoldatCap extends SoldatGlobal {
    /**
     * Velocitat a la que es mou el rei.
     */

    private static final int VELOCITATMAXIMA = 15;

    /**
     * Crea un soldat rei.
     * @param dibuix Imatge del soldat
     */
    public SoldatCap(final Image dibuix) {
        super(dibuix);
        setDestiY(0);
    }

    /**
     * El rei no es mou però li donem un zero per
     * indicar que no cal tenir-lo en compte per
     * arribar a l'altre costat.
     * @return sempre zero
     */
    @Override
    public final int mou() {

        int dirY = getDireccioY();

        getImatge().move(0,
                dirY * Aleatori.obtenir(VELOCITATMAXIMA));

        setDireccioY(calculaDireccioY(VELOCITATMAXIMA));

        // Si estem al destí les direccions seran zero...
        if (getDireccioY() == 0) {
            if (getDestiY() == 0) {
                setDestiY(400);
            } else {
                setDestiY(0);
            }

        }
        return 0;
    }

}
