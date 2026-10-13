package com.client.core_financial_processing.service;

import com.client.runtime.ProgramRegistry;
import com.client.runtime.ProgramService;
import com.client.core_financial_processing.dto.Cbact04cRequest;
import com.client.core_financial_processing.dto.Cbact04cResponse;
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
@DisplayName("Cbact04cService Business Domain Service Tests")
class Cbact04cServiceTest {

    @Mock
    private ProgramRegistry registry;

    @InjectMocks
    private Cbact04cService service;

    @BeforeEach
    void setUp() {
        // Setup initial test fixture state
    }

    @Test
    @DisplayName("Verify module implements ProgramService and has valid metadata")
    void testModuleMetadata() {
        assertTrue(service instanceof ProgramService);
        assertEquals("CBACT04C", service.programId());
        assertEquals("CBACT04C", service.getModuleId());
        assertNotNull(service.sourcePath());
        assertNotNull(service.getSourcePath());
        assertNotNull(service.rules());
        assertNotNull(service.newState());
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
        assertTrue(response.message().contains("successfully") || response.reasonCode() == 0);
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
        assertEquals("DECLINED", response.status());
        assertTrue(response.message().toLowerCase().contains("overlimit") || response.message().toLowerCase().contains("overdraft") || response.reasonCode() == 102);
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
    @Test
    @DisplayName("CardDemo CBACT04C Ground Truth: Monthly interest calculation using (balance * rate) / 1200")
    void testCardDemoMonthlyInterestFormula() {
        Cbact04cRequest req = new Cbact04cRequest(
            "00000000001",
            new BigDecimal("1000.00"),
            "INTEREST",
            "TXN-INT-01",
            null,
            new BigDecimal("1000.00"),
            null,
            new BigDecimal("12.00"),
            null,
            null,
            null,
            null,
            null,
            Map.of()
        );
        Cbact04cResponse resp = service.calculateMonthlyInterest(req);
        assertEquals("SUCCESS", resp.status());
        assertEquals(0, resp.reasonCode());
        assertEquals(new BigDecimal("10.00"), resp.interestAmount());
        assertEquals(new BigDecimal("1010.00"), resp.newBalance());
    }
}
