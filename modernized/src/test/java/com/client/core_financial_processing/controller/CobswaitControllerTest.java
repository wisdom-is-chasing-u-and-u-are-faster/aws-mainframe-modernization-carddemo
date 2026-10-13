package com.client.core_financial_processing.controller;

import com.client.core_financial_processing.dto.CobswaitRequest;
import com.client.core_financial_processing.dto.CobswaitResponse;
import com.client.core_financial_processing.service.CobswaitService;
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

@WebMvcTest(CobswaitController.class)
@DisplayName("CobswaitController REST API Endpoints Tests")
class CobswaitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CobswaitService service;

    @Test
    @DisplayName("GET /api/v1/core_financial_processing/cobswait/health returns HTTP 200 and status ACTIVE")
    void testHealthEndpoint() throws Exception {
        when(service.getModuleId()).thenReturn("COBSWAIT");
        when(service.getSourcePath()).thenReturn("app/cbl/COBSWAIT.cbl");

        mockMvc.perform(get("/api/v1/core_financial_processing/cobswait/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("ACTIVE"))
            .andExpect(jsonPath("$.moduleId").value("COBSWAIT"));
    }

    @Test
    @DisplayName("POST /api/v1/core_financial_processing/cobswait/cobswait0067 returns HTTP 200")
    void testActionEndpoint_Success() throws Exception {
        CobswaitResponse mockResponse = CobswaitResponse.success(
            "BR-COBSWAIT-0001",
            "Rule executed successfully",
            new BigDecimal("850.00"),
            "TXN-1001"
        );
        when(service.executeCobswait0067(any(CobswaitRequest.class))).thenReturn(mockResponse);

        String jsonPayload = """
            {
                "accountId": "00000000001",
                "amount": 150.00,
                "transactionType": "DEBIT",
                "referenceNumber": "TXN-1001"
            }
            """;

        mockMvc.perform(post("/api/v1/core_financial_processing/cobswait/cobswait0067")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("SUCCESS"))
            .andExpect(jsonPath("$.newBalance").value(850.00));
    }
}
