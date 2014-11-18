package net.xaviersala.batalla;

import acm.graphics.GImage;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa el canó.
 *
 * <p>
 * El canó llença bales que acaben explotant en el destí
 *
 * @author xavier
 *
 */
public class SoldatCano extends SoldatGlobal {

  /**
   * Imatge de la bala de canó per defecte.
   */
  private static final String BALADEFAULT = "bala.png";

  /**
   * Imatge de la bala.
   */
  private String imatgeBala;

  /**
   * Dimensions del terreny de combat.
   */
  private int dimensionsX;

  /**
   * Dimensions del terreny de combat.
   */
  private int dimensionsY;

  /**
   * Cadència de tret de canó...
   */
  private int cadenciaTret;

  /**
   * Llista de bales.
   */
  private List<Bala> bales;

  /**
   * Es crea el canó a partir de la seva imatge i de les dimensions del camp de
   * batalla.
   *
   * @param dibuix
   *          Imatge del canó
   * @param dibuixBala
   *          Imatge de la bala
   * @param llarg
   *          Dimensions del camp de batalla
   * @param alt
   *          Dimensions del camp de batalla
   */
  public SoldatCano(final Image dibuix, final String dibuixBala,
      final int llarg, final int alt) {
    super(dibuix);

    if (dibuixBala == null) {
      imatgeBala = BALADEFAULT;
    } else {
      imatgeBala = dibuixBala;
    }

    dimensionsX = llarg;
    dimensionsY = alt;

    bales = new ArrayList<Bala>();

    cadenciaTret = Aleatori.obtenir(2);
  }

  /**
   * El moviment del canó consisteix en decidir si ha de disparar i moure totes
   * les seves bales.
   *
   * @return no es mou.
   */
  @Override
  public final int mou() {

    for (Bala b : bales) {
      b.mou();
    }

    int tiraONo = Aleatori.obtenir(100);
    if (tiraONo < cadenciaTret) {
      int posicioX = (int) ((int) getImatge().getX() + getImatge().getWidth() / 2);
      int posicioY = (int) ((int) getImatge().getY() + getImatge().getHeight() / 2);
      Bala projectil = new Bala(new GImage(imatgeBala).getImage(), posicioX,
          posicioY, Aleatori.obtenir(dimensionsX),
          Aleatori.obtenir(dimensionsY));
      bales.add(projectil);
      getImatge().getParent().add(projectil.getImatge());
    }

    // Eliminar les bales mortes.
    return 1;
  }

}
