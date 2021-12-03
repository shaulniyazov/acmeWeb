package com.acme;

public class AvailableProcessorsDetails extends SystemDetails {
    int availableProcessors;
    SystemDetails systemDetails;
    public void returnString() {
        details.append(", and there are " + Runtime.getRuntime().availableProcessors() + " processors available");
//        return systemDetails.detailString() + ", and there are " + Runtime.getRuntime().availableProcessors() + " processors available";
    }

    AvailableProcessorsDetails(){

    }
}
