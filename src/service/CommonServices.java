package src.service;

import java.util.LinkedList;

public class CommonServices {
    public String convertLinkToString(LinkedList<String[]> linkedList) {
        String str="";
        int index =0;
        for (String[] arr: linkedList
             ) {

            for (int i = 0; i < arr.length ; i++) {
                str+=arr[i];
                if (i!= arr.length-1){
                    str+=",";
                }
            }
            if (index!=linkedList.size()) {
                str = str + "\n";
            }
            index++;
        }
        return str;
    }
}
