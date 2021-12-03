package com.acme;

public class FreeMemoryDetails extends SystemDetails {
    //todo make it get the processor amount from runtime
    static SystemDetails systemDetails;

    public void returnString()
    {
        details.append(", and there are " + Runtime.getRuntime().freeMemory() + " bytes of JVM memory free");
//        return systemDetails.detailString() + ", and there are " +Runtime.getRuntime().freeMemory()+ " bytes of JVM memory free";

    }

    public FreeMemoryDetails(){

    }

}
