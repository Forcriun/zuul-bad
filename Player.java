import java.util.ArrayList;
import java.util.Stack;

/**
 * Clase Player. Representa al jugador de la partida.
 * 
 * El jugador puede moverse a placer por las distintas salas del juego, recoger y
 * soltar objetos, almacenarlos en su inventario, comer, observar la sala en la
 * que se encuentra.
 * 
 *
 * @author Dídac Fernández
 * @version 2018/03/20
 */
public class Player
{   
    //  Variables de control de posicion
    private Room currentRoom;   //  La sala en la que se encuentra el jugador
    private Stack<Room> enteredRooms;   //  Stack de las salas visitadas por el jugador durante la partida

    //  Variables de control del inventario
    private ArrayList<Item> inventory;

    /**
     * Constructor de la clase Player
     */
    public Player(){
        enteredRooms = new Stack<>();
        inventory = new ArrayList<>();
    }

    /**
     * Getter de la sala actual
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * Setter de la sala actual
     */
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }

    /**
     * Metodo que simula el movimiento del jugador de una sala a otra del mapa
     * a traves de la salida indicada por parametro. Si no hay salida en esa direccion
     * avisa por pantalla.
     * 
     * @param direction La direccion de la salida indicada
     */
    public void goRoom(String direction){
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("¡No hay salida posible en esa dirección!");
        }
        else {
            enteredRooms.push(currentRoom);
            currentRoom = nextRoom;
            look();
        }
    }

    /**
     * Muestra por pantalla la informacion detallada de la sala actual y sus 
     * salidas.
     */
    public void look() 
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Metodo que devuelve al jugador a la ultima sala en la que estuvo. Puede
     * invocarse repetidamente hasta volver a la posicion inicial de la partida.
     * Avisa por pantalla en caso de estar en la sala inicial.
     */
    public void back() 
    {
        if(enteredRooms.empty()){
            System.out.println("¡No puedes volver atrás, estás en el comienzo del juego!"); 
        }
        else{
            currentRoom = enteredRooms.pop();
            look();
        }
    }

    /**
     * Metodo que simula la ingesta de alimentos por el jugador y avisa de ello
     * por pantalla.
     */
    public void eat(){
        System.out.println("Has comido algo y ya no estás hambriento.");
    }

    /**
     * Metodo que simula al jugador cogiendo un objeto de la sala en la que se
     * encuentra. Si tiene lleno el inventario o el peso del objeto a coger
     * hace que sobrepase su limite, no lo coge.
     * 
     * @param itemId El ID del objeto
     */
    public void take(String itemId) 
    {        
        Item currentItem = currentRoom.searchItem(itemId);

        if(currentItem != null){
            if(currentItem.getCanBeTaken()){
                inventory.add(currentItem);
                currentRoom.removeItem(currentItem);
                System.out.println("Has cogido el objeto: " + currentItem.getItemDescription() + ".");
            }

            else{
                System.out.println("¡No puedes coger el objeto!");
            }
        }
        else{
            System.out.println("¿De qué objeto me estás hablando?");
        }
    }
}