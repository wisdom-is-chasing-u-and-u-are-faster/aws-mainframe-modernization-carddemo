package com.client.core_financial_processing.controller;

import com.client.core_financial_processing.dto.CsutldtcRequest;
import com.client.core_financial_processing.dto.CsutldtcResponse;
import com.client.core_financial_processing.service.CsutldtcService;
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

@WebMvcTest(CsutldtcController.class)
@DisplayName("CsutldtcController REST API Endpoints Tests")
class CsutldtcControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CsutldtcService service;

    @Test
    @DisplayName("GET /api/v1/core_financial_processing/csutldtc/health returns HTTP 200 and status ACTIVE")
    void testHealthEndpoint() throws Exception {
        when(service.getModuleId()).thenReturn("CSUTLDTC");
        when(service.getSourcePath()).thenReturn("app/cbl/CSUTLDTC.cbl");

        mockMvc.perform(get("/api/v1/core_financial_processing/csutldtc/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("ACTIVE"))
            .andExpect(jsonPath("$.moduleId").value("CSUTLDTC"));
    }

    @Test
    @DisplayName("POST /api/v1/core_financial_processing/csutldtc/csutldtc0283 returns HTTP 200")
    void testActionEndpoint_Success() throws Exception {
        CsutldtcResponse mockResponse = CsutldtcResponse.success(
            "BR-CSUTLDTC-0001",
            "Rule executed successfully",
            new BigDecimal("850.00"),
            "TXN-1001"
        );
        when(service.executeCsutldtc0283(any(CsutldtcRequest.class))).thenReturn(mockResponse);

        String jsonPayload = "{\"accountId\": \"00000000001\", \"amount\": 150.00, \"transactionType\": \"DEBIT\"}";

        mockMvc.perform(post("/api/v1/core_financial_processing/csutldtc/csutldtc0283")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("SUCCESS"))
            .andExpect(jsonPath("$.newBalance").value(850.00));
    }
}
