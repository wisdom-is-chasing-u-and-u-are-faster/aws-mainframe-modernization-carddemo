package com.client.core_financial_processing.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Modernized Response DTO for legacy COBOL module: CBSTM03A
 * Mainframe Source: app/cbl/CBSTM03A.CBL
 */
@Schema(description = "Response payload for Cbstm03aService domain operations")
public record Cbstm03aResponse(
    @Schema(description = "Executed Business Rule Identifier", example = "BR-CBSTM03A-0001")
    String ruleId,

    @Schema(description = "Operation Status (SUCCESS, DECLINED_INSUFFICIENT_FUNDS, VALIDATION_ERROR)", example = "SUCCESS")
    String status,

    @Schema(description = "Human-readable execution summary or failure rationale", example = "Transaction processed successfully")
    String message,

    @Schema(description = "Resulting Account Balance", example = "1250.75")
    BigDecimal newBalance,

    @Schema(description = "Transaction Reference Identifier", example = "TXN-73910")
    String transactionReference,

    @Schema(description = "Execution Timestamp")
    Instant timestamp
) {
    public Cbstm03aResponse(String ruleId, String status, String message, BigDecimal newBalance, String transactionReference) {
        this(ruleId, status, message, newBalance, transactionReference, Instant.now());
    }

    public static Cbstm03aResponse success(String ruleId, String message, BigDecimal balance, String ref) {
        return new Cbstm03aResponse(ruleId, "SUCCESS", message, balance, ref, Instant.now());
    }

    public static Cbstm03aResponse declined(String ruleId, String message, BigDecimal balance, String ref) {
        return new Cbstm03aResponse(ruleId, "DECLINED_INSUFFICIENT_FUNDS", message, balance, ref, Instant.now());
    }

    public static Cbstm03aResponse validationError(String ruleId, String message) {
        return new Cbstm03aResponse(ruleId, "VALIDATION_ERROR", message, BigDecimal.ZERO, "ERR", Instant.now());
    }
}
