
/**
 * Clase que representa items que puede encontrarse el jugador en las salas.
 *
 * @author Didac Fernandez Fernandez
 * @version (a version number or a date)
 */
public class Item
{
    private String id;
    private String description;
    private int weight;
    private boolean canBeTaken;

    /**
     * Constructor para objetos de la clase Item
     * 
     * @param id El ID del item
     * @param description La descripcion del item
     * @param weight El peso del item
     * @param canBeTaken Si el objeto puede cogerse
     */
    public Item(String id, String description, int weight, boolean canBeTaken)
    {
        this.id = id;
        this.description = description;
        this.weight = weight;
        this.canBeTaken = canBeTaken;
    }

    /**
     * Getter del ID del item
     */
    public String getId()
    {
        return id;
    }

    /**
     * Getter de la descripcion del item
     */
    public String getItemDescription()
    {
        return description;
    }

    /**
     * Getter de la descripcion del item
     */
    public int getItemWeight()
    {
        return weight;
    }

    /**
     * Getter de canBeTaken del item
     */
    public boolean getCanBeTaken()
    {
        return canBeTaken;
    }
    
    /**
     * Devuelve toda la informacion del item
     * 
     * @return  Una cadena con la informacion detallada del item
     */
    public String getLongDescription()
    {
        return "  --> ID[" + id + "] - " + description + " (" + weight + " gm).";
    }
}
