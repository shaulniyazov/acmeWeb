package com.acme.details;

import com.acme.DetailsFacade;
import com.acme.details.SystemDetails;
import com.acme.statusmgr.beans.ServerStatus;

public class JreVersionDetails extends SystemDetails {
    public JreVersionDetails(ServerStatus serverStatus){
        this.serverStatus = serverStatus;
    }

    public JreVersionDetails() {

    }

    @Override
    public String getStatusDesc() {
        return serverStatus.getStatusDesc() + ", and the JRE version is " + ServerStatus.facade.jreVersion();
    }
}
