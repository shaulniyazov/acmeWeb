package com.acme.details;

import com.acme.details.SystemDetails;
import com.acme.statusmgr.beans.ServerStatus;

public class FreeMemoryDetails extends SystemDetails {
    public FreeMemoryDetails(ServerStatus serverStatus){
        this.serverStatus = serverStatus;
    }

    @Override
    public String getStatusDesc() {
        return serverStatus.getStatusDesc() + ", and there are " + serverStatus.facade.freeMemory() + " bytes of JVM memory free";
    }
}
