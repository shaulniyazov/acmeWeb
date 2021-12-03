package com.acme;

import com.acme.servermgr.ServerManager;

import java.util.ArrayList;
import java.util.List;

public abstract class SystemDetails {
    //the thing that will be decorated
    static StringBuilder details = new StringBuilder("up");

    //will be called at the end to give the full list of details
    public static String detailString(){
        return details.toString();
    }

    //method that the details that extend SystemDetails will implement
    public abstract void returnString();

    public static List<SystemDetails> StringToDetail(List<String> stringsToConvert){
        List<SystemDetails> convertedStrings = new ArrayList<>();
       // availableProcessors,freeJVMMemory,totalJVMMemory,jreVersion,tempLocation
        for (int i = 0; i < stringsToConvert.size(); i++) {
            if(stringsToConvert.get(i).equals("availableProcessors")){
                convertedStrings.add(new AvailableProcessorsDetails());
            }
            else if(stringsToConvert.get(i).equals("freeJVMMemory")){
                convertedStrings.add(new FreeMemoryDetails());
            }
            else if(stringsToConvert.get(i).equals("totalJVMMemory")){
                convertedStrings.add(new TotalMemoryDetails());
            }
            else if(stringsToConvert.get(i).equals("jreVersion")){
                convertedStrings.add(new JreVersionDetails());
            }
            else if(stringsToConvert.get(i).equals("tempLocation")){
                convertedStrings.add(new TempLocationDetails());
            }
        }
        return convertedStrings;
    }
}
