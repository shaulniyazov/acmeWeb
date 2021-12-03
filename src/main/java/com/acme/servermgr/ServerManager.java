package com.acme.servermgr;

import com.acme.SystemDetails;
import com.acme.TempLocationDetails;

import java.util.List;

/**
 * Manage all servers (service providers) being tracked by the Acme server tracking system
 * For now just some simple static methods for use in school project.
 * Treat this as a 'black box' that gives back indicators like 'up', true, or percentages like '95%' for
 * the various 'services' that are being managed.
 */
public class ServerManager {

    /**
     * Get the status of this server
     * @return a descriptive string about the servers status
     */
    static public String getCurrentServerStatus() {
        return "up";  // The server is up
    }

    static public String getCurrentServerStatus(List<SystemDetails> details) {
        for (int i = 0; i < details.size(); i++) {
            details.get(i).returnString();
        }
        System.out.println(SystemDetails.detailString());
        return SystemDetails.detailString();  // The server is up
    }

    /**
     * Find out if this server is operating normally
     * @return Boolean indicating if server is operating normally
     */
    static public Boolean isOperatingNormally()
    {
        return true;
    }
}
