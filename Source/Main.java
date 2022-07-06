import java.io.*;
import java.util.*;

public class Main {
    public static HashMap<String, List<String>> map = new HashMap<String, List<String>>();
    public static ArrayList<String> history = new ArrayList<String>();
    public static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        ReadFile("newslang.txt");
        if (map.isEmpty()) {
            ReadFile("slang.txt");
        }
        history = loadHistory("history.txt");
        Menu();
    }

    public static void Menu() {


        System.out.println("======================MENU======================");
        System.out.println("1. Search by Slang Word");
        System.out.println("2. Search by Definition");
        System.out.println("3. Show History");
        System.out.println("4. Add a Slang Word");
        System.out.println("5. Edit a Slang Word");
        System.out.println("6. Delete a Slang Word");
        System.out.println("7. Reset");
        System.out.println("8. Get Random 1 Slang Word");
        System.out.println("9. Find Definition by Slang word(Mini Game 1)");
        System.out.println("10. Find Slang Word by Definition(Mini Game 2)");
        System.out.println("11. Exit");
        System.out.println("================================================");
        System.out.print("Press Your choice is: ");
        int choice = 0;
        try {
            choice = sc.nextInt();
        } catch (Exception ex) {
            choice = 0;
            System.out.println("Your Value is Wrong. Error: " + ex);
        }
        String ignore = sc.nextLine();
        if (choice == 1) {
            searchBySlang();
        } else if (choice == 2) {
            searchByDef();
        } else if (choice == 3) {
            showHistory();
        } else if (choice == 4) {
            addSlang();
        } else if (choice == 5) {
            editSlang();
        } else if (choice == 6) {

        } else if (choice == 7) {

        } else if (choice == 8) {

        } else if (choice == 9) {

        } else if (choice == 10) {

        } else {
            WriteHistory("history.txt");
            WriteFile("newslang.txt");
            System.exit(0);
        }
    }

    public static void searchBySlang() {

        System.out.print("Press a Slang word: ");
        String key = sc.nextLine();
        history.add(key);
        key = key.toUpperCase();
        if (!map.containsKey(key)) {
            System.out.println("Not Found !");
        } else {
            List<String> l = map.get(key);
            System.out.println("Definition:");
            for (String s : l) {
                System.out.println("- " + s);
            }
        }
        pauseTerminal();
        Menu();
    }

    public static void searchByDef() {

        ArrayList<String> slang_means = new ArrayList<String>();
        System.out.print("Press a Definition: ");
        String word = sc.nextLine();
        history.add(word);
        word = word.toLowerCase();
        for (String i : map.keySet()) {
            for (String s: map.get(i)) {
                if (s.toLowerCase().contains(word)) {
                    slang_means.add(i);
                }
            }
        }

        if (!slang_means.isEmpty()) {
            System.out.println("Slang Words found: ");
            for (String i : slang_means) {
                System.out.print("- " + i + ": ");
                ShowDefinition(i);
            }
        }
        else {
            System.out.println("Not Found !");
        }
        pauseTerminal();
        Menu();
    }

    public static void ShowDefinition(String slang) {
        List<String> l = map.get(slang);
        for (String s: l) {
            System.out.print(s + ", ");
        }
        System.out.print("\b\b     \n");
    }

    public static void showHistory() {
        System.out.println("History:");
        for (String i : history) {
            System.out.println("- " + i);
        }
        pauseTerminal();
        Menu();
    }

    public static void WriteHistory(String file_name) {
        try {
            File f = new File(file_name);
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String i : history) {
                fw.write(i + "\n");
            }

            fw.close();
            bw.close();
        }

        catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
    }

    public static ArrayList<String> loadHistory (String file_name) {
        ArrayList<String> his = new ArrayList<String>();
        try {
            File f = new File(file_name);
            FileReader fr = new FileReader(f);

            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                his.add(line);
            }

            fr.close();
            br.close();
        }
        catch (Exception ex) {
            System.out.println("Error: "+ex);
        }

        return his;
    }

    public static void addSlang() {

        System.out.print("Enter Slang Word: ");
        String slang = sc.nextLine();
        System.out.print("Enter Definition: ");
        String means = sc.nextLine();
        if (map.containsKey(slang)) {
            System.out.println("Slang Word was existed, Choose: ");
            System.out.println("1. Overwrite");
            System.out.println("2. Duplicate");
            int c = sc.nextInt();
            if (c == 1) {
                addSlang(slang, means);
            }
            else if (c == 2) {
                Duplicate(slang, means);
            }
        }
        else {
            addSlang(slang, means);
        }
        pauseTerminal();
        Menu();
    }
    public static void addSlang(String slang, String means) {
        ArrayList<String> tmp = new ArrayList<String>();
        tmp.add(means);
        map.put(slang.toUpperCase(), tmp);
        System.out.println("Add Successfully!!!");
    }

    public static void Duplicate(String slang, String means) {
        List<String> tmp = new ArrayList<String>();
        tmp = map.get(slang);
        tmp.add(means);
        map.put(slang.toUpperCase(), tmp);
        System.out.println("Add Successfully!!!");
    }

    public static void editSlang() {
        System.out.print("Press Slang Word you want to edit: ");
        String slang = sc.nextLine();
        slang = slang.toUpperCase();
        if (!map.containsKey(slang)) {
            System.out.println("This Slang Word does not exist!!");
        }
        else {
            System.out.print("Press New Slang Word: ");
            String new_slang = sc.nextLine();
            System.out.print("Press New Meaning: ");
            String new_means = sc.nextLine();
            List<String> tmp = new ArrayList<String>();
            tmp.add(new_means);
            map.put(new_slang.toUpperCase(), tmp);
            map.remove(slang);
            System.out.println("Edit Successfully!!");
        }
        pauseTerminal();
        Menu();
    }

    public static void ReadFile(String file_name) {
        try {
            File f = new File(file_name);
            FileReader fr = new FileReader(f);

            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("`")) {
                    List<String> tar = new ArrayList<String>();

                    String[] s = line.split("`");
                    if (s[1].contains("|")) {
                        String[] tmp = s[1].split("\\|");
                        for (int i = 0; i < tmp.length; i++) {
                            tmp[i] = tmp[i].trim();
                        }
                        tar = Arrays.asList(tmp);
                    } else {
                        tar.add(s[1]);
                    }
                    map.put(s[0], tar);
                }
            }

            fr.close();
            br.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    public static void WriteFile(String file_name) {
        try {
            File f = new File(file_name);
            FileWriter fw = new FileWriter(f);
            for (String key : map.keySet()) {
                fw.write(key + "`");
                List<String> tmp = map.get(key);
                int i = 0;
                for (i = 0; i < tmp.size() - 1; i++) {
                    fw.write(tmp.get(i) + "| ");
                }

                fw.write(tmp.get(i) + "\n");
            }

            fw.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    public static void pauseTerminal() {
        System.out.println("-----Press Any Key To Continue-----");
        new java.util.Scanner(System.in).nextLine();
    }
}