package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized POJO DTO generated from Copybook: COADM02Y
 * @citation app/cpy/COADM02Y.cpy
 * 4 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coadm02y implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: CDEMO-ADMIN-OPT-COUNT 9(02) (line 22)
     * PIC clause: 9(02)
     */
    @JsonProperty("CDEMO_ADMIN_OPT_COUNT")
    private Integer cdemoAdminOptCount;

    /**
     * Original: CDEMO-ADMIN-OPT-NUM 9(02) (line 57)
     * PIC clause: 9(02)
     */
    @JsonProperty("CDEMO_ADMIN_OPT_NUM")
    private Integer cdemoAdminOptNum;

    /**
     * Original: CDEMO-ADMIN-OPT-NAME X(35) (line 58)
     * PIC clause: X(35)
     */
    @JsonProperty("CDEMO_ADMIN_OPT_NAME")
    private String cdemoAdminOptName;

    /**
     * Original: CDEMO-ADMIN-OPT-PGMNAME X(08) (line 59)
     * PIC clause: X(08)
     */
    @JsonProperty("CDEMO_ADMIN_OPT_PGMNAME")
    private String cdemoAdminOptPgmname;

}
