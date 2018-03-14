
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

    /**
     * Constructor para objetos de la clase Item
     * 
     * @param description La descripcion del item
     * @param description El peso del item
     */
    public Item(String description, int weight)
    {
        this.description = description;
        this.weight = weight;
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
     * Devuelve toda la informacion del item
     * 
     * @return  Una cadena con la informacion detallada del item
     */
    public String getLongDescription()
    {
        return "Item: " + description + " (" + weight + " gm).";
    }
}
