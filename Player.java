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
    private int maxWeight;
    private int currentWeight;

    //  Poder especial del jugador por el cual puede coger cualquier objeto sin importar
    //  su atributo canBeTaken. El poder se activa una vez en cuentra y equipa la mochila
    //  mágica localizada en alguna de las salas de la partida
    private boolean specialPower;

    /**
     * Constructor de la clase Player
     * 
     * @param  maxWeight  El peso maximo en gramos que puede llevar el jugador en çel inventario
     */
    public Player(int maxWeight){
        enteredRooms = new Stack<>();
        inventory = new ArrayList<>();
        currentWeight = 0;
        this.maxWeight = maxWeight;
        specialPower = false;
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
            if(currentItem.getCanBeTaken() || specialPower){
                if(currentItem.getItemWeight() + currentWeight < maxWeight){
                    inventory.add(currentItem);
                    currentRoom.removeItem(currentItem);
                    currentWeight += currentItem.getItemWeight();
                    System.out.println("Has cogido el objeto: " + currentItem.getItemDescription() + ".");
                }
                else{
                    System.out.println("¡El objeto no cabe en tu inventario!");
                }
            }
            else{
                System.out.println("¡No puedes coger el objeto!");
            }
        }
        else{
            System.out.println("¿De qué objeto me estás hablando?");
        }
    }

    /**
     * Metodo que informa al jugador del contenido de su inventario.
     */
    public void items() 
    {
        if(inventory.isEmpty()){
            System.out.println("Tu inventario está vacío.");
        }
        else{
            System.out.println("Inventario: ");

            for(Item item : inventory){
                System.out.println(item.getLongDescription());
            }            

            System.out.println("Peso total: " + currentWeight + "(gm).");
        }
    }

    /**
     * Metodo que simula al jugador soltando un objeto en la sala en la que se
     * encuentra. Si tiene el inventario vacio recibe un aviso.
     * 
     * @param itemId El ID del objeto
     */
    public void drop(String itemId) 
    {
        if(itemId != null){
            Item currentItem = null;
            boolean searching = true;

            for(int i = 0; i < inventory.size() && searching; i++){
                if(inventory.get(i).getId().contains(itemId)){
                    currentItem = inventory.get(i);
                    searching = false;
                }
            }

            if(currentItem != null){
                currentRoom.addItem(currentItem);
                inventory.remove(currentItem);
                currentWeight -= currentItem.getItemWeight();
                System.out.println("Has soltado: " + currentItem.getItemDescription());
            }
            else{
                System.out.println("El objeto no está en tu inventario.");
            }
        }
        else{
            System.out.println("¿Soltar qué?");
        } 
    }

    /**
     * Metodo que simula al jugador equipandose una mochila magica que le permite
     * poder coger cualquier objeto del juego, incluidos los que no podian cogerse
     * anteriormente.
     * 
     * @param itemId El ID del objeto
     */
    public void equip(String itemId) 
    {
        if(itemId != null){
            if (itemId.equals("mochilaca")) {
                if(inventory.isEmpty()){
                    System.out.println("Primero tendrás que coger algo, melón.");
                }                        
                else if(specialPower){
                    System.out.println("Pero si ya la tienes equipada, zote.");
                }
                else{
                    boolean searching = true;

                    for(int i = 0; i < inventory.size() && searching; i++){
                        if(inventory.get(i).getId().equals("mochilaca")){
                            inventory.remove(inventory.get(i));
                            specialPower = true;
                            searching = false;
                            System.out.println("Te has equipado la mochila mágica.\n¡¡¡Ahora puedes coger cualquier objeto que encuentres!!!");
                        }
                    }
                }
            }
            else{
                System.out.println("No puedes equiparte eso.");
            }
        }
        else {
            System.out.println("¿Equipar qué?");
        }
    }
}