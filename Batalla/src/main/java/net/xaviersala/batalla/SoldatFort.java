package net.xaviersala.batalla;

import java.awt.Image;

/**
 * Classe que implementa els soldats que no moren amb un sol toc
 * sinó que es moren al cap de diferents tocs.
 *
 * <p>
 * Per ara aquests tocs estan definits per VIDAFORT.
 *
 * @author xavier
 *
 */
public class SoldatFort extends SoldatGlobal {

  /**
   * Vida del soldat fort.
   */
  private static final int VIDAFORT = 3;
  /**
   * Velocitat màxima a la que es mou el soldat fort.
   */
  private static final int VELOCITATMAXIMA = 8;

  /**
   * Retorna la imatge del soldat.
   * @param dibuix
   *          imatge del soldat
   */
  public SoldatFort(final Image dibuix) {
    super(dibuix);
    setVida(VIDAFORT);
  }

  /*
   * (non-Javadoc)
   *
   * @see net.xaviersala.batalla.SoldatGlobal#mou()
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
