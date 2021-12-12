package com.acme;

import com.acme.statusmgr.beans.ServerStatus;

public class TotalMemoryDetails extends SystemDetails {
    public TotalMemoryDetails(ServerStatus serverStatus){
        this.serverStatus = serverStatus;
    }

    @Override
    public String getStatusDesc() {
        return serverStatus.getStatusDesc() + ", and there is a total of " + Runtime.getRuntime().totalMemory() + " bytes of JVM memory";
    }
}
