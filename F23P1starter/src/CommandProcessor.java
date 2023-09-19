/**
 * 
 * 
 * Class for the CommandProcessor
 * 
 * @author nchilakala pratc
 * @version 9/19/23
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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
     * @param MemSize and HashSize strings that tell us how big our hashtable and memory pool will be
     */
    public CommandProcessor(String memSize, String hashSize) {
        this.hashSize = Integer.parseInt(hashSize);
        this.memSize = Integer.parseInt(memSize);

    }


    /**
     * method to parse through the file
     * 
     * @param filename to parse through
     */
    public void parsing(String filename) {
        try {
            SemDatabase semdata = new SemDatabase(hashSize, memSize);
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                String cmd = sc.next();
                // switch case based on what string command is read
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
                        String[] key = sc.nextLine().trim().split("\\s+");
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
                    case "print":
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
                    case "delete":
                        id = sc.nextInt();
                        semdata.delete(id);
                        break;
                    case "search":
                        id = sc.nextInt();
                        semdata.search(id);

                        break;
                    default:
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
