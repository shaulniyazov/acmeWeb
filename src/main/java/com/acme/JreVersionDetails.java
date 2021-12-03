package com.acme;

public class JreVersionDetails extends SystemDetails {
    //todo make it get the processor amount from runtime
    SystemDetails systemDetails;

    public void returnString() {
        details.append(", and the JRE version is " + Runtime.version());
//        return systemDetails.detailString() + ", and the JRE version is " + Runtime.version();
    }
}
