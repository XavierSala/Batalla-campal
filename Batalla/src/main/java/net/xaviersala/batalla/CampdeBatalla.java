package net.xaviersala.batalla;

import java.util.ArrayList;
import java.util.List;

/**
 * Camp de batalla.
 *
 * @author xavier
 *
 */
public class CampdeBatalla {
  /**
   * Temps d'espera entre passades.
   */
  private static final int TEMPSESPERA = 80;

  /**
   * Número de files del terreny.
   */
  private static final int FILESTERRENY = 6;

  /**
   * Numero de files inicials del camp de batalla.
   */
  private final int filesInicialsTerreny;

  /**
   * Llista d'exèrcits.
   */
  private List<Exercit> exercits;

  /**
   * Amplada del camp de batalla.
   */
  private int ampladaCamp;

  /**
   * Crea el camp de batalla i l'associa a el Canvas actual.
   *
   * @param alturapantalla
   *          Altura del terreny de batalla
   * @param ampladapantalla
   *          Amplada del terreny de batalla
   */

  public CampdeBatalla(final int ampladapantalla, final int alturapantalla) {
    ampladaCamp = ampladapantalla;
    filesInicialsTerreny = FILESTERRENY;
    exercits = new ArrayList<Exercit>();
  }

  /**
   * Afegir un exèrcit al camp de batalla.
   *
   * @param ex
   *          Exercit que es vol afegir
   * @param posicioi
   *          Posició inicial de l'exèrcit
   * @param posiciof
   *          Posició final de l'exèrcit
   */
  public final void afegirExercit(final Exercit ex, final int posicioi,
      final int posiciof) {
    if (ex != null) {
      exercits.add(ex);
      ex.setMidaCampBatalla(ampladaCamp);
      ex.setPosicio(posicioi, posiciof);
      ex.soldatsFormacioInicial(filesInicialsTerreny);
    }
  }

  /**
   * Comença la batalla entre els exèrcits.
   *
   * <p>
   * Bàsicament es fan atacar els exèrcits i es comproven els morts després de
   * cada avançada.
   *
   * <p>
   * També ha de comprovar si un exèrcit ha vist reduits els seus soldats de
   * manera que no pugui omplir les files. Si això passa es redueixen les files
   * perquè els exèrcits es trobin més fàcilment
   */
  public final void batalla() {

    while (exercits.get(0).getNumeroDeSoldats() > 0
        && exercits.get(1).getNumeroDeSoldats() > 0) {

      exercits.get(0).soldatsAtacar();
      exercits.get(1).rebreAtac(exercits.get(0));
      exercits.get(1).soldatsAtacar();
      exercits.get(0).rebreAtac(exercits.get(1));
      // pantalla.pause(TEMPSESPERA);
      espera(TEMPSESPERA);

      // Comprovar si s'han de reduïr les files
      int minim = Math.min(exercits.get(0).getNumeroDeSoldats(), exercits
          .get(1).getNumeroDeSoldats());

      if (minim < FILESTERRENY) {
        exercits.get(0).setFilesExercit(minim);
        exercits.get(1).setFilesExercit(minim);
      }

    }

  }

  /**
   * @param tempsespera2
   *          Temps en que el thread ha d'estar aturat.
   *
   */
  private void espera(final int tempsespera2) {
    try {
      Thread.sleep(tempsespera2);
    } catch (InterruptedException e) {
      // Problemes ...
      e.printStackTrace();
    }
  }
}
