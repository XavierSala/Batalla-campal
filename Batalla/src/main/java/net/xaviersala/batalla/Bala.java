package net.xaviersala.batalla;

import acm.graphics.GImage;

import java.awt.Image;

/**
 * Implementació de les bales que disparen els canons.
 *
 * @author xavier
 *
 */
public class Bala {

  /**
   * Velocitat de la bala.
   */
  private static final int VELOCITATMAXIMA = 15;
  /**
   * Imatge de la bala.
   */
  private GImage imatge;

  /**
   * Retorna la imatge de la bala.
   * @return the imatge
   */
  public final GImage getImatge() {
    return imatge;
  }

  /**
   * Destí X.
   */
  private int destiX;
  /**
   * Desti Y.
   */
  private int destiY;

  /**
   * Direccio en la que es mou la bala.
   */
  private double direccioX;
  /**
   * Direcció en la que es mou la bala.
   */
  private double direccioY;

  /**
   * Crea una bala.
   *
   * @param dibuix
   *          Dibuix de la bala
   * @param posx
   *          posició inicial de la bala
   * @param posy
   *          posició inicial de la bala
   * @param destix
   *          destí de la bala
   * @param destiy
   *          destí de la bala
   */
  public Bala(final Image dibuix, final int posx, final int posy,
      final int destix, final int destiy) {

    imatge = new GImage(dibuix, posx, posy);

    destiX = destix;
    destiY = destiy;

    int distancia = calculaDistancia();
    direccioX = (destiX - imatge.getX()) / distancia;
    direccioY = (destiY - imatge.getY()) / distancia;
  }

  /**
   * Moure la bala.
   *
   * @return si l'ha mogut o no
   */
  public final int mou() {

    getImatge().move(direccioX * VELOCITATMAXIMA, direccioY * VELOCITATMAXIMA);

    if (calculaDistancia() < VELOCITATMAXIMA) {
      //
      // EXPLOSIÓ
      //
      if (direccioX != 0 && direccioY != 0) {

        GImage explota = new GImage("explosio.png", imatge.getX(),
            imatge.getY());
        imatge.getParent().add(explota);
        imatge.getParent().remove(imatge);
        imatge = explota;
        direccioX = 0;
        direccioY = 0;
      }

      return 0;
    }

    return 1;
  }

  /**
   * @return determina si la bala està explotant.
   */
  public final boolean explota() {
    return (direccioX == 0 && direccioY == 0);
  }

  /**
   * @return Distància de la bala al seu destí.
   */
  private int calculaDistancia() {
    int dirX = (int) (destiX - imatge.getX());
    int dirY = (int) (destiY - imatge.getY());
    return (int) Math.sqrt(dirX * dirX + dirY * dirY);
  }

}
