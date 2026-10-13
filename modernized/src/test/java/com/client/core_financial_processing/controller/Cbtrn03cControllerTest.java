package com.client.core_financial_processing.controller;

import com.client.core_financial_processing.dto.Cbtrn03cRequest;
import com.client.core_financial_processing.dto.Cbtrn03cResponse;
import com.client.core_financial_processing.service.Cbtrn03cService;
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

@WebMvcTest(Cbtrn03cController.class)
@DisplayName("Cbtrn03cController REST API Endpoints Tests")
class Cbtrn03cControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Cbtrn03cService service;

    @Test
    @DisplayName("GET /api/v1/core_financial_processing/cbtrn03c/health returns HTTP 200 and status ACTIVE")
    void testHealthEndpoint() throws Exception {
        when(service.getModuleId()).thenReturn("CBTRN03C");
        when(service.getSourcePath()).thenReturn("app/cbl/CBTRN03C.cbl");

        mockMvc.perform(get("/api/v1/core_financial_processing/cbtrn03c/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("ACTIVE"))
            .andExpect(jsonPath("$.moduleId").value("CBTRN03C"));
    }

    @Test
    @DisplayName("POST /api/v1/core_financial_processing/cbtrn03c/cbtrn03c0059 returns HTTP 200")
    void testActionEndpoint_Success() throws Exception {
        Cbtrn03cResponse mockResponse = Cbtrn03cResponse.success(
            "BR-CBTRN03C-0001",
            "Rule executed successfully",
            new BigDecimal("850.00"),
            "TXN-1001"
        );
        when(service.executeCbtrn03c0059(any(Cbtrn03cRequest.class))).thenReturn(mockResponse);

        String jsonPayload = "{\"accountId\": \"00000000001\", \"amount\": 150.00, \"transactionType\": \"DEBIT\"}";

        mockMvc.perform(post("/api/v1/core_financial_processing/cbtrn03c/cbtrn03c0059")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("SUCCESS"))
            .andExpect(jsonPath("$.newBalance").value(850.00));
    }
}
