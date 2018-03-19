import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String,Room> exits;
    private ArrayList<Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        this.items = new ArrayList<>();
    }

    /**
     * Establece una salida de la sala en la direccion que recibe como argumento.
     * 
     * @param direction La direccion de salida.
     * @param nextRoom La siguiente sala en la direccion dada.
     */
    public void setExit(String direction, Room nextRoom){
        exits.put(direction,nextRoom);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param direction La direccion de la salida a comprobar.
     * @return La sala asociada a la direccion de salida. Si no tiene salida en esa direccion devuelve null
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        String exitString = "Salidas: ";

        for(String exit : exits.keySet()){
            exitString += exit + " ";
        }

        return exitString;
    }

    /**
     * Devuelve una descripcion larga de la sala, en formato:
     *     Estas en 'nombre de la sala'
     *     Salidas: north west southwest
     *     Item: description (weight)
     * @return Una descripcion de la sala, incluyendo las salidas.
     */
    public String getLongDescription(){
        String itemsLongDescription = (items.isEmpty())?"":"Hay " + items.size() + " item(s) en la sala:\n";
        
        for(Item item : items){
            itemsLongDescription += item.getLongDescription() + "\n";
        }

        return "Estas en " + getDescription() + "\n" + getExitString() + "\n" + itemsLongDescription;
    }
    
    /**
     * Metodo que anade nuevos items al listado de items de la sala
     * 
     * @param description La descripcion del objeto
     * @param weight El peso del objeto
     * @param canBeTaken Si se puede coger el objeto
     */
    public void addItem(String description,int weight, boolean canBeTaken){
        items.add(new Item (description,weight, canBeTaken));
    }
    
    /**
     * Getter de items
     */
    public ArrayList<Item> getItems(){
        return items;
    }
}
