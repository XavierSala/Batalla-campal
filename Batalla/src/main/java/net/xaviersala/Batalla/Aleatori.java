package net.xaviersala.Batalla;

import java.util.Random;

/**
 * Crea el generador de números aleatòris.
 *
 * @author xavier
 *
 */
public final class Aleatori {

    /**
     * Iniciar el generador.
     */
    private static Random r = new Random();

    /**
     * No es crea.
     */
    private Aleatori() {
    }

    /**
     * Obtenir un número aleatòri entre 0 .. max-1
     * @param max Número màxim
     * @return el resultat
     */
    public static int obtenir(final int max) {
        return r.nextInt(max);
    }

}
