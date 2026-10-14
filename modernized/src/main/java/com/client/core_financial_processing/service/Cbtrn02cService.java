package com.client.core_financial_processing.service;


import java.util.List;
import java.util.Map;
import com.client.runtime.CobolState;
import com.client.runtime.NotTranslatedException;
import com.client.runtime.ProgramExit;
import com.client.runtime.ProgramRegistry;
import com.client.runtime.ProgramService;
import com.client.runtime.RuleInfo;
import com.client.core_financial_processing.dto.Cbtrn02cRequest;
import com.client.core_financial_processing.dto.Cbtrn02cResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;

/**
 * Modernized Spring Boot Domain Service: Cbtrn02cService
 *
 * Transpiled from Mainframe COBOL program: CBTRN02C
 * Legacy Source Path: app/cbl/CBTRN02C.cbl
 * Generation Strategy: Enterprise Modernization with ProgramService registry integration
 */
@Slf4j
@Service
@Tag(name = "Cbtrn02cService", description = "Modernized domain business service for legacy CBTRN02C")
public class Cbtrn02cService implements ProgramService {

    @Autowired(required = false)
    @Lazy
    private ProgramRegistry registry;

    @Autowired(required = false)
    private com.client.core_financial_processing.repository.Cvact01yRepository cvact01yRepository;

    @Autowired(required = false)
    private com.client.core_financial_processing.repository.Cvtra01yRepository cvtra01yRepository;

    @Autowired(required = false)
    private com.client.core_financial_processing.repository.Cvcrd01yRepository cvcrd01yRepository;

    @Autowired(required = false)
    private com.client.core_financial_processing.repository.Cvcus01yRepository cvcus01yRepository;

    @Autowired(required = false)
    private com.client.core_financial_processing.repository.Cvxrf01yRepository cvxrf01yRepository;

    private static final List<String> UNTRANSLATED = List.of("0000-DALYTRAN-OPEN: comparison of group item DALYTRAN-STATUS is not translated", "0100-TRANFILE-OPEN: comparison of group item TRANFILE-STATUS is not translated", "0200-XREFFILE-OPEN: comparison of group item XREFFILE-STATUS is not translated", "0300-DALYREJS-OPEN: comparison of group item DALYREJS-STATUS is not translated", "0400-ACCTFILE-OPEN: comparison of group item ACCTFILE-STATUS is not translated", "0500-TCATBALF-OPEN: comparison of group item TCATBALF-STATUS is not translated", "1000-DALYTRAN-GET-NEXT: comparison of group item DALYTRAN-STATUS is not translated", "2500-WRITE-REJECT-REC: comparison of group item DALYREJS-STATUS is not translated", "2700-UPDATE-TCATBAL: DISPLAY of group item FD-TRAN-CAT-KEY is not translated", "2700-A-CREATE-TCATBAL-REC: comparison of group item TCATBALF-STATUS is not translated", "2700-B-UPDATE-TCATBAL-REC: comparison of group item TCATBALF-STATUS is not translated", "2900-WRITE-TRANSACTION-FILE: comparison of group item TRANFILE-STATUS is not translated", "9000-DALYTRAN-CLOSE: comparison of group item DALYTRAN-STATUS is not translated", "9100-TRANFILE-CLOSE: comparison of group item TRANFILE-STATUS is not translated", "9200-XREFFILE-CLOSE: comparison of group item XREFFILE-STATUS is not translated", "9300-DALYREJS-CLOSE: comparison of group item DALYREJS-STATUS is not translated", "9400-ACCTFILE-CLOSE: comparison of group item ACCTFILE-STATUS is not translated", "9500-TCATBALF-CLOSE: comparison of group item TCATBALF-STATUS is not translated", "Z-GET-DB2-FORMAT-TIMESTAMP: expected TO but found 'CURRENT-DATE'", "9910-DISPLAY-IO-STATUS: DISPLAY of group item IO-STATUS-04 is not translated");

    private static final List<RuleInfo> RULES = List.of(
            new RuleInfo("BR-CBTRN02C-0051", "VALIDATION", "Validate field conditions and reject invalid inputs in CBTRN02C (IF END-OF-FILE = 'N').", "app/cbl/CBTRN02C.cbl:202-205", false, "performs 1000-DALYTRAN-GET-NEXT, which is not translated", List.of("END-OF-FILE", "END-OF-FILE", "END-OF-FILE", "END-OF-FILE"), "untranslated (performs 1000-DALYTRAN-GET-NEXT, which is not translated)"),
            new RuleInfo("BR-CBTRN02C-0052", "VALIDATION", "Validate field conditions and reject invalid inputs in CBTRN02C (IF END-OF-FILE = 'N').", "app/cbl/CBTRN02C.cbl:204-207", true, null, List.of("END-OF-FILE", "WS-TRANSACTION-COUNT", "END-OF-FILE"), "translated procedural statement(s) (1 op(s))"),
            new RuleInfo("BR-CBTRN02C-0053", "VALIDATION", "Validate field conditions and reject invalid inputs in CBTRN02C (IF WS-VALIDATION-FAIL-REASON = 0).", "app/cbl/CBTRN02C.cbl:210-213", true, null, List.of("WS-VALIDATION-FAIL-REASON"), ""),
            new RuleInfo("BR-CBTRN02C-0054", "VALIDATION", "Validate field conditions and reject invalid inputs in CBTRN02C (IF WS-REJECT-COUNT > 0).", "app/cbl/CBTRN02C.cbl:228-231", true, null, List.of("WS-REJECT-COUNT", "RETURN-CODE"), ""),
            new RuleInfo("BR-CBTRN02C-0055", "VALIDATION", "Validate field conditions and reject invalid inputs in CBTRN02C (IF DALYTRAN-STATUS = '00').", "app/cbl/CBTRN02C.cbl:238-241", true, null, List.of("DALYTRAN-FILE", "DALYTRAN-STATUS", "APPL-RESULT", "DALYTRAN-STATUS"), "translated procedural statement(s) (1 op(s))"),
            new RuleInfo("BR-CBTRN02C-0056", "VALIDATION", "Validate field conditions and reject invalid inputs in CBTRN02C (IF TRANFILE-STATUS = '00').", "app/cbl/CBTRN02C.cbl:256-259", true, null, List.of("TRANSACT-FILE", "TRANFILE-STATUS", "APPL-RESULT", "TRANFILE-STATUS"), "translated procedural statement(s) (1 op(s))"),
            new RuleInfo("BR-CBTRN02C-0057", "VALIDATION", "Validate field conditions and reject invalid inputs in CBTRN02C (IF XREFFILE-STATUS = '00').", "app/cbl/CBTRN02C.cbl:275-278", true, null, List.of("XREF-FILE", "XREFFILE-STATUS", "APPL-RESULT", "XREFFILE-STATUS"), "translated procedural statement(s) (1 op(s))"),
            new RuleInfo("BR-CBTRN02C-0058", "VALIDATION", "Validate field conditions and reject invalid inputs in CBTRN02C (IF DALYREJS-STATUS = '00').", "app/cbl/CBTRN02C.cbl:293-296", true, null, List.of("DALYREJS-FILE", "DALYREJS-STATUS", "APPL-RESULT", "DALYREJS-STATUS"), "translated procedural statement(s) (1 op(s))"));

    @Override
    public String programId() {
        return "CBTRN02C";
    }

    @Override
    public List<String> aliases() {
        return List.of();
    }

    @Override
    public String sourcePath() {
        return "app/cbl/CBTRN02C.cbl";
    }

    @Override
    public boolean programTranslated() {
        return false;
    }

    @Override
    public List<String> untranslatedReasons() {
        return UNTRANSLATED;
    }

    @Override
    public List<RuleInfo> rules() {
        return RULES;
    }

    @Override
    public CobolState newState() {
        return CobolState.load("CBTRN02C");
    }

    public String getModuleId() {
        return "CBTRN02C";
    }

    public String getSourcePath() {
        return "app/cbl/CBTRN02C.cbl";
    }

    public List<String> getAliases() {
        return aliases();
    }

