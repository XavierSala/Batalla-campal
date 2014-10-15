package net.xaviersala.Batalla;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class Exercit {

    /**
     * Nom per defecte de l'exèrcit.
     */
    private static final String DEFAULT_NAME = "Exèrcit";
    /**
     * Amplada de la fila.
     */
    private static final int AMPLADAFILA = 60;
    /**
     * Altura de la fila.
     */
    private static final int ALTURAFILA = 80;
    /**
     * Nom de l'exercit.
     */
    String nom;
    /**
     * Llista de soldats que composen l'exèrcit.
     */
    List<Soldat> soldats;
    /**
     * Direcció en la que es mou l'exèrcit.
     */
    int direccio;

    /**
     * Imatges de l'exèrcit
     */
    List<Image> imatges;

    /**
     * Generador de números aleatòris
     */
    private Random aleatori;

    /**
     * Crea un exèrcit amb un identificador.
     *
     * L'exèrcit es crea sense soldats... (no sé
     * perquè pot servir)
     */
    public Exercit(String identificador) {
        aleatori = new Random();
        if (identificador!=null) nom = identificador;
        else nom = DEFAULT_NAME;
        soldats = new ArrayList<Soldat>();
        imatges = new ArrayList<Image>();
        direccio = 1;
    }

    /**
     * Crea un exèrcit especificant-li un identificador i
     * un número de soldats.
     *
     * @param identificador Nom de l'exèrcit
     * @param numSoldats Número de soldats a crear
     */
    public Exercit(String identificador, int numSoldats) {
        this(identificador);
        crearSoldats(numSoldats);
    }

    /**
     * Crea un exèrcit i li defineix quines seran les imatges que
     * farà servir.
     *
     * @param identificador Nom de l'exèrcit
     * @param fitxersImatges Llista amb les imatges
     */
    public Exercit(String identificador, List<Image> fitxersImatges) {
        this(identificador);
        for(Image fitxerImatge: fitxersImatges) {
            afegirImatge(fitxerImatge);
        }

    }

    /**
     * Crea un exèrcit a partir dels paràmetres especificats.
     *
     * @param identificador Nom de l'exèrcit
     * @param fitxersImatges Imatges que formaran l'exèrcit
     * @param numSoldats Número de soldats de l'exèrcit
     */
    public Exercit(String identificador, List<Image> fitxersImatges, int numSoldats) {
        this(identificador, fitxersImatges);
        crearSoldats(numSoldats);
    }

    /**
     * @return retorna el nom de l'exèrcit
     */
    public String getNom() {
        return nom;
    }

    /**
     * Defineix el nom de l'exèrcit.
     * @param nom nom que es posarà
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Canvia la direcció de l'exercit.
     */
    public void canviaDireccio() {
        if (Math.abs(direccio)==1) {
            direccio *= -1;
        } else {
            direccio = 1;
        }
    }

    /**
     * Crear els soldats de l'exercit.
     *
     * En cas de que tingui imatges les passarà al constructor del
     * soldat i si no en tenen deixarà que el constructor del Soldat
     * trïi les que tingui per defecte
     *
     * Si no s'ha creat l'exèrcit amb imatges crearà un soldat amb
     * la imatge per defecte.
     *
     * @param numero número de soldats
     */
    public void crearSoldats(int numero) {
        int numImatges = imatges.size();
        for(int i=0; i < numero; i++) {
            if (imatges.size() == 0) {
                soldats.add(new Soldat());
            } else {
                soldats.add(new Soldat(
                        imatges.get(aleatori.nextInt(numImatges))
                ));
            }
        }
    }

    public List<Soldat> getSoldats() {
        return soldats;
    }

    /**
     * @return Retorna totes les imatges que formen l'exèrcit.
     */
    List<GImage> getImatges() {
        List<GImage> grafics = new ArrayList<GImage>();
        for(Soldat s: soldats) {
            grafics.add(s.getImatge());
        }
        return grafics;
    }

    /**
     * Afegir una imatge a l'exèrcit.
     * La idea és que un exèrcit pugui tenir soldats amb imatges
     * diferents per poder-los crear diferents
     */
    public boolean afegirImatge(Image im) {
        if (im!=null) {
            imatges.add(im);
            return true;
        }
        return false;
    }
    /**
     * @return Retorna el número de soldats de l'exèrcit
     */
    public int numeroDeSoldats() {
        return soldats.size();
    }

    /**
     * Posiciona els soldats en formació en el seu costat
     * corresponent.
     * @param files Files en les que s'ha de reorganitzar l'exèrcit
     */
    public void formacio(int files) {
        int[] posicioEnLesFiles = new int[files];
        for(Soldat s: soldats) {
            int fila = aleatori.nextInt(files);
            s.posiciona(posicioEnLesFiles[fila]*AMPLADAFILA, fila*ALTURAFILA);
            posicioEnLesFiles[fila]++;
        }
    }

    /**
     * Atacar!
     */
    public void atacar() {
        for(Soldat s: soldats) {
            s.mou(direccio);
            // Comprovar si matxaca a algú?
        }
    }

    /**
     * Obtenir un dels soldats de la llista o bé 'null' si en demanen
     * un que no hi és.
     *
     * @param index Soldat que es vol obtenir
     * @return retorna el soldat
     */
    public Soldat getSoldat(int index) {
        if (index < soldats.size()) {
            return soldats.get(index);
        }
        return null;
    }
    /**
     * Comprova si el soldat especificat xoca amb algun
     * dels soldats de la llista i si hi n'hi ha algun
     * l'esborra.
     * @param s Soldat
     * @return posicioDelSoldat.
     */
    public GRectangle rebreAtac(Soldat s) {
        return null;
    }

    /**
     * L'exèrcit actual rep un atac de l'altre exèrcit.
     *
     * @param altre Altre exèrcit
     * @return quants morts hem tingut...
     */
    public int rebreAtac(Exercit enemics) {
        Soldat eliminar = null;
        int eliminats = 0;

        for (Soldat enemic: enemics.getSoldats()) {
            for(Soldat soldat: soldats) {
                GRectangle soldatAmic = soldat.getPosicio();
                GRectangle soldatEnemic = enemic.getPosicio();
                if (soldatEnemic.intersects(soldatAmic)) {
                    eliminar = soldat;
                    break;
                }
            }
            if (eliminar!= null) {
                soldats.remove(eliminar);
                eliminar = null;
                eliminats++;
            }
        }
        return eliminats;
    }

}
