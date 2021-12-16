package com.acme.details;

import com.acme.statusmgr.beans.ServerStatus;

public class TotalMemoryDetails extends SystemDetails {


    public TotalMemoryDetails(ServerStatus serverStatus){
        this.serverStatus = serverStatus;
    }

    @Override
    public String getStatusDesc() {
        return serverStatus.getStatusDesc() + ", and there is a total of " + serverStatus.facade.totalMemory()+ " bytes of JVM memory";
    }
}
