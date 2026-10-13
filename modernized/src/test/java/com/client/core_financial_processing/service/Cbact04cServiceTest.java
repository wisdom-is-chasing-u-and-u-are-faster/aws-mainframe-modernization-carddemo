package com.client.core_financial_processing.service;

import com.client.core_financial_processing.dto.Cbact04cRequest;
import com.client.core_financial_processing.dto.Cbact04cResponse;
import com.client.core_financial_processing.model.Cbact04c;
import com.client.core_financial_processing.repository.Cbact04cRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Cbact04cService Business Domain Service Tests")
class Cbact04cServiceTest {

    @Mock
    private Cbact04cRepository repository;

    @InjectMocks
    private Cbact04cService service;

    @BeforeEach
    void setUp() {
        // Setup initial test fixture state
    }

    @Test
    @DisplayName("Verify module metadata and mainframe source path")
    void testModuleMetadata() {
        assertNotNull(service.getModuleId());
        assertEquals("CBACT04C", service.getModuleId());
        assertNotNull(service.getSourcePath());
    }

    @Test
    @DisplayName("Happy path: execute business rule with valid debit request")
    void testExecuteRule_Success() {
        Cbact04cRequest request = new Cbact04cRequest(
            "00000000001",
            new BigDecimal("150.00"),
            "DEBIT",
            "TXN-1001",
            Map.of()
        );
        Cbact04cResponse response = service.executeCbact04c0023(request);

        assertNotNull(response);
        assertEquals("SUCCESS", response.status());
        assertNotNull(response.newBalance());
        assertTrue(response.message().contains("successfully"));
    }

    @Test
    @DisplayName("Overdraft guard: reject transaction exceeding overdraft limits")
    void testExecuteRule_OverdraftDeclined() {
        Cbact04cRequest request = new Cbact04cRequest(
            "00000000001",
            new BigDecimal("999999.00"),
            "DEBIT",
            "TXN-OVERDRAFT",
            Map.of()
        );
        Cbact04cResponse response = service.executeCbact04c0023(request);

        assertNotNull(response);
        assertEquals("DECLINED_INSUFFICIENT_FUNDS", response.status());
        assertTrue(response.message().toLowerCase().contains("overdraft"));
    }

    @Test
    @DisplayName("Validation guard: reject negative transaction amount")
    void testExecuteRule_NegativeAmountValidation() {
        Cbact04cRequest request = new Cbact04cRequest(
            "00000000001",
            new BigDecimal("-50.00"),
            "DEBIT",
            "TXN-NEG",
            Map.of()
        );
        Cbact04cResponse response = service.executeCbact04c0023(request);

        assertNotNull(response);
        assertEquals("VALIDATION_ERROR", response.status());
    }

    @Test
    @DisplayName("Resilience: handle null request gracefully with baseline default")
    void testExecuteRule_NullRequestHandling() {
        Cbact04cResponse response = service.executeCbact04c0023((Cbact04cRequest) null);

        assertNotNull(response);
        assertNotNull(response.status());
    }

    @Test
    @DisplayName("Backward compatibility: handle legacy Map payload")
    void testExecuteRule_LegacyMapPayload() {
        Map<String, Object> payload = Map.of(
            "accountId", "00000000001",
            "amount", 50.0
        );
        var result = service.executeCbact04c0023(payload);

        assertNotNull(result);
        assertEquals("SUCCESS", result.status());
    }
}
