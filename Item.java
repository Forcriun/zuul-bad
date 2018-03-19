
/**
 * Clase que representa items que puede encontrarse el jugador en las salas.
 *
 * @author Didac Fernandez Fernandez
 * @version (a version number or a date)
 */
public class Item
{
    private String description;
    private int weight;
    private boolean canBeTaken;

    /**
     * Constructor para objetos de la clase Item
     * 
     * @param description La descripcion del item
     * @param description El peso del item
     */
    public Item(String description, int weight, boolean canBeTaken)
    {
        this.description = description;
        this.weight = weight;
        this.canBeTaken = canBeTaken;
    }

    /**
     * Getter de la descripcion del item
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Getter de la descripcion del item
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * Getter de la descripcion del item
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
        return "  --> " + description + " (" + weight + " gm).";
    }
}
