package net.xaviersala.batalla;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

import java.awt.Image;

/**
 * Classe abstracta del soldat per definir tot allò que serà comú a tots els
 * soldats.
 *
 * @author xavier
 *
 */
public abstract class SoldatGlobal implements Soldat {

  /**
   * Vida del soldat.
   */
  private int vida;

  /**
   * Defineix la vida que té un soldat.
   * @param vides
   *          La vida a posar
   */
  public final void setVida(final int vides) {
    this.vida = vides;
  }

  /**
   * Imatge del soldat.
   */
  private GImage imatge;

  /**
   * Defineix el destí X del soldat.
   */
  private int destiX;

  /**
   * Retorna quin és el seu destí.
   * @return the destiX
   */
  final int getDestiX() {
    return destiX;
  }

  /**
   * Assigna el nou destí al soldat.
   * @param novaX
   *          the destiX to set
   */
  final void setDestiX(final int novaX) {
    this.destiX = novaX;
  }

  /**
   * Obtenir el nou destí Y del soldat.
   * @return the destiY
   */
  final int getDestiY() {
    return destiY;
  }

  /**
   * Definir el nou destí Y del soldat.
   * @param novaY
   *          the destiY to set
   */
  final void setDestiY(final int novaY) {
    destiY = novaY;
  }

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
   * Crea un soldat.
   *
   * @param dibuix
   *          dibuix del solda
   */
  public SoldatGlobal(final Image dibuix) {
    imatge = new GImage(dibuix);

    destiX = (int) imatge.getX();
    direccioX = 1;
    mirantA = 1;

    vida = 1;
  }

  /**
   * Posiciona el soldat en la posició.
   *
   * <p>
   * Com que es mouen linealment el destí d'aquest soldat serà el del lloc en
   * que l'haguem posicionat.
   *
   * @param posX
   *          posició x
   * @param posY
   *          posició y
   */
  public final void posiciona(final int posX, final int posY) {
    imatge.setLocation(posX, posY);
    direccioX = calculaDireccioX(1);
    direccioY = 0;
    destiY = posY;
  }

  /**
   * Retorna la imatge del soldat.
   * @return obtenir la imatge del soldat
   */
  public final GImage getImatge() {
    return imatge;
  }

  /**
   * Canviar la imatge del soldat.
   *
   * @param dibuix
   *          Imatge a posar.
   */
  public final void setImatge(final GImage dibuix) {
    this.imatge = dibuix;

  }

  /**
   * Retorna el rectangle que inclou el soldat.
   * @return Obtenir el rectangle amb la posicó del soldat
   */
  public final GRectangle getPosicio() {
    return imatge.getBounds();
  }

  /**
   * Mou el soldat cap al seu objectiu.
   *
   * @return Si s'ha mogut retorna 1 i sinó 0
   */
  public abstract int mou();

  /**
   * Comprova si el soldat especificat ha tocat al soldat actual.
   *
   * @param enemic
   *          SoldatNormal enemic
   * @return posicioDelSoldat.
   */
  public final boolean rebreAtac(final Soldat enemic) {
    GRectangle jo = getPosicio();
    GRectangle soldatEnemic = enemic.getPosicio();
    boolean resultat = soldatEnemic.intersects(jo);
    if (resultat) {
      vida--;
    }
    return resultat;
  }

  /**
   * Informa de si el soldat està mort o no.
   *
   * @return determina si el soldat està mort
   */
  public final boolean haMort() {
    return (vida <= 0);
  }

  /**
   * Defineix a on ha d'anar el soldat.
   *
   * <p>
   * Fa servir la funció definirDesti(x) per determinar quin és el destí en
   * l'eix de les x
   *
   * <p>
   * Quan direccioY és zero estem en càrrega de batalla i quan val una direcció
   * és que s'està posicionant en formació per tornar a atacar
   *
   * @param posicioX
   *          posició X on volem arribar
   * @param posicioY
   *          posició Y on volem arribar
   */
  public final void definirDesti(final int posicioX, final int posicioY) {

    definirDesti(posicioX);

    destiY = posicioY;
    direccioY = calculaDireccioY(1);
  }

  /**
   * Defineix a on ha d'anar el soldat.
   *
   * <p>
   * Calcula la direcció cap on va el soldat agafant de referència el lloc on
   * vol arribar. En cas de que la X canviï de direcció s'ha de girar la imatge
   * perquè vol anar cap a l'altre costat.
   *
   * @param posicioFinal
   *          posició x de destí
   */
  public final void definirDesti(final int posicioFinal) {
    destiX = posicioFinal - (int) imatge.getWidth() / 2;
    direccioX = calculaDireccioX(1);
    if (direccioX != mirantA) {
      mirantA *= -1;
      flipHorizontal();
    }
  }

  /**
   * Funció per calcular la direcció a partir de l'objectiu i la posició en la
   * que s'està.
   *
   * <p>
   * Per evitar problemes quan estigui en el lloc ha d'aturar-se
   *
   * @param maximavelocitat
   *          Velocitat màxima del soldat
   * @return Calcula la direcció a partir de l'objectiu i la posició
   */
  final int calculaDireccioX(final int maximavelocitat) {
    int posX = getPosicioXInt();

    if ((destiX - posX) == 0) {
      return 0;
    }

    if (Math.abs(destiX - posX) <= maximavelocitat) {
      imatge.setLocation(destiX, imatge.getY());
      return 0;
    }

    return Math.abs(destiX - posX) / (destiX - posX);
  }

  /**
   * Si està molt a prop s'hi posa i llestos.
   *
   * @param maximavelocitat
   *          velocitat màxima del soldat
   * @return La direcció en que s'ha de moure
   */
  final int calculaDireccioY(final int maximavelocitat) {
    int posY = getPosicioYInt();

    if ((destiY - posY) == 0) {
      return 0;
    }

    if (Math.abs(destiY - posY) <= maximavelocitat) {
      imatge.setLocation(imatge.getX(), destiY);
      return 0;
    }
    return Math.abs(destiY - posY) / (destiY - posY);
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
   *
   * @param imatge Imatge a girar
   * @return Imatge girada
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
   * Definir la direccióX.
   *
   * @param novaX
   *          direcció X nova
   */
  final void setDireccioX(final int novaX) {
    direccioX = novaX;
  }

  /**
   * Definir la direccióY.
   *
   * @param novaY
   *          direccio Y nova
   */
  final void setDireccioY(final int novaY) {
    direccioY = novaY;
  }

  /**
   * Direcció en que es mou en l'eix de les x.
   * @return the direccioX
   */
  final int getDireccioX() {
    return direccioX;
  }

  /**
   * Direcció en que es mou en l'eix de les y.
   * @return the direccioY
   */
  final int getDireccioY() {
    return direccioY;
  }

  /**
   * @return Dades sobre el soldat.
   */
  public final String toString() {
    return imatge.getX() + " " + imatge.getY() + " -> " + destiX + "," + destiY
        + " (" + direccioX + "," + direccioY + ")";

  }

}