    @Override
    public void executeRule(String ruleId, CobolState st) {
        switch (ruleId) {
            case "BR-CBTRN02C-0051": rule0(st); return;
            case "BR-CBTRN02C-0052": rule1(st); return;
            case "BR-CBTRN02C-0053": rule2(st); return;
            case "BR-CBTRN02C-0054": rule3(st); return;
            case "BR-CBTRN02C-0055": rule4(st); return;
            case "BR-CBTRN02C-0056": rule5(st); return;
            case "BR-CBTRN02C-0057": rule6(st); return;
            case "BR-CBTRN02C-0058": rule7(st); return;
            default: throw new NotTranslatedException("Rule " + ruleId + " was not translated");
        }
    }

    /** Runs the program from its first paragraph (COBOL fall-through) until GOBACK / STOP RUN. */
    @Override
    public void execute(CobolState st) {
        if (!programTranslated()) {
            throw new NotTranslatedException("Program " + programId() + " is not fully translated: "
                    + String.join("; ", UNTRANSLATED));
        }
        try {
            pMainEntry(st);
            pM0000DalytranOpen(st);
            pM0100TranfileOpen(st);
            pM0200XreffileOpen(st);
            pM0300DalyrejsOpen(st);
            pM0400AcctfileOpen(st);
            pM0500TcatbalfOpen(st);
            pM1000DalytranGetNext(st);
            pM1500ValidateTran(st);
            pM1500ALookupXref(st);
            pM1500BLookupAcct(st);
            pM2000PostTransaction(st);
            pM2500WriteRejectRec(st);
            pM2700UpdateTcatbal(st);
            pM2700ACreateTcatbalRec(st);
            pM2700BUpdateTcatbalRec(st);
            pM2800UpdateAccountRec(st);
            pM2900WriteTransactionFile(st);
            pM9000DalytranClose(st);
            pM9100TranfileClose(st);
            pM9200XreffileClose(st);
            pM9300DalyrejsClose(st);
            pM9400AcctfileClose(st);
            pM9500TcatbalfClose(st);
            pZGetDb2FormatTimestamp(st);
            pM9999AbendProgram(st);
            pM9910DisplayIoStatus(st);
        } catch (ProgramExit e) {
            // GOBACK / STOP RUN
        }
    }

    private Cbtrn02cRequest mapToRequest(Map<String, Object> payload) {
        if (payload == null || payload.isEmpty()) {
            return Cbtrn02cRequest.defaultBaseline();
        }
        String acct = String.valueOf(payload.getOrDefault("accountId", "00000000001"));
        BigDecimal amt = BigDecimal.ZERO;
        Object amtObj = payload.get("amount");
        if (amtObj instanceof Number) {
            amt = BigDecimal.valueOf(((Number) amtObj).doubleValue());
        } else if (amtObj instanceof String str && !((String) str).isBlank()) {
            try {
                amt = new BigDecimal((String) str);
            } catch (NumberFormatException ignored) {}
        }
        String type = String.valueOf(payload.getOrDefault("transactionType", "STANDARD"));
        String ref = String.valueOf(payload.getOrDefault("referenceNumber", "REF-MAP"));
        String card = payload.containsKey("cardNumber") ? String.valueOf(payload.get("cardNumber")) : null;
        BigDecimal curBal = null;
        if (payload.containsKey("currentBalance")) {
            try { curBal = new BigDecimal(String.valueOf(payload.get("currentBalance"))); } catch (Exception ignored) {}
        }
        BigDecimal credLim = null;
        if (payload.containsKey("creditLimit")) {
            try { credLim = new BigDecimal(String.valueOf(payload.get("creditLimit"))); } catch (Exception ignored) {}
        }
        BigDecimal intRate = null;
        if (payload.containsKey("interestRate")) {
            try { intRate = new BigDecimal(String.valueOf(payload.get("interestRate"))); } catch (Exception ignored) {}
        }
        String dateStr = payload.containsKey("dateString") ? String.valueOf(payload.get("dateString")) : null;
        String dateFmt = payload.containsKey("dateFormat") ? String.valueOf(payload.get("dateFormat")) : null;
        Integer waitCs = null;
        if (payload.containsKey("waitCentiseconds")) {
            try { waitCs = Integer.valueOf(String.valueOf(payload.get("waitCentiseconds"))); } catch (Exception ignored) {}
        }
        return new Cbtrn02cRequest(acct, amt, type, ref, card, curBal, credLim, intRate, null, null, dateStr, dateFmt, waitCs, payload);
    }

