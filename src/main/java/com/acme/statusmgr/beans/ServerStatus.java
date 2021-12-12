package com.acme.statusmgr.beans;

import com.acme.details.*;
import com.acme.servermgr.ServerManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A POJO that represents Server Status and can be returned as the result of a request.
 */
public class ServerStatus {

    private long id;
    private String contentHeader;
    private String statusDesc = "Unknown";
    private List<SystemDetails> details;

    /**
     * Construct a ServerStatus using info passed in for identification, and obtaining current
     * server status from the appropriate Manager class.
     * This class must return a pretty, english-like representation of the server status.
     *
     * @param id            a numeric identifier/counter of which request this is
     * @param contentHeader info about the request
     */
    public ServerStatus(long id, String contentHeader) {
        this.id = id;
        this.contentHeader = contentHeader;

        // Obtain current status of server
        this.statusDesc = "Server is " + ServerManager.getCurrentServerStatus();
    }

    public ServerStatus(long id, String contentHeader, List<String> detailsString) {
        this.id = id;
        this.contentHeader = contentHeader;
        List<SystemDetails> details = StringToDetail(detailsString);
        this.details = details;

        //Obtain current status of server
        this.statusDesc = "Server is " + ServerManager.getCurrentServerStatus();

        for (int i = 0; i < details.size(); i++) {
            this.statusDesc = details.get(i).getStatusDesc();
        }

    }

    public ServerStatus() {

    }

    /**
     * get the id of this request
     *
     * @return a numeric id that increases during life of server for each request .
     */
    public long getId() {
        return id;
    }

    /**
     * Get the content header that was specified by the request
     *
     * @return some string
     */
    public String getContentHeader() {
        return contentHeader;
    }

    /**
     * Get an english-like description of the server's status
     *
     * @return A string describing status
     */
    public String getStatusDesc() {
        return statusDesc;
    }


    public List<SystemDetails> StringToDetail(List<String> stringsToConvert){
        List<SystemDetails> convertedStrings = new ArrayList<>();
        // availableProcessors,freeJVMMemory,totalJVMMemory,jreVersion,tempLocation
        for (int i = 0; i < stringsToConvert.size(); i++) {
            if(stringsToConvert.get(i).equals("availableProcessors")){
                convertedStrings.add(new AvailableProcessorDetails(this));
            }
            else if(stringsToConvert.get(i).equals("freeJVMMemory")){
                convertedStrings.add(new FreeMemoryDetails(this));
            }
            else if(stringsToConvert.get(i).equals("totalJVMMemory")){
                convertedStrings.add(new TotalMemoryDetails(this));
            }
            else if(stringsToConvert.get(i).equals("jreVersion")){
                convertedStrings.add(new JreVersionDetails(this));
            }
            else if(stringsToConvert.get(i).equals("tempLocation")){
                convertedStrings.add(new TempLocationDetails(this));
            }
        }
        return convertedStrings;
    }
}
