import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Class that will do most of the parsing of the input file. 
 */

public class CommandProcessor {
    private int id;
    private String title;
    private String date; // Seminar date
    private int length; // Seminar length
    private String[] keywords; // Seminar keywords
    private short x; // Seminar x coord
    private short y; // Seminar y coord
    private String desc; // Seminar description
    private int cost; // Seminar cost
    private int hashSize;
    private int memSize;

    /**
     * Creates the command processor object
     * @param memSize: size of the memory pool
     * @param hashSize: size of the hash table
     */
    public CommandProcessor(String memSize, String hashSize) {
        this.hashSize = Integer.parseInt(hashSize);
        this.memSize = Integer.parseInt(memSize);

    }

    /**
     * Parses through the input file and checks what commands should be processed
     * @param filename: name of the input file
     */
    public void beginParsing(String filename) {
        try {
            SemDatabase semdata = new SemDatabase(hashSize, memSize);
            Scanner sc = new Scanner(new File(filename));// Create our new
                                                         // scanner
            while (sc.hasNext()) {// While the scanner has information to read
                String cmd = sc.next();// Read the next te
                switch (cmd) {
                    case "insert":
                        id = sc.nextInt();
                        sc.nextLine();
                        title = sc.nextLine();
                        date = sc.next();
                        length = sc.nextInt();
                        x = sc.nextShort();
                        y = sc.nextShort();
                        cost = sc.nextInt();
                        sc.nextLine();
                        String[] key = sc.nextLine().split("\\s+");
                        keywords = new String[key.length];
                        for (int i = 0; i < key.length; i++) {
                            if (!key[i].equals(" ")) {
                                keywords[i] = (key[i].trim());
                            }

                        }
                        desc = sc.nextLine().trim();

                        Seminar sem = new Seminar(id, title, date, length, x, y,
                            cost, keywords, desc);
                        semdata.insert(sem);

                        break;
                    case "print":// Found an add command
                        String command = sc.next();
                        if (command.equals("hashtable")) {
                            semdata.printHash();
                        }
                        else if (command.equals("blocks")) {
                            semdata.printFreeBlock();
                        }
                        else {
                            break;
                        }
                        break;
                    case "delete":// Found a delete command
                        id = sc.nextInt();
                        semdata.delete(id);
                        break;
                    case "search":// Found a search command
                        id = sc.nextInt();
                        semdata.search(id);

                        break;
                    default:// Found an unrecognized command
                        System.out.println("Unrecognized input " + cmd);
                        break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
