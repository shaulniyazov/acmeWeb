package com.acme.details;

import com.acme.statusmgr.beans.ServerStatus;

import java.util.ArrayList;
import java.util.List;

public abstract class SystemDetails {
    //the thing I decorate
    ServerStatus serverStatus;

    SystemDetails(){}

    public abstract String getStatusDesc();

}
