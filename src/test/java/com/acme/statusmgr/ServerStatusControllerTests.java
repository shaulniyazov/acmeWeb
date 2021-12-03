/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.statusmgr;

import com.acme.FreeMemoryDetails;
import com.acme.JreVersionDetails;
import com.acme.SystemDetails;
import com.acme.servermgr.ServerManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ServerStatusControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("/server/status")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up"));
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {

        this.mockMvc.perform(get("/server/status").param("name", "RebYid"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by RebYid"));
    }

    @Test
    public void detailed_name_availProc() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=availableProcessors&name=Yankel"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Avrumel"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and there are 4 processors available"));
    }

    @Test
    public void detailed_name_availProc_freeMemory() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=availableProcessors,freeJVMMemory&name=Yankel"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Shmerel"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and there are 4 processors available"));
    }

    @Test
    public void detailed_name_availProc_freeMemory_jreVersion() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=availableProcessors,freeJVMMemory,jreVersion&name=Yankel"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Berel"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and there are 4 processors available"));
    }

    @Test
    //todo make it catch an exception or something that will then print out whatever the prof. said to
    public void detailed_name_error() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=schedule&name=Yankel"))
                .andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(status().reason("This application has no explicit mapping for /error, so you are seeing this as a fallback.\n" +
                        //todo change the details
                        "Wed Apr 07 17:06:55 EDT 2021\n" +
                        "There was an unexpected error (type=Bad Request, status=400).\n" +
                        "Invalid details option: junkERROR"));
    }
    @Test
    public void getCurrentServerStatusDetailsMethod(){
        ArrayList<SystemDetails> systemDetails = new ArrayList<>();
        systemDetails.add(new FreeMemoryDetails());
        systemDetails.add(new JreVersionDetails());
        ServerManager.getCurrentServerStatus(systemDetails);
    }



}
