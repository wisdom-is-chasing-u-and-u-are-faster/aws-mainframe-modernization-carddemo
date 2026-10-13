package com.client.core_financial_processing.service;

import com.client.runtime.ProgramRegistry;
import com.client.runtime.ProgramService;
import com.client.core_financial_processing.dto.Cbstm03bRequest;
import com.client.core_financial_processing.dto.Cbstm03bResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Cbstm03bService Business Domain Service Tests")
class Cbstm03bServiceTest {

    @Mock
    private ProgramRegistry registry;

    @InjectMocks
    private Cbstm03bService service;

    @BeforeEach
    void setUp() {
        // Setup initial test fixture state
    }

    @Test
    @DisplayName("Verify module implements ProgramService and has valid metadata")
    void testModuleMetadata() {
        assertTrue(service instanceof ProgramService);
        assertEquals("CBSTM03B", service.programId());
        assertEquals("CBSTM03B", service.getModuleId());
        assertNotNull(service.sourcePath());
        assertNotNull(service.getSourcePath());
        assertNotNull(service.rules());
        assertNotNull(service.newState());
    }

    @Test
    @DisplayName("Happy path: execute business rule with valid debit request")
    void testExecuteRule_Success() {
        Cbstm03bRequest request = new Cbstm03bRequest(
            "00000000001",
            new BigDecimal("150.00"),
            "DEBIT",
            "TXN-1001",
            Map.of()
        );
        Cbstm03bResponse response = service.executeCbstm03b0163(request);

        assertNotNull(response);
        assertEquals("SUCCESS", response.status());
        assertNotNull(response.newBalance());
        assertTrue(response.message().contains("successfully") || response.reasonCode() == 0);
    }

    @Test
    @DisplayName("Overdraft guard: reject transaction exceeding overdraft limits")
    void testExecuteRule_OverdraftDeclined() {
        Cbstm03bRequest request = new Cbstm03bRequest(
            "00000000001",
            new BigDecimal("999999.00"),
            "DEBIT",
            "TXN-OVERDRAFT",
            Map.of()
        );
        Cbstm03bResponse response = service.executeCbstm03b0163(request);

        assertNotNull(response);
        assertEquals("DECLINED", response.status());
        assertTrue(response.message().toLowerCase().contains("overlimit") || response.message().toLowerCase().contains("overdraft") || response.reasonCode() == 102);
    }

    @Test
    @DisplayName("Validation guard: reject negative transaction amount")
    void testExecuteRule_NegativeAmountValidation() {
        Cbstm03bRequest request = new Cbstm03bRequest(
            "00000000001",
            new BigDecimal("-50.00"),
            "DEBIT",
            "TXN-NEG",
            Map.of()
        );
        Cbstm03bResponse response = service.executeCbstm03b0163(request);

        assertNotNull(response);
        assertEquals("VALIDATION_ERROR", response.status());
    }

    @Test
    @DisplayName("Resilience: handle null request gracefully with baseline default")
    void testExecuteRule_NullRequestHandling() {
        Cbstm03bResponse response = service.executeCbstm03b0163((Cbstm03bRequest) null);

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
        var result = service.executeCbstm03b0163(payload);

        assertNotNull(result);
        assertEquals("SUCCESS", result.status());
    }
    @Test
    @DisplayName("CardDemo CBSTM03B: Monthly statement balance computation success")
    void testStatementCalculationSuccess() {
        Cbstm03bRequest req = Cbstm03bRequest.defaultBaseline();
        Cbstm03bResponse resp = service.generateStatement(req);
        assertEquals("SUCCESS", resp.status());
        assertEquals(0, resp.reasonCode());
        assertNotNull(resp.endingBalance());
    }

    @Test
    @DisplayName("CardDemo CBSTM03B: Invalid statement month rejected with code 30")
    void testInvalidStatementPeriodRejected() {
        Cbstm03bRequest req = new Cbstm03bRequest(
            "00000000001", BigDecimal.ZERO, "STANDARD", "REF-ERR",
            null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null,
            null, null, null, 13, 2026, BigDecimal.valueOf(1000.00), BigDecimal.valueOf(100.00), BigDecimal.valueOf(200.00),
            null, null, null, null, Map.of()
        );
        Cbstm03bResponse resp = service.generateStatement(req);
        assertEquals("DECLINED", resp.status());
        assertEquals(30, resp.reasonCode());
    }
}
