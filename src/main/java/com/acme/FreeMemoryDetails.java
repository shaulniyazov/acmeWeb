package com.acme;

import com.acme.statusmgr.beans.ServerStatus;

public class FreeMemoryDetails extends SystemDetails {
    public FreeMemoryDetails(ServerStatus serverStatus){
        this.serverStatus = serverStatus;
    }

    @Override
    public String getStatusDesc() {
        return serverStatus.getStatusDesc() + ", and there are " +Runtime.getRuntime().freeMemory()+ " bytes of JVM memory free";
    }
}
