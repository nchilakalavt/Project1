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
    String filename;
    public CommandProcessor(String s) {
        filename = s;
    }
    public void processCommands(String inputFile) {
       Scanner scanF = new Scanner(inputFile);
    }
}

