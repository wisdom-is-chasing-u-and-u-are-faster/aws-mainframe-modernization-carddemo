package com.client.core_financial_processing.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

/**
 * Modernized Response DTO for legacy COBOL module: CBACT04C
 * Mainframe Source: app/cbl/CBACT04C.cbl
 */
@Schema(description = "Response payload for Cbact04cService domain operations")
public record Cbact04cResponse(
    @Schema(description = "Executed Business Rule Identifier", example = "BR-CBACT04C-0001")
    String ruleId,

    @Schema(description = "Operation Status (SUCCESS, DECLINED, VALIDATION_ERROR, UNTRANSLATED)", example = "SUCCESS")
    String status,

    @Schema(description = "Human-readable execution summary or failure rationale", example = "Transaction processed successfully")
    String message,

    @Schema(description = "Mainframe posting reason or severity code (e.g., 0, 100, 101, 102, 103, 12)", example = "0")
    int reasonCode,

    @Schema(description = "Execution severity level (0=Normal, 4=Warning, 8=Error, 12=Severe)", example = "0")
    int severity,

    @Schema(description = "Resulting Account Balance", example = "1250.75")
    BigDecimal newBalance,

    @Schema(description = "Calculated monthly interest amount (for CBACT04C)", example = "12.50")
    BigDecimal interestAmount,

    @Schema(description = "Date validation status (for CSUTLDTC)", example = "Date is valid")
    String dateValidationResult,

    @Schema(description = "Transaction Reference Identifier", example = "TXN-73910")
    String transactionReference,

    @Schema(description = "Execution Timestamp")
    Instant timestamp,

    @Schema(description = "COBOL state snapshot after execution")
    Map<String, Object> state
) {
    public Cbact04cResponse(String ruleId, String status, String message, BigDecimal newBalance, String transactionReference) {
        this(ruleId, status, message, 0, 0, newBalance, null, null, transactionReference, Instant.now(), Map.of());
    }

    public Cbact04cResponse(String ruleId, String status, String message, BigDecimal newBalance, String transactionReference, Instant timestamp) {
        this(ruleId, status, message, 0, 0, newBalance, null, null, transactionReference, timestamp, Map.of());
    }

    public BigDecimal resultingBalance() { return newBalance; }
    public String referenceNumber() { return transactionReference; }

    public static Cbact04cResponse success(String ruleId, String message, BigDecimal balance, String ref) {
        return new Cbact04cResponse(ruleId, "SUCCESS", message, 0, 0, balance, null, null, ref, Instant.now(), Map.of());
    }

    public static Cbact04cResponse declined(String ruleId, String message, int reasonCode, BigDecimal balance, String ref) {
        return new Cbact04cResponse(ruleId, "DECLINED", message, reasonCode, 8, balance, null, null, ref, Instant.now(), Map.of());
    }

    public static Cbact04cResponse declined(String ruleId, String message, BigDecimal balance, String ref) {
        return new Cbact04cResponse(ruleId, "DECLINED", message, 102, 8, balance, null, null, ref, Instant.now(), Map.of());
    }

    public static Cbact04cResponse validationError(String ruleId, String message) {
        return new Cbact04cResponse(ruleId, "VALIDATION_ERROR", message, 100, 8, BigDecimal.ZERO, null, null, "ERR", Instant.now(), Map.of());
    }

    public static Cbact04cResponse untranslated(String ruleId, String reason) {
        return new Cbact04cResponse(ruleId, "UNTRANSLATED", reason, 999, 12, BigDecimal.ZERO, null, null, "UNTRANSLATED", Instant.now(), Map.of());
    }

    public static Cbact04cResponse interestResult(String ruleId, String message, int reasonCode, BigDecimal balance, BigDecimal interest, String ref) {
        return new Cbact04cResponse(ruleId, "SUCCESS", message, reasonCode, 0, balance, interest, null, ref, Instant.now(), Map.of());
    }

    public static Cbact04cResponse dateValidationResult(String ruleId, String message, int severity, String result) {
        return new Cbact04cResponse(ruleId, severity == 0 ? "SUCCESS" : "VALIDATION_ERROR", message, severity, severity, BigDecimal.ZERO, null, result, "DATE-VAL", Instant.now(), Map.of());
    }
}
