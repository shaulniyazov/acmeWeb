package com.acme.statusmgr;

import com.acme.*;
import com.acme.statusmgr.beans.ServerStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Controller for all web/REST requests about the status of servers
 * <p>
 * BadRequestal school project - just handles info about this server
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
//        logger.info(detailsString.toString());
//        for (int i = 0; i < detailsString.size(); i++) {
//            detailsTest.add(details.get(i));
//
//        }

        return new ServerStatus(counter.incrementAndGet(),
                String.format(template, name),
                detailsString);
    }

    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity sendViaException(String invalidDetail) {
        throw new BadRequestException(invalidDetail);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public class BadRequestException extends RuntimeException {
        BadRequestException(String invalidDetail){
            super("Invalid details option: " + invalidDetail);
        }
    }

}