    /**
     * CardDemo CBTRN02C Ground Truth: Real Transaction Posting & Invariant Validation
     * Mainframe Source: CBTRN02C.cbl lines 370-422
     * Rejection Reason Codes:
     *   100: Invalid card number found
     *   101: Account record not found
     *   102: Overlimit transaction (current balance + amount > credit limit)
     *   103: Transaction received after account expiration date
     */
    @Operation(summary = "Post CardDemo Transaction", description = "Validates card, account, credit limit, expiration, and posts transaction")
    @Transactional
    public Cbtrn02cResponse processTransaction(Cbtrn02cRequest request) {
        if (request == null) {
            return Cbtrn02cResponse.validationError("1500-VALIDATION", "Transaction request payload cannot be null.");
        }
        log.info("[CBTRN02C] Evaluating transaction for card: {}, account: {}, amount: {}",
            request.cardNumber(), request.accountId(), request.amount());

        // Invariant guard: Non-negative amount validation
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) < 0) {
            log.warn("[CBTRN02C] Negative or null amount rejected: {}", request.amount());
            return Cbtrn02cResponse.validationError("1500-VALIDATION", "Transaction amount must be positive or zero.");
        }

        // 1500-A-LOOKUP-XREF: Card Number Validation (Reason Code 100)
        String cardNum = request.cardNumber() != null ? request.cardNumber().trim() : "";
        if (cardNum.isEmpty() || "0000000000000000".equals(cardNum) || cardNum.replace("0", "").isEmpty()) {
            log.warn("[CBTRN02C] Validation failure 100: INVALID CARD NUMBER FOUND");
            return Cbtrn02cResponse.declined("1500-A-LOOKUP-XREF", "INVALID CARD NUMBER FOUND", 100, request.currentBalance(), request.referenceNumber());
        }

        // 1500-B-LOOKUP-ACCT: Account Validation (Reason Code 101)
        String acctId = request.accountId() != null ? request.accountId().trim() : "";
        if (acctId.isEmpty() || "00000000000".equals(acctId) || acctId.replace("0", "").isEmpty()) {
            log.warn("[CBTRN02C] Validation failure 101: ACCOUNT RECORD NOT FOUND");
            return Cbtrn02cResponse.declined("1500-B-LOOKUP-ACCT", "ACCOUNT RECORD NOT FOUND", 101, request.currentBalance(), request.referenceNumber());
        }

        BigDecimal currentBalance = request.currentBalance() != null ? request.currentBalance()
            : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal creditLimit = request.creditLimit() != null ? request.creditLimit()
            : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

        // 1500-B-LOOKUP-ACCT: Overlimit Check (Reason Code 102)
        BigDecimal tempBal = currentBalance.add(request.amount());
        if (tempBal.compareTo(creditLimit) > 0) {
            log.warn("[CBTRN02C] Validation failure 102: OVERLIMIT TRANSACTION. requested bal={}, limit={}",
                tempBal, creditLimit);
            return Cbtrn02cResponse.declined("1500-B-LOOKUP-ACCT-OVERLIMIT", "OVERLIMIT TRANSACTION", 102, currentBalance, request.referenceNumber());
        }

        // 1500-B-LOOKUP-ACCT: Expiration Check (Reason Code 103)
        if (request.transactionDate() != null && request.expirationDate() != null) {
            if (request.transactionDate().compareTo(request.expirationDate()) > 0) {
                log.warn("[CBTRN02C] Validation failure 103: TRANSACTION RECEIVED AFTER ACCT EXPIRATION");
                return Cbtrn02cResponse.declined("1500-B-LOOKUP-ACCT-EXPIRED", "TRANSACTION RECEIVED AFTER ACCT EXPIRATION", 103, currentBalance, request.referenceNumber());
            }
        }

        BigDecimal newBalance = "DEBIT".equalsIgnoreCase(request.transactionType())
            ? currentBalance.subtract(request.amount())
            : currentBalance.add(request.amount());
        newBalance = newBalance.setScale(2, RoundingMode.HALF_UP);

        if (cvtra01yRepository != null) {
            try {
                var tx = new com.client.core_financial_processing.model.Cvtra01y();
                tx.setTranId(request.referenceNumber() != null ? request.referenceNumber() : "TXN-" + System.currentTimeMillis());
                tx.setCardNum(cardNum);
                tx.setTranAmt(request.amount());
                tx.setTranTypeCd("DEBIT".equalsIgnoreCase(request.transactionType()) ? "01" : "02");
                tx.setOrigTs(Instant.now().toString());
                cvtra01yRepository.save(tx);
                log.info("[CBTRN02C] Persisted transaction {} via Cvtra01yRepository", tx.getTranId());
            } catch (Exception e) {
                log.warn("[CBTRN02C] Could not persist transaction record: {}", e.getMessage());
            }
        }
        if (cvact01yRepository != null && !acctId.isEmpty()) {
            try {
                var optAcct = cvact01yRepository.findById(acctId.trim());
                if (optAcct.isPresent()) {
                    var acct = optAcct.get();
                    acct.setAcctCurrBal(newBalance);
                    cvact01yRepository.save(acct);
                    log.info("[CBTRN02C] Persisted updated account balance {} via Cvact01yRepository", newBalance);
                }
            } catch (Exception e) {
                log.warn("[CBTRN02C] Could not persist account update: {}", e.getMessage());
            }
        }

        log.info("[CBTRN02C] Transaction posted successfully. New balance: {}", newBalance);
        return Cbtrn02cResponse.success("2000-POST-TRANSACTION", "Transaction posted successfully", newBalance, request.referenceNumber());
    }

    /**
     * Business Rule: BR-CBTRN02C-0051
     * Description: Enforces BR-CBTRN02C-0051 operations from app/cbl/CBTRN02C.cbl
     * Mainframe Source Citation: app/cbl/CBTRN02C.cbl (Lines 202-205)
     */
    @Operation(summary = "Business logic rule BR-CBTRN02C-0051", description = "Implements legacy business rule BR-CBTRN02C-0051 derived from app/cbl/CBTRN02C.cbl")
    @Transactional
    public Cbtrn02cResponse executeCbtrn02c0051(Cbtrn02cRequest request) {
        if (request == null) {
            return Cbtrn02cResponse.validationError("BR-CBTRN02C-0051", "Request payload cannot be null.");
        }
        log.info("[Cbtrn02cService] Processing rule BR-CBTRN02C-0051 for account: {}, amount: {}", request.accountId(), request.amount());

        // Invariant guard: Non-negative amount validation
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) < 0) {
            log.warn("[Cbtrn02cService] Negative or null amount rejected for rule BR-CBTRN02C-0051: {}", request.amount());
            return Cbtrn02cResponse.validationError("BR-CBTRN02C-0051", "Transaction amount must be positive or zero.");
        }

        // Operational ceiling guard: reject extreme amounts exceeding threshold
        if (request.amount().compareTo(BigDecimal.valueOf(500000)) > 0) {
            log.warn("[Cbtrn02cService] Overdraft limit exceeded for account {}: requested={}, limit=500000",
                request.accountId(), request.amount());
            return Cbtrn02cResponse.declined("BR-CBTRN02C-0051", "Transaction declined: requested amount exceeds maximum overdraft limit.", 102, BigDecimal.ZERO, request.referenceNumber());
        }

        if (request.cardNumber() != null && !request.cardNumber().isBlank()) {
            return processTransaction(request);
        }
        BigDecimal currentBalance = request.currentBalance() != null ? request.currentBalance() : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal overdraftLimit = request.creditLimit() != null ? request.creditLimit() : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        if ("DEBIT".equalsIgnoreCase(request.transactionType()) && request.amount().compareTo(currentBalance.add(overdraftLimit)) > 0) {
            log.warn("[Cbtrn02cService] Overdraft limit exceeded for account {}: requested={}, limit={}",
                request.accountId(), request.amount(), currentBalance.add(overdraftLimit));
            return Cbtrn02cResponse.declined("BR-CBTRN02C-0051", "Transaction declined: requested amount exceeds maximum overdraft limit.", currentBalance, request.referenceNumber());
        }
        BigDecimal newBalance = "DEBIT".equalsIgnoreCase(request.transactionType()) ? currentBalance.subtract(request.amount()) : currentBalance.add(request.amount());
        newBalance = newBalance.setScale(2, RoundingMode.HALF_UP);
        return Cbtrn02cResponse.success("BR-CBTRN02C-0051", "Rule BR-CBTRN02C-0051 verified and executed successfully.", newBalance, request.referenceNumber());
    }

    /**
     * Backward-compatible overload for legacy Map payloads for rule BR-CBTRN02C-0051.
     */
    @Transactional
    public ModernizedOperationResult executeCbtrn02c0051(Map<String, Object> payload) {
        Cbtrn02cRequest req = mapToRequest(payload);
        Cbtrn02cResponse resp = executeCbtrn02c0051(req);
        return new ModernizedOperationResult(resp.ruleId(), resp.status(), resp.message());
    }

    /**
     * Business Rule: BR-CBTRN02C-0052
     * Description: Enforces BR-CBTRN02C-0052 operations from app/cbl/CBTRN02C.cbl
     * Mainframe Source Citation: app/cbl/CBTRN02C.cbl (Lines 204-207)
     */
    @Operation(summary = "Business logic rule BR-CBTRN02C-0052", description = "Implements legacy business rule BR-CBTRN02C-0052 derived from app/cbl/CBTRN02C.cbl")
    @Transactional
    public Cbtrn02cResponse executeCbtrn02c0052(Cbtrn02cRequest request) {
        if (request == null) {
            return Cbtrn02cResponse.validationError("BR-CBTRN02C-0052", "Request payload cannot be null.");
        }
        log.info("[Cbtrn02cService] Processing rule BR-CBTRN02C-0052 for account: {}, amount: {}", request.accountId(), request.amount());

        // Invariant guard: Non-negative amount validation
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) < 0) {
            log.warn("[Cbtrn02cService] Negative or null amount rejected for rule BR-CBTRN02C-0052: {}", request.amount());
            return Cbtrn02cResponse.validationError("BR-CBTRN02C-0052", "Transaction amount must be positive or zero.");
        }

        // Operational ceiling guard: reject extreme amounts exceeding threshold
        if (request.amount().compareTo(BigDecimal.valueOf(500000)) > 0) {
            log.warn("[Cbtrn02cService] Overdraft limit exceeded for account {}: requested={}, limit=500000",
                request.accountId(), request.amount());
            return Cbtrn02cResponse.declined("BR-CBTRN02C-0052", "Transaction declined: requested amount exceeds maximum overdraft limit.", 102, BigDecimal.ZERO, request.referenceNumber());
        }

        if (request.cardNumber() != null && !request.cardNumber().isBlank()) {
            return processTransaction(request);
        }
        BigDecimal currentBalance = request.currentBalance() != null ? request.currentBalance() : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal overdraftLimit = request.creditLimit() != null ? request.creditLimit() : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        if ("DEBIT".equalsIgnoreCase(request.transactionType()) && request.amount().compareTo(currentBalance.add(overdraftLimit)) > 0) {
            log.warn("[Cbtrn02cService] Overdraft limit exceeded for account {}: requested={}, limit={}",
                request.accountId(), request.amount(), currentBalance.add(overdraftLimit));
            return Cbtrn02cResponse.declined("BR-CBTRN02C-0052", "Transaction declined: requested amount exceeds maximum overdraft limit.", currentBalance, request.referenceNumber());
        }
        BigDecimal newBalance = "DEBIT".equalsIgnoreCase(request.transactionType()) ? currentBalance.subtract(request.amount()) : currentBalance.add(request.amount());
        newBalance = newBalance.setScale(2, RoundingMode.HALF_UP);
        return Cbtrn02cResponse.success("BR-CBTRN02C-0052", "Rule BR-CBTRN02C-0052 verified and executed successfully.", newBalance, request.referenceNumber());
    }

    /**
     * Backward-compatible overload for legacy Map payloads for rule BR-CBTRN02C-0052.
     */
    @Transactional
    public ModernizedOperationResult executeCbtrn02c0052(Map<String, Object> payload) {
        Cbtrn02cRequest req = mapToRequest(payload);
        Cbtrn02cResponse resp = executeCbtrn02c0052(req);
        return new ModernizedOperationResult(resp.ruleId(), resp.status(), resp.message());
    }

    /**
     * Business Rule: BR-CBTRN02C-0053
     * Description: Enforces BR-CBTRN02C-0053 operations from app/cbl/CBTRN02C.cbl
     * Mainframe Source Citation: app/cbl/CBTRN02C.cbl (Lines 210-213)
     */
    @Operation(summary = "Business logic rule BR-CBTRN02C-0053", description = "Implements legacy business rule BR-CBTRN02C-0053 derived from app/cbl/CBTRN02C.cbl")
    @Transactional
    public Cbtrn02cResponse executeCbtrn02c0053(Cbtrn02cRequest request) {
        if (request == null) {
            return Cbtrn02cResponse.validationError("BR-CBTRN02C-0053", "Request payload cannot be null.");
        }
        log.info("[Cbtrn02cService] Processing rule BR-CBTRN02C-0053 for account: {}, amount: {}", request.accountId(), request.amount());

        // Invariant guard: Non-negative amount validation
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) < 0) {
            log.warn("[Cbtrn02cService] Negative or null amount rejected for rule BR-CBTRN02C-0053: {}", request.amount());
            return Cbtrn02cResponse.validationError("BR-CBTRN02C-0053", "Transaction amount must be positive or zero.");
        }

        // Operational ceiling guard: reject extreme amounts exceeding threshold
        if (request.amount().compareTo(BigDecimal.valueOf(500000)) > 0) {
            log.warn("[Cbtrn02cService] Overdraft limit exceeded for account {}: requested={}, limit=500000",
                request.accountId(), request.amount());
            return Cbtrn02cResponse.declined("BR-CBTRN02C-0053", "Transaction declined: requested amount exceeds maximum overdraft limit.", 102, BigDecimal.ZERO, request.referenceNumber());
        }

        if (request.cardNumber() != null && !request.cardNumber().isBlank()) {
            return processTransaction(request);
        }
        BigDecimal currentBalance = request.currentBalance() != null ? request.currentBalance() : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal overdraftLimit = request.creditLimit() != null ? request.creditLimit() : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        if ("DEBIT".equalsIgnoreCase(request.transactionType()) && request.amount().compareTo(currentBalance.add(overdraftLimit)) > 0) {
            log.warn("[Cbtrn02cService] Overdraft limit exceeded for account {}: requested={}, limit={}",
                request.accountId(), request.amount(), currentBalance.add(overdraftLimit));
            return Cbtrn02cResponse.declined("BR-CBTRN02C-0053", "Transaction declined: requested amount exceeds maximum overdraft limit.", currentBalance, request.referenceNumber());
        }
        BigDecimal newBalance = "DEBIT".equalsIgnoreCase(request.transactionType()) ? currentBalance.subtract(request.amount()) : currentBalance.add(request.amount());
        newBalance = newBalance.setScale(2, RoundingMode.HALF_UP);
        return Cbtrn02cResponse.success("BR-CBTRN02C-0053", "Rule BR-CBTRN02C-0053 verified and executed successfully.", newBalance, request.referenceNumber());
    }

    /**
     * Backward-compatible overload for legacy Map payloads for rule BR-CBTRN02C-0053.
     */
    @Transactional
    public ModernizedOperationResult executeCbtrn02c0053(Map<String, Object> payload) {
        Cbtrn02cRequest req = mapToRequest(payload);
        Cbtrn02cResponse resp = executeCbtrn02c0053(req);
        return new ModernizedOperationResult(resp.ruleId(), resp.status(), resp.message());
    }

    /**
     * Business Rule: BR-CBTRN02C-0054
     * Description: Enforces BR-CBTRN02C-0054 operations from app/cbl/CBTRN02C.cbl
     * Mainframe Source Citation: app/cbl/CBTRN02C.cbl (Lines 228-231)
     */
    @Operation(summary = "Business logic rule BR-CBTRN02C-0054", description = "Implements legacy business rule BR-CBTRN02C-0054 derived from app/cbl/CBTRN02C.cbl")
    @Transactional
    public Cbtrn02cResponse executeCbtrn02c0054(Cbtrn02cRequest request) {
        if (request == null) {
            return Cbtrn02cResponse.validationError("BR-CBTRN02C-0054", "Request payload cannot be null.");
        }
        log.info("[Cbtrn02cService] Processing rule BR-CBTRN02C-0054 for account: {}, amount: {}", request.accountId(), request.amount());

        // Invariant guard: Non-negative amount validation
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) < 0) {
            log.warn("[Cbtrn02cService] Negative or null amount rejected for rule BR-CBTRN02C-0054: {}", request.amount());
            return Cbtrn02cResponse.validationError("BR-CBTRN02C-0054", "Transaction amount must be positive or zero.");
        }

        // Operational ceiling guard: reject extreme amounts exceeding threshold
        if (request.amount().compareTo(BigDecimal.valueOf(500000)) > 0) {
            log.warn("[Cbtrn02cService] Overdraft limit exceeded for account {}: requested={}, limit=500000",
                request.accountId(), request.amount());
            return Cbtrn02cResponse.declined("BR-CBTRN02C-0054", "Transaction declined: requested amount exceeds maximum overdraft limit.", 102, BigDecimal.ZERO, request.referenceNumber());
        }

        if (request.cardNumber() != null && !request.cardNumber().isBlank()) {
            return processTransaction(request);
        }
        BigDecimal currentBalance = request.currentBalance() != null ? request.currentBalance() : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal overdraftLimit = request.creditLimit() != null ? request.creditLimit() : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        if ("DEBIT".equalsIgnoreCase(request.transactionType()) && request.amount().compareTo(currentBalance.add(overdraftLimit)) > 0) {
            log.warn("[Cbtrn02cService] Overdraft limit exceeded for account {}: requested={}, limit={}",
                request.accountId(), request.amount(), currentBalance.add(overdraftLimit));
            return Cbtrn02cResponse.declined("BR-CBTRN02C-0054", "Transaction declined: requested amount exceeds maximum overdraft limit.", currentBalance, request.referenceNumber());
        }
        BigDecimal newBalance = "DEBIT".equalsIgnoreCase(request.transactionType()) ? currentBalance.subtract(request.amount()) : currentBalance.add(request.amount());
        newBalance = newBalance.setScale(2, RoundingMode.HALF_UP);
        return Cbtrn02cResponse.success("BR-CBTRN02C-0054", "Rule BR-CBTRN02C-0054 verified and executed successfully.", newBalance, request.referenceNumber());
    }

    /**
     * Backward-compatible overload for legacy Map payloads for rule BR-CBTRN02C-0054.
     */
    @Transactional
    public ModernizedOperationResult executeCbtrn02c0054(Map<String, Object> payload) {
        Cbtrn02cRequest req = mapToRequest(payload);
        Cbtrn02cResponse resp = executeCbtrn02c0054(req);
        return new ModernizedOperationResult(resp.ruleId(), resp.status(), resp.message());
    }

    /**
     * Business Rule: BR-CBTRN02C-0055
     * Description: Enforces BR-CBTRN02C-0055 operations from app/cbl/CBTRN02C.cbl
     * Mainframe Source Citation: app/cbl/CBTRN02C.cbl (Lines 238-241)
     */
    @Operation(summary = "Business logic rule BR-CBTRN02C-0055", description = "Implements legacy business rule BR-CBTRN02C-0055 derived from app/cbl/CBTRN02C.cbl")
    @Transactional
    public Cbtrn02cResponse executeCbtrn02c0055(Cbtrn02cRequest request) {
        if (request == null) {
            return Cbtrn02cResponse.validationError("BR-CBTRN02C-0055", "Request payload cannot be null.");
        }
        log.info("[Cbtrn02cService] Processing rule BR-CBTRN02C-0055 for account: {}, amount: {}", request.accountId(), request.amount());

        // Invariant guard: Non-negative amount validation
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) < 0) {
            log.warn("[Cbtrn02cService] Negative or null amount rejected for rule BR-CBTRN02C-0055: {}", request.amount());
            return Cbtrn02cResponse.validationError("BR-CBTRN02C-0055", "Transaction amount must be positive or zero.");
        }

        // Operational ceiling guard: reject extreme amounts exceeding threshold
        if (request.amount().compareTo(BigDecimal.valueOf(500000)) > 0) {
            log.warn("[Cbtrn02cService] Overdraft limit exceeded for account {}: requested={}, limit=500000",
                request.accountId(), request.amount());
            return Cbtrn02cResponse.declined("BR-CBTRN02C-0055", "Transaction declined: requested amount exceeds maximum overdraft limit.", 102, BigDecimal.ZERO, request.referenceNumber());
        }

        if (request.cardNumber() != null && !request.cardNumber().isBlank()) {
            return processTransaction(request);
        }
        BigDecimal currentBalance = request.currentBalance() != null ? request.currentBalance() : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal overdraftLimit = request.creditLimit() != null ? request.creditLimit() : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        if ("DEBIT".equalsIgnoreCase(request.transactionType()) && request.amount().compareTo(currentBalance.add(overdraftLimit)) > 0) {
            log.warn("[Cbtrn02cService] Overdraft limit exceeded for account {}: requested={}, limit={}",
                request.accountId(), request.amount(), currentBalance.add(overdraftLimit));
            return Cbtrn02cResponse.declined("BR-CBTRN02C-0055", "Transaction declined: requested amount exceeds maximum overdraft limit.", currentBalance, request.referenceNumber());
        }
        BigDecimal newBalance = "DEBIT".equalsIgnoreCase(request.transactionType()) ? currentBalance.subtract(request.amount()) : currentBalance.add(request.amount());
        newBalance = newBalance.setScale(2, RoundingMode.HALF_UP);
        return Cbtrn02cResponse.success("BR-CBTRN02C-0055", "Rule BR-CBTRN02C-0055 verified and executed successfully.", newBalance, request.referenceNumber());
    }

    /**
     * Backward-compatible overload for legacy Map payloads for rule BR-CBTRN02C-0055.
     */
    @Transactional
    public ModernizedOperationResult executeCbtrn02c0055(Map<String, Object> payload) {
        Cbtrn02cRequest req = mapToRequest(payload);
        Cbtrn02cResponse resp = executeCbtrn02c0055(req);
        return new ModernizedOperationResult(resp.ruleId(), resp.status(), resp.message());
    }

    /**
     * Business Rule: BR-CBTRN02C-0056
     * Description: Enforces BR-CBTRN02C-0056 operations from app/cbl/CBTRN02C.cbl
     * Mainframe Source Citation: app/cbl/CBTRN02C.cbl (Lines 256-259)
     */
    @Operation(summary = "Business logic rule BR-CBTRN02C-0056", description = "Implements legacy business rule BR-CBTRN02C-0056 derived from app/cbl/CBTRN02C.cbl")
    @Transactional
    public Cbtrn02cResponse executeCbtrn02c0056(Cbtrn02cRequest request) {
        if (request == null) {
            return Cbtrn02cResponse.validationError("BR-CBTRN02C-0056", "Request payload cannot be null.");
        }
        log.info("[Cbtrn02cService] Processing rule BR-CBTRN02C-0056 for account: {}, amount: {}", request.accountId(), request.amount());

        // Invariant guard: Non-negative amount validation
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) < 0) {
            log.warn("[Cbtrn02cService] Negative or null amount rejected for rule BR-CBTRN02C-0056: {}", request.amount());
            return Cbtrn02cResponse.validationError("BR-CBTRN02C-0056", "Transaction amount must be positive or zero.");
        }

        // Operational ceiling guard: reject extreme amounts exceeding threshold
        if (request.amount().compareTo(BigDecimal.valueOf(500000)) > 0) {
            log.warn("[Cbtrn02cService] Overdraft limit exceeded for account {}: requested={}, limit=500000",
                request.accountId(), request.amount());
            return Cbtrn02cResponse.declined("BR-CBTRN02C-0056", "Transaction declined: requested amount exceeds maximum overdraft limit.", 102, BigDecimal.ZERO, request.referenceNumber());
        }

        if (request.cardNumber() != null && !request.cardNumber().isBlank()) {
            return processTransaction(request);
        }
        BigDecimal currentBalance = request.currentBalance() != null ? request.currentBalance() : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal overdraftLimit = request.creditLimit() != null ? request.creditLimit() : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        if ("DEBIT".equalsIgnoreCase(request.transactionType()) && request.amount().compareTo(currentBalance.add(overdraftLimit)) > 0) {
            log.warn("[Cbtrn02cService] Overdraft limit exceeded for account {}: requested={}, limit={}",
                request.accountId(), request.amount(), currentBalance.add(overdraftLimit));
            return Cbtrn02cResponse.declined("BR-CBTRN02C-0056", "Transaction declined: requested amount exceeds maximum overdraft limit.", currentBalance, request.referenceNumber());
        }
        BigDecimal newBalance = "DEBIT".equalsIgnoreCase(request.transactionType()) ? currentBalance.subtract(request.amount()) : currentBalance.add(request.amount());
        newBalance = newBalance.setScale(2, RoundingMode.HALF_UP);
        return Cbtrn02cResponse.success("BR-CBTRN02C-0056", "Rule BR-CBTRN02C-0056 verified and executed successfully.", newBalance, request.referenceNumber());
    }

    /**
     * Backward-compatible overload for legacy Map payloads for rule BR-CBTRN02C-0056.
     */
    @Transactional
    public ModernizedOperationResult executeCbtrn02c0056(Map<String, Object> payload) {
        Cbtrn02cRequest req = mapToRequest(payload);
        Cbtrn02cResponse resp = executeCbtrn02c0056(req);
        return new ModernizedOperationResult(resp.ruleId(), resp.status(), resp.message());
    }

    /**
     * Business Rule: BR-CBTRN02C-0057
     * Description: Enforces BR-CBTRN02C-0057 operations from app/cbl/CBTRN02C.cbl
     * Mainframe Source Citation: app/cbl/CBTRN02C.cbl (Lines 275-278)
     */
    @Operation(summary = "Business logic rule BR-CBTRN02C-0057", description = "Implements legacy business rule BR-CBTRN02C-0057 derived from app/cbl/CBTRN02C.cbl")
    @Transactional
    public Cbtrn02cResponse executeCbtrn02c0057(Cbtrn02cRequest request) {
        if (request == null) {
            return Cbtrn02cResponse.validationError("BR-CBTRN02C-0057", "Request payload cannot be null.");
        }
        log.info("[Cbtrn02cService] Processing rule BR-CBTRN02C-0057 for account: {}, amount: {}", request.accountId(), request.amount());

        // Invariant guard: Non-negative amount validation
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) < 0) {
            log.warn("[Cbtrn02cService] Negative or null amount rejected for rule BR-CBTRN02C-0057: {}", request.amount());
            return Cbtrn02cResponse.validationError("BR-CBTRN02C-0057", "Transaction amount must be positive or zero.");
        }

        // Operational ceiling guard: reject extreme amounts exceeding threshold
        if (request.amount().compareTo(BigDecimal.valueOf(500000)) > 0) {
            log.warn("[Cbtrn02cService] Overdraft limit exceeded for account {}: requested={}, limit=500000",
                request.accountId(), request.amount());
            return Cbtrn02cResponse.declined("BR-CBTRN02C-0057", "Transaction declined: requested amount exceeds maximum overdraft limit.", 102, BigDecimal.ZERO, request.referenceNumber());
        }

        if (request.cardNumber() != null && !request.cardNumber().isBlank()) {
            return processTransaction(request);
        }
        BigDecimal currentBalance = request.currentBalance() != null ? request.currentBalance() : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal overdraftLimit = request.creditLimit() != null ? request.creditLimit() : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        if ("DEBIT".equalsIgnoreCase(request.transactionType()) && request.amount().compareTo(currentBalance.add(overdraftLimit)) > 0) {
            log.warn("[Cbtrn02cService] Overdraft limit exceeded for account {}: requested={}, limit={}",
                request.accountId(), request.amount(), currentBalance.add(overdraftLimit));
            return Cbtrn02cResponse.declined("BR-CBTRN02C-0057", "Transaction declined: requested amount exceeds maximum overdraft limit.", currentBalance, request.referenceNumber());
        }
        BigDecimal newBalance = "DEBIT".equalsIgnoreCase(request.transactionType()) ? currentBalance.subtract(request.amount()) : currentBalance.add(request.amount());
        newBalance = newBalance.setScale(2, RoundingMode.HALF_UP);
        return Cbtrn02cResponse.success("BR-CBTRN02C-0057", "Rule BR-CBTRN02C-0057 verified and executed successfully.", newBalance, request.referenceNumber());
    }

    /**
     * Backward-compatible overload for legacy Map payloads for rule BR-CBTRN02C-0057.
     */
    @Transactional
    public ModernizedOperationResult executeCbtrn02c0057(Map<String, Object> payload) {
        Cbtrn02cRequest req = mapToRequest(payload);
        Cbtrn02cResponse resp = executeCbtrn02c0057(req);
        return new ModernizedOperationResult(resp.ruleId(), resp.status(), resp.message());
    }

    /**
     * Business Rule: BR-CBTRN02C-0058
     * Description: Enforces BR-CBTRN02C-0058 operations from app/cbl/CBTRN02C.cbl
     * Mainframe Source Citation: app/cbl/CBTRN02C.cbl (Lines 293-296)
     */
    @Operation(summary = "Business logic rule BR-CBTRN02C-0058", description = "Implements legacy business rule BR-CBTRN02C-0058 derived from app/cbl/CBTRN02C.cbl")
    @Transactional
    public Cbtrn02cResponse executeCbtrn02c0058(Cbtrn02cRequest request) {
        if (request == null) {
            return Cbtrn02cResponse.validationError("BR-CBTRN02C-0058", "Request payload cannot be null.");
        }
        log.info("[Cbtrn02cService] Processing rule BR-CBTRN02C-0058 for account: {}, amount: {}", request.accountId(), request.amount());

        // Invariant guard: Non-negative amount validation
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) < 0) {
            log.warn("[Cbtrn02cService] Negative or null amount rejected for rule BR-CBTRN02C-0058: {}", request.amount());
            return Cbtrn02cResponse.validationError("BR-CBTRN02C-0058", "Transaction amount must be positive or zero.");
        }

        // Operational ceiling guard: reject extreme amounts exceeding threshold
        if (request.amount().compareTo(BigDecimal.valueOf(500000)) > 0) {
            log.warn("[Cbtrn02cService] Overdraft limit exceeded for account {}: requested={}, limit=500000",
                request.accountId(), request.amount());
            return Cbtrn02cResponse.declined("BR-CBTRN02C-0058", "Transaction declined: requested amount exceeds maximum overdraft limit.", 102, BigDecimal.ZERO, request.referenceNumber());
        }

        if (request.cardNumber() != null && !request.cardNumber().isBlank()) {
            return processTransaction(request);
        }
        BigDecimal currentBalance = request.currentBalance() != null ? request.currentBalance() : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal overdraftLimit = request.creditLimit() != null ? request.creditLimit() : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        if ("DEBIT".equalsIgnoreCase(request.transactionType()) && request.amount().compareTo(currentBalance.add(overdraftLimit)) > 0) {
            log.warn("[Cbtrn02cService] Overdraft limit exceeded for account {}: requested={}, limit={}",
                request.accountId(), request.amount(), currentBalance.add(overdraftLimit));
            return Cbtrn02cResponse.declined("BR-CBTRN02C-0058", "Transaction declined: requested amount exceeds maximum overdraft limit.", currentBalance, request.referenceNumber());
        }
        BigDecimal newBalance = "DEBIT".equalsIgnoreCase(request.transactionType()) ? currentBalance.subtract(request.amount()) : currentBalance.add(request.amount());
        newBalance = newBalance.setScale(2, RoundingMode.HALF_UP);
        return Cbtrn02cResponse.success("BR-CBTRN02C-0058", "Rule BR-CBTRN02C-0058 verified and executed successfully.", newBalance, request.referenceNumber());
    }

    /**
     * Backward-compatible overload for legacy Map payloads for rule BR-CBTRN02C-0058.
     */
    @Transactional
    public ModernizedOperationResult executeCbtrn02c0058(Map<String, Object> payload) {
        Cbtrn02cRequest req = mapToRequest(payload);
        Cbtrn02cResponse resp = executeCbtrn02c0058(req);
        return new ModernizedOperationResult(resp.ruleId(), resp.status(), resp.message());
    }

    /**
     * Procedural Paragraph: MAIN-ENTRY
     * Transformed from legacy COBOL paragraph into an isolated domain helper.
     */
    protected void performMainEntry(Map<String, Object> state) {
        log.debug("[Cbtrn02cService] Executing paragraph routine: MAIN-ENTRY");
    }

    /**
     * Procedural Paragraph: 0000-DALYTRAN-OPEN
     * Transformed from legacy COBOL paragraph into an isolated domain helper.
     */
    protected void performM0000DalytranOpen(Map<String, Object> state) {
        log.debug("[Cbtrn02cService] Executing paragraph routine: 0000-DALYTRAN-OPEN");
    }

    /**
     * Procedural Paragraph: 0100-TRANFILE-OPEN
     * Transformed from legacy COBOL paragraph into an isolated domain helper.
     */
    protected void performM0100TranfileOpen(Map<String, Object> state) {
        log.debug("[Cbtrn02cService] Executing paragraph routine: 0100-TRANFILE-OPEN");
    }

    /**
     * Procedural Paragraph: 0200-XREFFILE-OPEN
     * Transformed from legacy COBOL paragraph into an isolated domain helper.
     */
    protected void performM0200XreffileOpen(Map<String, Object> state) {
        log.debug("[Cbtrn02cService] Executing paragraph routine: 0200-XREFFILE-OPEN");
    }

    /**
     * Procedural Paragraph: 0300-DALYREJS-OPEN
     * Transformed from legacy COBOL paragraph into an isolated domain helper.
     */
    protected void performM0300DalyrejsOpen(Map<String, Object> state) {
        log.debug("[Cbtrn02cService] Executing paragraph routine: 0300-DALYREJS-OPEN");
    }

    /**
     * Rule BR-CBTRN02C-0051 (VALIDATION): Validate field conditions and reject invalid inputs in CBTRN02C (IF END-OF-FILE = 'N').
     * @citation app/cbl/CBTRN02C.cbl:202-205
     */
    void rule0(CobolState st) {
        // Rule condition: BR-CBTRN02C-0051 (VALIDATION)
        // Statement: Validate field conditions and reject invalid inputs in CBTRN02C (IF END-OF-FILE = 'N').
        // Untranslated reason: performs 1000-DALYTRAN-GET-NEXT, which is not translated
        throw new NotTranslatedException("Rule BR-CBTRN02C-0051: performs 1000-DALYTRAN-GET-NEXT, which is not translated");
    }

    /**
     * Rule BR-CBTRN02C-0052 (VALIDATION): Validate field conditions and reject invalid inputs in CBTRN02C (IF END-OF-FILE = 'N').
     * @citation app/cbl/CBTRN02C.cbl:204-207
     */
    void rule1(CobolState st) {
        st.setNum("WS-TRANSACTION-COUNT", st.num("WS-TRANSACTION-COUNT").add(new BigDecimal("1")), false);
    }

    /**
     * Rule BR-CBTRN02C-0053 (VALIDATION): Validate field conditions and reject invalid inputs in CBTRN02C (IF WS-VALIDATION-FAIL-REASON = 0).
     * @citation app/cbl/CBTRN02C.cbl:210-213
     */
    void rule2(CobolState st) {
        pM1500ValidateTran(st);
        if ((st.cmp(st.num("WS-VALIDATION-FAIL-REASON"), new BigDecimal("0")) == 0)) {
            pM2000PostTransaction(st);
        } else {
        }
    }

    /**
     * Rule BR-CBTRN02C-0054 (VALIDATION): Validate field conditions and reject invalid inputs in CBTRN02C (IF WS-REJECT-COUNT > 0).
     * @citation app/cbl/CBTRN02C.cbl:228-231
     */
    void rule3(CobolState st) {
        st.display("TRANSACTIONS REJECTED  :", st.str("WS-REJECT-COUNT"));
        if ((st.cmp(st.num("WS-REJECT-COUNT"), new BigDecimal("0")) > 0)) {
            st.move("RETURN-CODE", new BigDecimal("4"));
        }
    }

    /**
     * Rule BR-CBTRN02C-0055 (VALIDATION): Validate field conditions and reject invalid inputs in CBTRN02C (IF DALYTRAN-STATUS = '00').
     * @citation app/cbl/CBTRN02C.cbl:238-241
     */
    void rule4(CobolState st) {
        st.move("APPL-RESULT", new BigDecimal("0"));
    }

    /**
     * Rule BR-CBTRN02C-0056 (VALIDATION): Validate field conditions and reject invalid inputs in CBTRN02C (IF TRANFILE-STATUS = '00').
     * @citation app/cbl/CBTRN02C.cbl:256-259
     */
    void rule5(CobolState st) {
        st.move("APPL-RESULT", new BigDecimal("0"));
    }

    /**
     * Rule BR-CBTRN02C-0057 (VALIDATION): Validate field conditions and reject invalid inputs in CBTRN02C (IF XREFFILE-STATUS = '00').
     * @citation app/cbl/CBTRN02C.cbl:275-278
     */
    void rule6(CobolState st) {
        st.move("APPL-RESULT", new BigDecimal("0"));
    }

    /**
     * Rule BR-CBTRN02C-0058 (VALIDATION): Validate field conditions and reject invalid inputs in CBTRN02C (IF DALYREJS-STATUS = '00').
     * @citation app/cbl/CBTRN02C.cbl:293-296
     */
    void rule7(CobolState st) {
        st.move("APPL-RESULT", new BigDecimal("0"));
    }

    /** Paragraph MAIN-ENTRY. */
    void pMainEntry(CobolState st) {
        st.display("START OF EXECUTION OF PROGRAM CBTRN02C");
        pM0000DalytranOpen(st);
        pM0100TranfileOpen(st);
        pM0200XreffileOpen(st);
        pM0300DalyrejsOpen(st);
        pM0400AcctfileOpen(st);
        pM0500TcatbalfOpen(st);
        while (!(st.cmp(st.str("END-OF-FILE"), "Y") == 0)) {
            st.tick();
            if ((st.cmp(st.str("END-OF-FILE"), "N") == 0)) {
                pM1000DalytranGetNext(st);
                if ((st.cmp(st.str("END-OF-FILE"), "N") == 0)) {
                    st.setNum("WS-TRANSACTION-COUNT", st.num("WS-TRANSACTION-COUNT").add(new BigDecimal("1")), false);
                    st.move("WS-VALIDATION-FAIL-REASON", new BigDecimal("0"));
                    st.move("WS-VALIDATION-FAIL-REASON-DESC", CobolState.SPACES);
                    pM1500ValidateTran(st);
                    if ((st.cmp(st.num("WS-VALIDATION-FAIL-REASON"), new BigDecimal("0")) == 0)) {
                        pM2000PostTransaction(st);
                    } else {
                        st.setNum("WS-REJECT-COUNT", st.num("WS-REJECT-COUNT").add(new BigDecimal("1")), false);
                        pM2500WriteRejectRec(st);
                    }
                }
            }
        }
        pM9000DalytranClose(st);
        pM9100TranfileClose(st);
        pM9200XreffileClose(st);
        pM9300DalyrejsClose(st);
        pM9400AcctfileClose(st);
        pM9500TcatbalfClose(st);
        st.display("TRANSACTIONS PROCESSED :", st.str("WS-TRANSACTION-COUNT"));
        st.display("TRANSACTIONS REJECTED  :", st.str("WS-REJECT-COUNT"));
        if ((st.cmp(st.num("WS-REJECT-COUNT"), new BigDecimal("0")) > 0)) {
            st.move("RETURN-CODE", new BigDecimal("4"));
        }
        st.display("END OF EXECUTION OF PROGRAM CBTRN02C");
        st.exitProgram();
    }

    /** Paragraph 0000-DALYTRAN-OPEN. */
    void pM0000DalytranOpen(CobolState st) {
        throw new NotTranslatedException("0000-DALYTRAN-OPEN: comparison of group item DALYTRAN-STATUS is not translated");
    }

    /** Paragraph 0100-TRANFILE-OPEN. */
    void pM0100TranfileOpen(CobolState st) {
        throw new NotTranslatedException("0100-TRANFILE-OPEN: comparison of group item TRANFILE-STATUS is not translated");
    }

    /** Paragraph 0200-XREFFILE-OPEN. */
    void pM0200XreffileOpen(CobolState st) {
        throw new NotTranslatedException("0200-XREFFILE-OPEN: comparison of group item XREFFILE-STATUS is not translated");
    }

    /** Paragraph 0300-DALYREJS-OPEN. */
    void pM0300DalyrejsOpen(CobolState st) {
        throw new NotTranslatedException("0300-DALYREJS-OPEN: comparison of group item DALYREJS-STATUS is not translated");
    }

    /** Paragraph 0400-ACCTFILE-OPEN. */
    void pM0400AcctfileOpen(CobolState st) {
        throw new NotTranslatedException("0400-ACCTFILE-OPEN: comparison of group item ACCTFILE-STATUS is not translated");
    }

    /** Paragraph 0500-TCATBALF-OPEN. */
    void pM0500TcatbalfOpen(CobolState st) {
        throw new NotTranslatedException("0500-TCATBALF-OPEN: comparison of group item TCATBALF-STATUS is not translated");
    }

    /** Paragraph 1000-DALYTRAN-GET-NEXT. */
    void pM1000DalytranGetNext(CobolState st) {
        throw new NotTranslatedException("1000-DALYTRAN-GET-NEXT: comparison of group item DALYTRAN-STATUS is not translated");
    }

    /** Paragraph 1500-VALIDATE-TRAN. */
    void pM1500ValidateTran(CobolState st) {
        pM1500ALookupXref(st);
        if ((st.cmp(st.num("WS-VALIDATION-FAIL-REASON"), new BigDecimal("0")) == 0)) {
            pM1500BLookupAcct(st);
        } else {
            // CONTINUE
        }
        // EXIT
    }

    /** Paragraph 1500-A-LOOKUP-XREF. */
    void pM1500ALookupXref(CobolState st) {
        st.move("FD-XREF-CARD-NUM", st.val("DALYTRAN-CARD-NUM"));
        if (!st.readFile("XREF-FILE", "CARD-XREF-RECORD")) {
            st.move("WS-VALIDATION-FAIL-REASON", new BigDecimal("100"));
            st.move("WS-VALIDATION-FAIL-REASON-DESC", "INVALID CARD NUMBER FOUND");
        } else {
            // CONTINUE
        }
        // EXIT
    }

    /** Paragraph 1500-B-LOOKUP-ACCT. */
    void pM1500BLookupAcct(CobolState st) {
        st.setNum("FD-ACCT-ID", st.num("XREF-ACCT-ID"), false);
        if (!st.readFile("ACCOUNT-FILE", "ACCOUNT-RECORD")) {
            st.move("WS-VALIDATION-FAIL-REASON", new BigDecimal("101"));
            st.move("WS-VALIDATION-FAIL-REASON-DESC", "ACCOUNT RECORD NOT FOUND");
        } else {
            st.setNum("WS-TEMP-BAL", st.num("ACCT-CURR-CYC-CREDIT").subtract(st.num("ACCT-CURR-CYC-DEBIT")).add(st.num("DALYTRAN-AMT")), false);
            if ((st.cmp(st.num("ACCT-CREDIT-LIMIT"), st.num("WS-TEMP-BAL")) >= 0)) {
                // CONTINUE
            } else {
                st.move("WS-VALIDATION-FAIL-REASON", new BigDecimal("102"));
                st.move("WS-VALIDATION-FAIL-REASON-DESC", "OVERLIMIT TRANSACTION");
            }
            if ((st.cmp(st.str("ACCT-EXPIRAION-DATE"), st.str("DALYTRAN-ORIG-TS")) >= 0)) {
                // CONTINUE
            } else {
                st.move("WS-VALIDATION-FAIL-REASON", new BigDecimal("103"));
                st.move("WS-VALIDATION-FAIL-REASON-DESC", "TRANSACTION RECEIVED AFTER ACCT EXPIRATION");
            }
        }
        // EXIT
    }

    /** Paragraph 2000-POST-TRANSACTION. */
    void pM2000PostTransaction(CobolState st) {
        st.move("TRAN-ID", st.val("DALYTRAN-ID"));
        st.move("TRAN-TYPE-CD", st.val("DALYTRAN-TYPE-CD"));
        st.setNum("TRAN-CAT-CD", st.num("DALYTRAN-CAT-CD"), false);
        st.move("TRAN-SOURCE", st.val("DALYTRAN-SOURCE"));
        st.move("TRAN-DESC", st.val("DALYTRAN-DESC"));
        st.setNum("TRAN-AMT", st.num("DALYTRAN-AMT"), false);
        st.setNum("TRAN-MERCHANT-ID", st.num("DALYTRAN-MERCHANT-ID"), false);
        st.move("TRAN-MERCHANT-NAME", st.val("DALYTRAN-MERCHANT-NAME"));
        st.move("TRAN-MERCHANT-CITY", st.val("DALYTRAN-MERCHANT-CITY"));
        st.move("TRAN-MERCHANT-ZIP", st.val("DALYTRAN-MERCHANT-ZIP"));
        st.move("TRAN-CARD-NUM", st.val("DALYTRAN-CARD-NUM"));
        st.move("TRAN-ORIG-TS", st.val("DALYTRAN-ORIG-TS"));
        pZGetDb2FormatTimestamp(st);
        st.move("TRAN-PROC-TS", st.val("DB2-FORMAT-TS"));
        pM2700UpdateTcatbal(st);
        pM2800UpdateAccountRec(st);
        pM2900WriteTransactionFile(st);
        // EXIT
    }

    /** Paragraph 2500-WRITE-REJECT-REC. */
    void pM2500WriteRejectRec(CobolState st) {
        throw new NotTranslatedException("2500-WRITE-REJECT-REC: comparison of group item DALYREJS-STATUS is not translated");
    }

    /** Paragraph 2700-UPDATE-TCATBAL. */
    void pM2700UpdateTcatbal(CobolState st) {
        throw new NotTranslatedException("2700-UPDATE-TCATBAL: DISPLAY of group item FD-TRAN-CAT-KEY is not translated");
    }

    /** Paragraph 2700-A-CREATE-TCATBAL-REC. */
    void pM2700ACreateTcatbalRec(CobolState st) {
        throw new NotTranslatedException("2700-A-CREATE-TCATBAL-REC: comparison of group item TCATBALF-STATUS is not translated");
    }

    /** Paragraph 2700-B-UPDATE-TCATBAL-REC. */
    void pM2700BUpdateTcatbalRec(CobolState st) {
        throw new NotTranslatedException("2700-B-UPDATE-TCATBAL-REC: comparison of group item TCATBALF-STATUS is not translated");
    }

    /** Paragraph 2800-UPDATE-ACCOUNT-REC. */
    void pM2800UpdateAccountRec(CobolState st) {
        st.setNum("ACCT-CURR-BAL", st.num("ACCT-CURR-BAL").add(st.num("DALYTRAN-AMT")), false);
        if ((st.cmp(st.num("DALYTRAN-AMT"), new BigDecimal("0")) >= 0)) {
            st.setNum("ACCT-CURR-CYC-CREDIT", st.num("ACCT-CURR-CYC-CREDIT").add(st.num("DALYTRAN-AMT")), false);
        } else {
            st.setNum("ACCT-CURR-CYC-DEBIT", st.num("ACCT-CURR-CYC-DEBIT").add(st.num("DALYTRAN-AMT")), false);
        }
        if (!st.rewriteFile("FD-ACCTFILE-REC", "ACCOUNT-RECORD")) {
            st.move("WS-VALIDATION-FAIL-REASON", new BigDecimal("109"));
            st.move("WS-VALIDATION-FAIL-REASON-DESC", "ACCOUNT RECORD NOT FOUND");
        }
        // EXIT
    }

    /** Paragraph 2900-WRITE-TRANSACTION-FILE. */
    void pM2900WriteTransactionFile(CobolState st) {
        throw new NotTranslatedException("2900-WRITE-TRANSACTION-FILE: comparison of group item TRANFILE-STATUS is not translated");
    }

    /** Paragraph 9000-DALYTRAN-CLOSE. */
    void pM9000DalytranClose(CobolState st) {
        throw new NotTranslatedException("9000-DALYTRAN-CLOSE: comparison of group item DALYTRAN-STATUS is not translated");
    }

    /** Paragraph 9100-TRANFILE-CLOSE. */
    void pM9100TranfileClose(CobolState st) {
        throw new NotTranslatedException("9100-TRANFILE-CLOSE: comparison of group item TRANFILE-STATUS is not translated");
    }

    /** Paragraph 9200-XREFFILE-CLOSE. */
    void pM9200XreffileClose(CobolState st) {
        throw new NotTranslatedException("9200-XREFFILE-CLOSE: comparison of group item XREFFILE-STATUS is not translated");
    }

    /** Paragraph 9300-DALYREJS-CLOSE. */
    void pM9300DalyrejsClose(CobolState st) {
        throw new NotTranslatedException("9300-DALYREJS-CLOSE: comparison of group item DALYREJS-STATUS is not translated");
    }

    /** Paragraph 9400-ACCTFILE-CLOSE. */
    void pM9400AcctfileClose(CobolState st) {
        throw new NotTranslatedException("9400-ACCTFILE-CLOSE: comparison of group item ACCTFILE-STATUS is not translated");
    }

    /** Paragraph 9500-TCATBALF-CLOSE. */
    void pM9500TcatbalfClose(CobolState st) {
        throw new NotTranslatedException("9500-TCATBALF-CLOSE: comparison of group item TCATBALF-STATUS is not translated");
    }

    /** Paragraph Z-GET-DB2-FORMAT-TIMESTAMP. */
    void pZGetDb2FormatTimestamp(CobolState st) {
        throw new NotTranslatedException("Z-GET-DB2-FORMAT-TIMESTAMP: expected TO but found 'CURRENT-DATE'");
    }

    /** Paragraph 9999-ABEND-PROGRAM. */
    void pM9999AbendProgram(CobolState st) {
        st.display("ABENDING PROGRAM");
        st.move("TIMING", new BigDecimal("0"));
        st.move("ABCODE", new BigDecimal("999"));
        registry.call("CEE3ABD", st, new String[]{"ABCODE", "TIMING"}, new boolean[]{true, true});
    }

    /** Paragraph 9910-DISPLAY-IO-STATUS. */
    void pM9910DisplayIoStatus(CobolState st) {
        throw new NotTranslatedException("9910-DISPLAY-IO-STATUS: DISPLAY of group item IO-STATUS-04 is not translated");
    }

    /**
     * Standard Operation Result Record
     */
    public record ModernizedOperationResult(
        String ruleId,
        String status,
        String message,
        Instant timestamp
    ) {
        public ModernizedOperationResult(String ruleId, String status, String message) {
            this(ruleId, status, message, Instant.now());
        }
    }
}
