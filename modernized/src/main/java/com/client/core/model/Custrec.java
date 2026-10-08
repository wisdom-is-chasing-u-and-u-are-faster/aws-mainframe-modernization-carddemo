package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized POJO DTO generated from Copybook: CUSTREC
 * @citation app/cpy/CUSTREC.cpy
 * 18 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Custrec implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: CUST-ID 9(09) (line 5)
     * PIC clause: 9(09)
     */
    @JsonProperty("CUST_ID")
    private Integer custId;

    /**
     * Original: CUST-FIRST-NAME X(25) (line 6)
     * PIC clause: X(25)
     */
    @JsonProperty("CUST_FIRST_NAME")
    private String custFirstName;

    /**
     * Original: CUST-MIDDLE-NAME X(25) (line 7)
     * PIC clause: X(25)
     */
    @JsonProperty("CUST_MIDDLE_NAME")
    private String custMiddleName;

    /**
     * Original: CUST-LAST-NAME X(25) (line 8)
     * PIC clause: X(25)
     */
    @JsonProperty("CUST_LAST_NAME")
    private String custLastName;

    /**
     * Original: CUST-ADDR-LINE-1 X(50) (line 9)
     * PIC clause: X(50)
     */
    @JsonProperty("CUST_ADDR_LINE_1")
    private String custAddrLine1;

    /**
     * Original: CUST-ADDR-LINE-2 X(50) (line 10)
     * PIC clause: X(50)
     */
    @JsonProperty("CUST_ADDR_LINE_2")
    private String custAddrLine2;

    /**
     * Original: CUST-ADDR-LINE-3 X(50) (line 11)
     * PIC clause: X(50)
     */
    @JsonProperty("CUST_ADDR_LINE_3")
    private String custAddrLine3;

    /**
     * Original: CUST-ADDR-STATE-CD X(02) (line 12)
     * PIC clause: X(02)
     */
    @JsonProperty("CUST_ADDR_STATE_CD")
    private String custAddrStateCd;

    /**
     * Original: CUST-ADDR-COUNTRY-CD X(03) (line 13)
     * PIC clause: X(03)
     */
    @JsonProperty("CUST_ADDR_COUNTRY_CD")
    private String custAddrCountryCd;

    /**
     * Original: CUST-ADDR-ZIP X(10) (line 14)
     * PIC clause: X(10)
     */
    @JsonProperty("CUST_ADDR_ZIP")
    private String custAddrZip;

    /**
     * Original: CUST-PHONE-NUM-1 X(15) (line 15)
     * PIC clause: X(15)
     */
    @JsonProperty("CUST_PHONE_NUM_1")
    private String custPhoneNum1;

    /**
     * Original: CUST-PHONE-NUM-2 X(15) (line 16)
     * PIC clause: X(15)
     */
    @JsonProperty("CUST_PHONE_NUM_2")
    private String custPhoneNum2;

    /**
     * Original: CUST-SSN 9(09) (line 17)
     * PIC clause: 9(09)
     */
    @JsonProperty("CUST_SSN")
    private Integer custSsn;

    /**
     * Original: CUST-GOVT-ISSUED-ID X(20) (line 18)
     * PIC clause: X(20)
     */
    @JsonProperty("CUST_GOVT_ISSUED_ID")
    private String custGovtIssuedId;

    /**
     * Original: CUST-DOB-YYYYMMDD X(10) (line 19)
     * PIC clause: X(10)
     */
    @JsonProperty("CUST_DOB_YYYYMMDD")
    private String custDobYyyymmdd;

    /**
     * Original: CUST-EFT-ACCOUNT-ID X(10) (line 20)
     * PIC clause: X(10)
     */
    @JsonProperty("CUST_EFT_ACCOUNT_ID")
    private String custEftAccountId;

    /**
     * Original: CUST-PRI-CARD-HOLDER-IND X(01) (line 21)
     * PIC clause: X(01)
     */
    @JsonProperty("CUST_PRI_CARD_HOLDER_IND")
    private String custPriCardHolderInd;

    /**
     * Original: CUST-FICO-CREDIT-SCORE 9(03) (line 22)
     * PIC clause: 9(03)
     */
    @JsonProperty("CUST_FICO_CREDIT_SCORE")
    private Integer custFicoCreditScore;

}
