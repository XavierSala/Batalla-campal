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
     * Crea un soldat rei.
     * @param dibuix Imatge del soldat
     */
    public SoldatCap(final Image dibuix) {
        super(dibuix);
        // TODO Auto-generated constructor stub
    }

    /**
     * El rei no es mou però li donem un zero per
     * indicar que no cal tenir-lo en compte per
     * arribar a l'altre costat.
     * @return sempre zero
     */
    @Override
    public final int mou() {
        return 0;
    }

}
