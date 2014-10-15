package net.xaviersala.Batalla;

import acm.program.GraphicsProgram;

/**
 * Hello world!
 *
 */
public class App  extends GraphicsProgram
{
    /**
     *
     */
    private static final long serialVersionUID = 8241782044916223928L;

    public void run()
    {
        setSize(800, 600);
        CampdeBatalla hastings = new CampdeBatalla(this.getGCanvas());

        String[] noms = { "soldat.png" };
        hastings.creaExercit("Imperi", 12, noms );

//        String[] nom2 = { "guerrer2.png", "guerrer3.png" };
//        hastings.creaExercit("TrencaOssos", 12, nom2 );

    }

    /**
     * Crea els exèrcits.
     */
    public void crearExercits() {


    }
}
