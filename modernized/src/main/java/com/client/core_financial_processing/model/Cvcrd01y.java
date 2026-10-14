package com.client.core_financial_processing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized JPA Entity generated from Copybook: CVCRD01Y
 * @citation cpy/CVCRD01Y.cpy
 * 10 discrete JPA field(s) decomposed for enterprise data modeling.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cvcrd01y")
public class Cvcrd01y implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 16-digit Primary Account Number (PAN)
     * PIC clause: X(16)
     */
    @Id
    @Column(name = "card_num", nullable = false)
    @JsonProperty("CARD_NUM")
    private String cardnum;

    /**
     * Associated Financial Account ID
     * PIC clause: 9(11)
     */
    @Column(name = "acct_id")
    @JsonProperty("ACCT_ID")
    private Long acctid;

    /**
     * Associated Customer ID
     * PIC clause: 9(09)
     */
    @Column(name = "cust_id")
    @JsonProperty("CUST_ID")
    private Integer custid;

    /**
     * Card Verification Value (CVV)
     * PIC clause: X(03)
     */
    @Column(name = "cvv_code")
    @JsonProperty("CVV_CODE")
    private String cvvcode;

    /**
     * Card Lifecycle Status (ACTIVE, BLOCKED, EXPIRED)
     * PIC clause: X(01)
     */
    @Column(name = "card_status")
    @JsonProperty("CARD_STATUS")
    private String cardstatus;

    /**
     * Card Brand (VISA, MASTERCARD, AMEX)
     * PIC clause: X(10)
     */
    @Column(name = "card_type")
    @JsonProperty("CARD_TYPE")
    private String cardtype;

    /**
     * Embossed Cardholder Name
     * PIC clause: X(50)
     */
    @Column(name = "card_embossed_name")
    @JsonProperty("CARD_EMBOSSED_NAME")
    private String cardembossedname;

    /**
     * Card Expiration Date (MM/YY or YYYY-MM-DD)
     * PIC clause: X(10)
     */
    @Column(name = "card_exp_date")
    @JsonProperty("CARD_EXP_DATE")
    private String cardexpdate;

    /**
     * Issuance Date
     * PIC clause: X(10)
     */
    @Column(name = "card_issued_date")
    @JsonProperty("CARD_ISSUED_DATE")
    private String cardissueddate;

    /**
     * Card Level Credit Limit
     * PIC clause: S9(10)V99
     */
    @Column(name = "credit_limit")
    @JsonProperty("CREDIT_LIMIT")
    private BigDecimal creditlimit;

}
