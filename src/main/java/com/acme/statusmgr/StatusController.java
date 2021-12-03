package com.acme.statusmgr;

import com.acme.SystemDetails;
import com.acme.statusmgr.beans.ServerStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Controller for all web/REST requests about the status of servers
 * <p>
 * For initial school project - just handles info about this server
 * Syntax for URLS:
 * All start with /server
 * /status  will give back status of server
 * a param of 'name' specifies a requestor name to appear in response
 * <p>
 * Examples:
 * http://localhost:8080/server/status
 * <p>
 * http://localhost:8080/server/status?name=Noach
 */
@RestController
@RequestMapping("/server")
public class StatusController {

    protected static final String template = "Server Status requested by %s";
    protected final AtomicLong counter = new AtomicLong();
    protected static List<SystemDetails> detailsTest = new ArrayList<>();

    /**
     * Process a request for server status information
     *
     * @param name optional param identifying the requestor
     * @return a ServerStatus object containing the info to be returned to the requestor
     */
    @RequestMapping("/status")
    public ServerStatus processRequest(@RequestParam(value = "name", defaultValue = "Anonymous") String name,
                                       @RequestParam(required = false, value = "details", defaultValue = "none")  List<String> details) {
        Logger logger = LoggerFactory.getLogger("detailsLog");
        logger.info(details.toString());
        return new ServerStatus(counter.incrementAndGet(),
                String.format(template, name));
    }

    /*this is what it looks like:
http://localhost:8080/server/status/detailed?name=Yankel&detailsString=availableProcessors,freeJVMMemory,totalJVMMemory,jreVersion,tempLocation
{"id":12,"contentHeader":"Server Status requested by Yankel","statusDesc":"Server is up, and there are 4 processors available, and there are 4 processors available"}
     */
    @RequestMapping("/status/detailed")
    public ServerStatus processDetailsRequest(@RequestParam(value = "name", defaultValue = "Anonymous") String name,
                                       @RequestParam(required = true, value = "detailsString")  List<String> detailsString) {

//        Logger logger = LoggerFactory.getLogger("detailsLog");
//        logger.info(details.toString());
//        for (int i = 0; i < details.size(); i++) {
//            detailsTest.add(details.get(i));
//
//        }
        List<SystemDetails> details  = SystemDetails.StringToDetail(detailsString);
        return new ServerStatus(counter.incrementAndGet(),
                String.format(template, name),
                details);
    }
}
