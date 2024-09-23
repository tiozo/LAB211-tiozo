package Viewer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import utilities.Validation;

public class Menu {
    
    private static final Set<String> uniqueSet = new HashSet<>();

    public static List<String> uniqueElements(List<String> list) {
        uniqueSet.clear();
        uniqueSet.addAll(list);
        return new ArrayList<>(uniqueSet);
    }

    public static List<String> uniqueElementsStream(List<String> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }
    
    public static int getChoice(Object[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        //System.out.print("Your options from 1 - " + options.length + ": ");
        //Scanner sc = new Scanner(System.in);
        int res = Validation.checkInt("Your options from 1 - " + options.length + ": ", options.length);
        return res;
    }
    
    public static int getChoiceFromList(List<String> options) {
        int idx = 1;
        for (String s: options) {
            System.out.println(idx++ + ". " + s);
        }
        int res = Validation.checkInt("Your options from 1 - " + options.size() + ": ", options.size());
        return res;
    }
}
