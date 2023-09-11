import java.util.Scanner;

public class CommandProcessor {
    public int id;
    public String title;
    private String date; // Seminar date
    private int length; // Seminar length
    private String[] keywords; // Seminar keywords
    private short x; // Seminar x coord
    private short y; // Seminar y coord
    private String desc; // Seminar description
    private int cost; // Seminar cost
    private Scanner scanF;
    String filename;
    
    public CommandProcessor(String s) {
        filename = s;
    }
    
    public void processCommands(String inputFile) {
       this.scanF = new Scanner(inputFile);
    }
    
    public Seminar processor(Scanner scanner) {
        this.scanF = scanner;
        String lineOne = scanner.nextLine();
        String[] command = lineOne.split(" ");
        if (command[0].equals("insert")) {
            return commandProcessorInsert(scanF);
        }
        else if (command[0].equals("delete")) {
            return commandProcessorDelete(scanF);
        }
        
        else if (command[0].equals("search")) {
            return commandProcessorSearch(scanF);
        }
        
        else {
            System.out.println("Error processing commands");
            return null;
        }
    }
    
    public Seminar commandProcessorInsert(Scanner scanner) {
        String lineOne = scanner.nextLine();
        String[] valueLineOne = lineOne.split(" ");
        this.id = Integer.parseInt(valueLineOne[1]); //Setting ID
        this.title = scanner.nextLine();
        String lineThree = scanner.nextLine();
        String[] valuesLineThree = lineThree.split(" ");
        this.date = valuesLineThree[0];
        this.length = Integer.parseInt(valuesLineThree[1]);
        this.x = Short.parseShort(valuesLineThree[2]);
        this.y = Short.parseShort(valuesLineThree[3]);
        this.cost = Integer.parseInt(valuesLineThree[4]);
        String lineFour = scanner.nextLine();
        this.keywords = lineFour.split(" ");
        this.desc = scanner.nextLine();
        
        Seminar retSem = new Seminar(this.id, this.title, this.date, this.length, this.x,
        this.y, this.cost, this.keywords, this.desc);
        return null;
    }
    
    public Seminar commandProcessorDelete(Scanner scanner) {
        return null;
    }
    
    public Seminar commandProcessorSearch(Scanner scanner) {
        return null;
    }
}

