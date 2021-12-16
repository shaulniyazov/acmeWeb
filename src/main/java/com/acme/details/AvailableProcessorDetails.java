package com.acme.details;

import com.acme.RealDetailsFacade;
import com.acme.statusmgr.beans.ServerStatus;

public class AvailableProcessorDetails extends SystemDetails {
    public AvailableProcessorDetails(ServerStatus serverStatus){
        this.serverStatus = serverStatus;
    }

    @Override
    public String getStatusDesc() {
        return serverStatus.getStatusDesc() + ", and there are " + serverStatus.facade.availableProcessors() + " processors available" ;
    }


}
