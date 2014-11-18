package net.xaviersala.batalla;

import acm.graphics.GImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Exèrcit de Soldats preparat per atacar.
 *
 * @author xavier
 *
 */
public class Exercit {

  /**
   * Nom per defecte de l'exèrcit.
   */
  private static final String DEFAULT_NAME = "Exèrcit";
  /**
   * Amplada de la fila.
   */
  private static final int AMPLADAFILA = 50;
  /**
   * Altura de la fila.
   */
  private static final int ALTURAFILA = 78;

  /**
   * Files en que es distribueix l'exèrcit.
   */
  private int filesExercit;

  /**
   * Nom de l'exercit.
   */
  private String nom;
  /**
   * Llista de soldats que composen l'exèrcit.
   */
  private List<Soldat> soldats;

  /**
   * Mida del Camp de Batalla.
   */
  private int midaCampBatalla;

  /**
   * Posicio des d'on s'inicia l'atac.
   */
  private int posicioOriginal;
  /**
   * Posicio final de l'atac.
   */
  private int posicioFinal;
  /**
   * Diu si està en formació o no.
   */
  private boolean formant;

  /**
   * Crea un exèrcit amb un identificador. Al
   * principi l'exèrcit es crea sense soldats...
   * (no sé perquè pot servir)
   *
   * @param identificador
   *          nom de l'exèrcit
   */
  public Exercit(final String identificador) {

    if (identificador != null) {
      nom = identificador;
    } else {
      nom = DEFAULT_NAME;
    }
    soldats = new ArrayList<Soldat>();
    filesExercit = 1;
  }

  /**
   * Crear un exèrcit i definir-li quina és la mida del camp de batalla.
   *
   * @param identificador
   *          nom de l'exèrcit
   * @param midaCamp
   *          mida del camp de batalla
   */
  public Exercit(final String identificador, final int midaCamp) {
    this(identificador);
    midaCampBatalla = midaCamp;
  }

  /**
   * Retorna el nom de l'exèrcit.
   * @return retorna el nom de l'exèrcit
   */
  public final String getNom() {
    return nom;
  }

  /**
   * Defineix el nom de l'exèrcit.
   *
   * @param nouNom
   *          nom que es posarà
   */
  public final void setNom(final String nouNom) {
    this.nom = nouNom;
  }

  /**
   * @return retorna la mida del camp de batalla.
   */
  public final int getMidaCampBatalla() {
    return midaCampBatalla;
  }

  /**
   * Defineix la mida del camp de batalla.
   *
   * @param mida
   *          Mida del camp de batalla
   */
  public final void setMidaCampBatalla(final int mida) {
    midaCampBatalla = mida;
  }

  /**
   * Retorna el número de files en que l'exèrcit ha de formar.
   * @return the filesExercit
   */
  public final int getFilesExercit() {
    return filesExercit;
  }

  /**
   * Defineix el número de files en que l'exèrcit ha de formar.
   * @param files
   *          the filesExercit to set
   */
  public final void setFilesExercit(final int files) {
    this.filesExercit = files;
  }

  /**
   * Canvia la direcció en que mira l'exercit.
   *
   * <p>
   * Per anar bé mai, no hauria de ser zero...
   *
   * @return direccio en la que mira l'exèrcit
   */
  public final int calculaDireccio() {

    if (posicioFinal - posicioOriginal == 0) {
      return 0;
    }
    return (posicioFinal - posicioOriginal)
        / Math.abs(posicioFinal - posicioOriginal);

  }

  /**
   * Afegir un soldat a l'exèrcit. Aquesta funció es fa servir
   * per reclutar nous soldats
   *
   * @param soldat
   *          número de soldats
   */
  public final void allistarSoldat(final Soldat soldat) {
    if (soldat != null) {
      soldats.add(soldat);
    }
  }

  /**
   * Retorna una llista d'objectes de tipus soldat.
   *
   * @return llista de soldats
   */
  public final List<Soldat> getSoldats() {
    return soldats;
  }

  /**
   * Retorna el número de soldats que té l'exèrcit.
   *
   * @return Retorna el número de soldats de l'exèrcit
   */
  public final int getNumeroDeSoldats() {
    return soldats.size();
  }

