package com.client.core_financial_processing.controller;

import com.client.core_financial_processing.dto.Cbstm03aRequest;
import com.client.core_financial_processing.dto.Cbstm03aResponse;
import com.client.core_financial_processing.service.Cbstm03aService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Cbstm03aController.class)
@DisplayName("Cbstm03aController REST API Endpoints Tests")
class Cbstm03aControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Cbstm03aService service;

    @Test
    @DisplayName("GET /api/v1/core_financial_processing/cbstm03a/health returns HTTP 200 and status ACTIVE")
    void testHealthEndpoint() throws Exception {
        when(service.getModuleId()).thenReturn("CBSTM03A");
        when(service.getSourcePath()).thenReturn("app/cbl/CBSTM03A.CBL");

        mockMvc.perform(get("/api/v1/core_financial_processing/cbstm03a/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("ACTIVE"))
            .andExpect(jsonPath("$.moduleId").value("CBSTM03A"));
    }

    @Test
    @DisplayName("POST /api/v1/core_financial_processing/cbstm03a/cbstm03a0043 returns HTTP 200")
    void testActionEndpoint_Success() throws Exception {
        Cbstm03aResponse mockResponse = Cbstm03aResponse.success(
            "BR-CBSTM03A-0001",
            "Rule executed successfully",
            new BigDecimal("850.00"),
            "TXN-1001"
        );
        when(service.executeCbstm03a0043(any(Cbstm03aRequest.class))).thenReturn(mockResponse);

        String jsonPayload = """
            {
                "accountId": "00000000001",
                "amount": 150.00,
                "transactionType": "DEBIT",
                "referenceNumber": "TXN-1001"
            }
            """;

        mockMvc.perform(post("/api/v1/core_financial_processing/cbstm03a/cbstm03a0043")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("SUCCESS"))
            .andExpect(jsonPath("$.newBalance").value(850.00));
    }
}
