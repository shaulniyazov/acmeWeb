package com.acme;

public class TotalMemoryDetails extends SystemDetails {
    //todo make it get the processor amount from runtime
    SystemDetails systemDetails;
    public void returnString() {
        details.append(", and there is a total of " + Runtime.getRuntime().totalMemory() + " bytes of JVM memory");
//        return systemDetails.detailString() +  ", and there is a total of " + Runtime.getRuntime().totalMemory() + " bytes of JVM memory";
    }
}
