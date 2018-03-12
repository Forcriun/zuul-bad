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
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southEastExit;
    private Room northWestExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     * @param southEast The southEast exit.
     * @param northWest The northWest exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southEast, Room northWest) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(southEast != null)
            southEastExit = southEast;
        if(northWest != null)
            northWestExit = northWest;
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
        Room nextRoom = null;
        if(direction.equals("north"))
            nextRoom = northExit;
        if(direction.equals("east"))
            nextRoom = eastExit;
        if(direction.equals("south"))
            nextRoom = southExit;
        if(direction.equals("west"))
            nextRoom = westExit;
        if(direction.equals("southEast"))
            nextRoom = southEastExit;
        if(direction.equals("northWest"))
            nextRoom = northWestExit;
        return nextRoom;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        String exitString = "Salidas: ";

        if(northExit != null)
            exitString += "north ";
        if(eastExit != null)
            exitString += "east ";
        if(southExit != null)
            exitString += "south ";
        if(westExit != null)
            exitString += "west ";
        if(southEastExit != null)
            exitString += "southEast ";
        if(northWestExit != null)
            exitString += "northWest ";

        return exitString;
    }
}
