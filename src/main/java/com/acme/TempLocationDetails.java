package com.acme;

public class TempLocationDetails extends SystemDetails {
    //todo make it get the processor amount from System
    SystemDetails systemDetails;

    public void returnString() {
        details.append(", and the server's temp file location is " + System.getenv("TEMP"));
//        return systemDetails.detailString() + ", and the server's temp file location is " + System.getenv("TEMP");

    }

    public TempLocationDetails(){

    }
}
