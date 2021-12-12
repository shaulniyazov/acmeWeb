package com.acme;

import com.acme.statusmgr.beans.ServerStatus;

public class JreVersionDetails extends SystemDetails {
    public JreVersionDetails(ServerStatus serverStatus){
        this.serverStatus = serverStatus;
    }

    @Override
    public String getStatusDesc() {
        return serverStatus.getStatusDesc() + ", and the JRE version is " + Runtime.version();
    }
}
