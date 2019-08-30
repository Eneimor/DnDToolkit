package dnd.Utils;

import java.util.ArrayList;

public class Misc {

    //Метод. из строки в коллекцию
    public ArrayList<Integer> toArray(String str) {
        String string = str;
        String[] firstList;
        String delimeter = ",";
        firstList = str.split(delimeter);
        ArrayList<Integer> outerList = new ArrayList<>();
        for (int i = 0; i < firstList.length; i++) {
            outerList.add(Integer.parseInt(firstList[i]));
        }
        return outerList;
    };

    //Метод из коллекции в строку
    public String fromArray(ArrayList<Integer> innerList) {
        ArrayList<Integer> in = innerList;
        String str = "";
        for (int i = 0; i < in.size(); i++) {
            str += in.get(i) + ",";
        }
        return str.substring(0,str.length()-1);
    }

}
