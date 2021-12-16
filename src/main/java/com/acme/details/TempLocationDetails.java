package com.acme.details;

import com.acme.details.SystemDetails;
import com.acme.statusmgr.beans.ServerStatus;

public class TempLocationDetails extends SystemDetails {
    public TempLocationDetails(ServerStatus serverStatus){
        this.serverStatus = serverStatus;
    }

    @Override
    public String getStatusDesc() {
        return serverStatus.getStatusDesc() + ", and the server's temp file location is " + serverStatus.facade.tempLocation();
    }
}
