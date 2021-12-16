package com.acme.details;

import com.acme.statusmgr.beans.ServerStatus;

public abstract class SystemDetails {
    //the thing I decorate
    ServerStatus serverStatus;

    SystemDetails(){}

    public abstract String getStatusDesc();

}
