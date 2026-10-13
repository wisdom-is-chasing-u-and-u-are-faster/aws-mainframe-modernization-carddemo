package com.client.core_financial_processing.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

/**
 * Modernized Request DTO for legacy COBOL module: CBSTM03A
 * Mainframe Source: app/cbl/CBSTM03A.CBL
 */
@Schema(description = "Request payload for Cbstm03aService domain operations")
public record Cbstm03aRequest(
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

    @Schema(description = "Target 16-digit Card Number for CardDemo operations", example = "1111222233334444")
    String cardNumber,

    @Schema(description = "Current cyclical or account balance for balance/posting evaluation", example = "1000.00")
    BigDecimal currentBalance,

    @Schema(description = "Assigned credit limit", example = "1500.00")
    BigDecimal creditLimit,

    @Schema(description = "Monthly or disclosure interest rate percentage", example = "12.00")
    BigDecimal interestRate,

    @Schema(description = "Transaction effective date for expiration checks", example = "2026-04-15")
    LocalDate transactionDate,

    @Schema(description = "Account / card expiration date", example = "2027-12-31")
    LocalDate expirationDate,

    @Schema(description = "Input date string for calendar/CEEDAYS date conversion validation", example = "2026-04-15")
    String dateString,

    @Schema(description = "Input date format pattern", example = "YYYY-MM-DD")
    String dateFormat,

    @Schema(description = "Wait duration in centiseconds (1/100 second) for delay routines", example = "100")
    Integer waitCentiseconds,

    @Schema(description = "Additional dynamic business parameters")
    Map<String, Object> parameters
) {
    public Cbstm03aRequest {
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

    public Cbstm03aRequest(String accountId, BigDecimal amount, String transactionType, String referenceNumber, Map<String, Object> parameters) {
        this(accountId, amount, transactionType, referenceNumber, null, null, null, null, null, null, null, null, null, parameters);
    }

    public static Cbstm03aRequest defaultBaseline() {
        return new Cbstm03aRequest("00000000001", BigDecimal.ZERO, "STANDARD", "REF-BASELINE", Map.of());
    }
}
