import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createPlayer();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private Room createRooms()
    {
        Room recepcion, oficinas, laboratorio, planta, logistica, vestuarios,torno;

        // create the rooms
        recepcion = new Room("la recepción de la fábrica.");
        oficinas = new Room("las oficinas del personal administrativo.");
        laboratorio = new Room("en el laboratorio de pruebas.");
        planta = new Room("en la planta de producción y montaje.");
        logistica = new Room("en el almacén de la fábrica.");
        vestuarios = new Room("en los vestuarios.");
        torno = new Room("en el torno de salida para empleados.");

        // initialise room exits        
        recepcion.setExit("north", oficinas);
        recepcion.setExit("northWest", laboratorio);
        // recepcion.setExit("south", torno);

        oficinas.setExit("north", vestuarios);
        oficinas.setExit("east", planta);
        oficinas.setExit("south", recepcion);
        oficinas.setExit("west", laboratorio);

        laboratorio.setExit("east", oficinas);
        laboratorio.setExit("southEast", recepcion);        
        // laboratorio.setExit("northEast", vestuarios);

        planta.setExit("north", logistica);
        planta.setExit("west", oficinas);
        planta.setExit("northWest", vestuarios);

        logistica.setExit("south", planta);
        logistica.setExit("west", vestuarios);
        logistica.setExit("northWest", torno);

        vestuarios.setExit("north", torno);
        vestuarios.setExit("east", logistica);
        vestuarios.setExit("south", oficinas);
        vestuarios.setExit("southEast", planta);       
        // vestuarios.setExit("southWest", laboratorio);

        torno.setExit("south", vestuarios);
        torno.setExit("southEast", logistica);

        // Anadimos los items a las salas
        recepcion.addItem(new Item("paraguas","Paraguas",400,true));
        recepcion.addItem(new Item("folleto","Folleto de la empresa",15,true));
        recepcion.addItem(new Item("stand","Stand publicitario",9700,true));
        
        oficinas.addItem(new Item("mochilaca","Mochila mágica",260,true));

        planta.addItem(new Item("barra","Barra de acero inoxidable",1200,true));
        planta.addItem(new Item("llave","Llave dinamométrica",3480,false));
        planta.addItem(new Item("casco","Casco de seguridad",720,true));

        logistica.addItem(new Item("rollo","Rollo de embalaje",12235,true));
        logistica.addItem(new Item("cuter","Cúter",115,false));

        vestuarios.addItem(new Item("abrigo","Abrigo de piel",2100,false));
        vestuarios.addItem(new Item("botas","Botas de seguridad",2430,true));
        vestuarios.addItem(new Item("guantes","Guantes del nº9",65,false));
        vestuarios.addItem(new Item("camiseta","Camiseta de tirantes",80,true));

        return recepcion;
    }

    /**
     * Metodo que inicializa al jugador de la partida en la sala inicial.
     * 
     * @param   startingRoom  La habitacion inicial de la partida
     */
    private void createPlayer(){
        player = new Player(10000);
        player.setCurrentRoom(createRooms());  // start game outside        
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Gracias por jugar.  Adios.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenido a F*** the police!");
        System.out.println("F*** the police! es una nueva aventura de texto increíblemente aburrida.");
        System.out.println("Escribe 'help' si necesitas ayuda.");
        System.out.println();
        player.look();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            player.look();
        }
        else if (commandWord.equals("take")) {
            player.take(command.getSecondWord());
        }
        else if (commandWord.equals("items")) {
            player.items();
        }
        else if (commandWord.equals("drop")) {
            player.drop(command.getSecondWord());
        }
        else if (commandWord.equals("equip")) {
            player.equip(command.getSecondWord());
        }
        else if (commandWord.equals("eat")) {
            player.eat();
        }
        else if (commandWord.equals("back")) {
            player.back();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Acabas de robar documentos importantes de una fábrica.");
        System.out.println("De vuelta en la entrada ves que la policía está en el exterior.");
        System.out.println("Debes encontrar otra salida.");
        System.out.println();
        System.out.println("Tus comandos son:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        player.goRoom(direction);
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
