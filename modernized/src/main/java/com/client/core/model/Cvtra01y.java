package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized JPA Entity generated from Copybook: CVTRA01Y
 * @citation app/cpy/CVTRA01Y.cpy
 * 4 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cvtra01y")
public class Cvtra01y implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: TRANCAT-ACCT-ID 9(11) (line 6)
     * PIC clause: 9(11)
     */
    @Id
    @Column(name = "trancat_acct_id", nullable = false)
    @JsonProperty("TRANCAT_ACCT_ID")
    private Long trancatAcctId;

    /**
     * Original: TRANCAT-TYPE-CD X(02) (line 7)
     * PIC clause: X(02)
     */
    @Column(name = "trancat_type_cd")
    @JsonProperty("TRANCAT_TYPE_CD")
    private String trancatTypeCd;

    /**
     * Original: TRANCAT-CD 9(04) (line 8)
     * PIC clause: 9(04)
     */
    @Column(name = "trancat_cd")
    @JsonProperty("TRANCAT_CD")
    private Integer trancatCd;

    /**
     * Original: TRAN-CAT-BAL S9(09)V99 (line 9)
     * PIC clause: S9(09)V99
     */
    @Column(name = "tran_cat_bal")
    @JsonProperty("TRAN_CAT_BAL")
    private BigDecimal tranCatBal;

}
