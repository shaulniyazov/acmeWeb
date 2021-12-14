package com.acme.statusmgr.beans;

import com.acme.DetailsFacade;
import com.acme.RealDetailsFacade;
import com.acme.details.*;
import com.acme.servermgr.ServerManager;

import java.util.List;

/**
 * A POJO that represents Server Status and can be returned as the result of a request.
 */
public class ServerStatus {

    private long id;
    private String contentHeader;
    private String statusDesc = "Unknown";
    public static DetailsFacade facade = new RealDetailsFacade();

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

        //Obtain current status of server
        this.statusDesc = "Server is " + ServerManager.getCurrentServerStatus();

        for (int i = 0; i < detailsString.size(); i++) {
            this.statusDesc = stringToDetail(detailsString.get(i)) ;
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

    public static void setFacade(DetailsFacade currentFacade){
        facade = currentFacade;
    }
    public String stringToDetail(String detail){
       // List<SystemDetails> convertedStrings = new ArrayList<>();
        // availableProcessors,freeJVMMemory,totalJVMMemory,jreVersion,tempLocation
            if(detail.equals("availableProcessors")){
                return new AvailableProcessorDetails(this).getStatusDesc();
            }
            else if(detail.equals("freeJVMMemory")){
                return new FreeMemoryDetails(this).getStatusDesc();
            }
            else if(detail.equals("totalJVMMemory")){
                return new TotalMemoryDetails(this).getStatusDesc();
            }
            else if(detail.equals("jreVersion")){
                return new JreVersionDetails(this).getStatusDesc();
            }
            else if(detail.equals("tempLocation")){
                return new TempLocationDetails(this).getStatusDesc();
            }
            return "ItDon'tWork";
        //todo return errorString;
    }
}
