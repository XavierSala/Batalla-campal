package net.xaviersala.batalla;

import acm.graphics.GContainer;
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

    final int distancia = calculaDistancia();
    direccioX = (destiX - imatge.getX()) / distancia;
    direccioY = (destiY - imatge.getY()) / distancia;
  }

  /**
   * Moure la bala.
   *
   * @return si l'ha mogut o no
   */
  public final int mou() {

    int retorn = 1;
    imatge.move(direccioX * VELOCITATMAXIMA, direccioY * VELOCITATMAXIMA);

    if (calculaDistancia() < VELOCITATMAXIMA) {
      //
      // EXPLOSIÓ
      //
      if (direccioX != 0 && direccioY != 0) {

        GImage explota = new GImage("explosio.png", imatge.getX(),
            imatge.getY());
        final GContainer pissarra = imatge.getParent();
        pissarra.add(explota);
        pissarra.remove(imatge);
        imatge = explota;
        direccioX = 0;
        direccioY = 0;
      }

      retorn = 0;
    }

    return retorn;
  }

  /**
   * @return determina si la bala està explotant.
   */
  public final boolean explota() {
    return direccioX == 0 && direccioY == 0;
  }

  /**
   * @return Distància de la bala al seu destí.
   */
  private int calculaDistancia() {
    final int dirX = (int) (destiX - imatge.getX());
    final int dirY = (int) (destiY - imatge.getY());
    return (int) Math.sqrt(dirX * dirX + dirY * dirY);
  }

  /**
   * Retorna la imatge de la bala.
   * @return the imatge
   */
  public final GImage getImatge() {
    return imatge;
  }

  /**
   * Posar la imatge de la bala.
   * @param ima Imatge de la bala
   */
  public void setImatge(final GImage ima) {
    imatge = ima;
  }

}
