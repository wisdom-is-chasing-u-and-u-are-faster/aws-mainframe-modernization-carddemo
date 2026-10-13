package com.client.core_financial_processing.controller;

import com.client.core_financial_processing.dto.Cbcus01cRequest;
import com.client.core_financial_processing.dto.Cbcus01cResponse;
import com.client.core_financial_processing.service.Cbcus01cService;
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

@WebMvcTest(Cbcus01cController.class)
@DisplayName("Cbcus01cController REST API Endpoints Tests")
class Cbcus01cControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Cbcus01cService service;

    @Test
    @DisplayName("GET /api/v1/core_financial_processing/cbcus01c/health returns HTTP 200 and status ACTIVE")
    void testHealthEndpoint() throws Exception {
        when(service.getModuleId()).thenReturn("CBCUS01C");
        when(service.getSourcePath()).thenReturn("app/cbl/CBCUS01C.cbl");

        mockMvc.perform(get("/api/v1/core_financial_processing/cbcus01c/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("ACTIVE"))
            .andExpect(jsonPath("$.moduleId").value("CBCUS01C"));
    }

    @Test
    @DisplayName("POST /api/v1/core_financial_processing/cbcus01c/cbcus01c0031 returns HTTP 200")
    void testActionEndpoint_Success() throws Exception {
        Cbcus01cResponse mockResponse = Cbcus01cResponse.success(
            "BR-CBCUS01C-0001",
            "Rule executed successfully",
            new BigDecimal("850.00"),
            "TXN-1001"
        );
        when(service.executeCbcus01c0031(any(Cbcus01cRequest.class))).thenReturn(mockResponse);

        String jsonPayload = "{\"accountId\": \"00000000001\", \"amount\": 150.00, \"transactionType\": \"DEBIT\"}";

        mockMvc.perform(post("/api/v1/core_financial_processing/cbcus01c/cbcus01c0031")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("SUCCESS"))
            .andExpect(jsonPath("$.newBalance").value(850.00));
    }
}
