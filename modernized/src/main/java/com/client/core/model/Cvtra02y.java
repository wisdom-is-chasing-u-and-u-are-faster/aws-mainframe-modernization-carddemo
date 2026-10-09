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
 * Modernized JPA Entity generated from Copybook: CVTRA02Y
 * @citation app/cpy/CVTRA02Y.cpy
 * 4 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cvtra02y")
public class Cvtra02y implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: DIS-ACCT-GROUP-ID X(10) (line 6)
     * PIC clause: X(10)
     */
    @Id
    @Column(name = "dis_acct_group_id", nullable = false)
    @JsonProperty("DIS_ACCT_GROUP_ID")
    private String disAcctGroupId;

    /**
     * Original: DIS-TRAN-TYPE-CD X(02) (line 7)
     * PIC clause: X(02)
     */
    @Column(name = "dis_tran_type_cd")
    @JsonProperty("DIS_TRAN_TYPE_CD")
    private String disTranTypeCd;

    /**
     * Original: DIS-TRAN-CAT-CD 9(04) (line 8)
     * PIC clause: 9(04)
     */
    @Column(name = "dis_tran_cat_cd")
    @JsonProperty("DIS_TRAN_CAT_CD")
    private Integer disTranCatCd;

    /**
     * Original: DIS-INT-RATE S9(04)V99 (line 9)
     * PIC clause: S9(04)V99
     */
    @Column(name = "dis_int_rate")
    @JsonProperty("DIS_INT_RATE")
    private BigDecimal disIntRate;

}
