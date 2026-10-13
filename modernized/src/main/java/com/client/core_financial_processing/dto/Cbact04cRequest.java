package com.client.core_financial_processing.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Modernized Request DTO for legacy COBOL module: CBACT04C
 * Mainframe Source: app/cbl/CBACT04C.cbl
 */
@Schema(description = "Request payload for Cbact04cService domain operations")
public record Cbact04cRequest(
    @Schema(description = "Target Account Identifier (COBOL PIC X(11))", example = "00000000001")
    @NotBlank(message = "accountId must not be blank")
    @Size(max = 20, message = "accountId exceeds max length")
    String accountId,

    @Schema(description = "Transaction / Execution Amount (COBOL PIC S9(9)V99)", example = "100.00")
    @NotNull(message = "amount is required")
    @DecimalMin(value = "0.00", message = "amount must be positive or zero")
    BigDecimal amount,

    @Schema(description = "Transaction code or operation subtype", example = "DEBIT")
    String transactionType,

    @Schema(description = "Correlation or reference tracking number", example = "TXN-98214")
    String referenceNumber,

    @Schema(description = "Additional dynamic business parameters")
    Map<String, Object> parameters
) {
    public Cbact04cRequest {
        if (accountId == null || accountId.isBlank()) {
            accountId = "00000000001";
        }
        if (amount == null) {
            amount = BigDecimal.ZERO;
        }
        if (transactionType == null || transactionType.isBlank()) {
            transactionType = "STANDARD";
        }
        if (referenceNumber == null || referenceNumber.isBlank()) {
            referenceNumber = "REF-AUTO";
        }
        if (parameters == null) {
            parameters = Map.of();
        }
    }

    public static Cbact04cRequest defaultBaseline() {
        return new Cbact04cRequest("00000000001", BigDecimal.ZERO, "STANDARD", "REF-BASELINE", Map.of());
    }
}