  /**
   * Posiciona els soldats en formació en el seu costat corresponent.
   *
   * @param files
   *          Files en les que s'ha de reorganitzar l'exèrcit
   */
  public final void soldatsFormacioInicial(final int files) {
    filesExercit = files;
    int[] posicioEnLesFiles = new int[files];
    int puntBase = 0;

    int direccio = calculaDireccio();

    if (direccio < 0) {
      puntBase = midaCampBatalla - AMPLADAFILA;
    }

    for (Soldat s : soldats) {
      int fila = Aleatori.obtenir(files);
      s.posiciona(puntBase + direccio * posicioEnLesFiles[fila] * AMPLADAFILA,
          fila * ALTURAFILA);
      posicioEnLesFiles[fila]++;
    }
    formant = false;
  }

  /**
   * Posiciona els soldats en formació en el seu costat corresponent.
   *
   * @param files
   *          Files en les que s'ha de reorganitzar l'exèrcit
   */
  public final void soldatsFormacio(final int files) {
    filesExercit = files;
    int[] posicioEnLesFiles = new int[files];
    int puntBase = 0;

    int direccio = calculaDireccio();

    if (direccio < 0) {
      puntBase = midaCampBatalla - AMPLADAFILA;
    }
    for (Soldat s : soldats) {
      int fila = Aleatori.obtenir(files);
      s.definirDesti(puntBase + direccio * posicioEnLesFiles[fila]
          * AMPLADAFILA, fila * ALTURAFILA);
      if (s instanceof SoldatHeroi) {
        SoldatHeroi tmp = (SoldatHeroi) s;
        tmp.setNumeroDeFiles(filesExercit);
      }
      posicioEnLesFiles[fila]++;
    }
    formant = true;
  }

  /**
   * Definir el destí dels soldats en coordenades x.
   */
  public final void soldatsDefinirDestiX() {
    for (Soldat s : soldats) {
      s.definirDesti(posicioFinal);
    }
  }

  /**
   * L'exèrcit es mou endavant en moviment d'atac.
   */
  public final void soldatsAtacar() {
    int quants = 0;
    for (Soldat s : soldats) {
      quants += s.mou();
    }

    if (quants == 0) {
      // No s'ha mogut ningú, per tant canvi!
      if (!formant) {
        intercanviaPosicions();
        soldatsFormacio(filesExercit);
      } else {
        formant = false;
        soldatsDefinirDestiX();
      }

    }
  }

  /**
   * Obtenir un dels soldats de la llista o bé 'null' si en demanen un que no hi
   * és.
   *
   * @param index
   *          SoldatNormal que es vol obtenir
   * @return retorna el soldat
   */
  public final Soldat getSoldat(final int index) {
    if (index < soldats.size()) {
      return soldats.get(index);
    }
    return null;
  }

  /**
   * L'exèrcit actual rep un atac de l'altre exèrcit.
   *
   * @param enemics
   *          Altre exèrcit
   * @return quants morts hem tingut...
   */
  public final int rebreAtac(final Exercit enemics) {
    Soldat eliminar = null;
    int eliminats = 0;

    for (Soldat enemic : enemics.getSoldats()) {
      for (Soldat soldat : soldats) {
        if (soldat.rebreAtac(enemic)) {
          if (soldat.haMort()) {
            eliminar = soldat;
          }
          break;
        }
      }
      if (eliminar != null) {
        soldats.remove(eliminar);
        GImage pic = eliminar.getImatge();
        pic.getParent().remove(pic);
        eliminar = null;
        eliminats++;
      }
    }
    return eliminats;
  }

  /**
   * Defineix en quin lloc està l'exèrcit.
   *
   * @param posicioi
   *          posició des d'on comença la formació
   * @param posiciof
   *          destí de l'exercit
   */
  public final void setPosicio(final int posicioi, final int posiciof) {
    posicioOriginal = posicioi;
    posicioFinal = posiciof;
    soldatsDefinirDestiX();
  }

  /**
   * Intercanvia les direccions.
   */
  private void intercanviaPosicions() {
    int tmp = posicioOriginal;
    posicioOriginal = posicioFinal;
    posicioFinal = tmp;
  }

  /**
   * Representació en forma de text de l'exèrcit.
   * @see toString
   * @return descriu l'exèrcit
   */
  public final String toString() {
    return nom;
  }
}
