package viewer;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    
    public static int getChoice(Object[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Your options from 1 - " + options.length + ": ");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }
    
    public static int getChoiceFromList(List<String> options) {
        int idx = 1;
        for (String s: options) {
            System.out.println(idx++ + ". " + s);
        }
        System.out.print("Your options from 1 - " + options.size() + ": ");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine().trim());
    }
}
