
/**
 * Enumeration class CommandWord - write a description of the enum class here
 *
 * @author Didac Fdez Fdez
 * @version 2018/04/13
 */
public enum CommandWord
{
    // Un objeto para cada comando, mas otro para los comandos no reconocidos
    GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), EAT("eat"),BACK("back"),
    TAKE("take"), DROP("drop"), ITEMS("items"),UNKNOWN(""), EQUIP("equip");
    
    private String command;
    
    CommandWord(String command){
        this.command = command;
    }
    
    public String getCommand(){
        return command;
    }
}
