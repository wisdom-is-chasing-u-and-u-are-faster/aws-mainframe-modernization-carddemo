package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized POJO DTO generated from Copybook: COMEN02Y
 * @citation app/cpy/COMEN02Y.cpy
 * 5 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comen02y implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: CDEMO-MENU-OPT-COUNT 9(02) (line 21)
     * PIC clause: 9(02)
     */
    @JsonProperty("CDEMO_MENU_OPT_COUNT")
    private Integer cdemoMenuOptCount;

    /**
     * Original: CDEMO-MENU-OPT-NUM 9(02) (line 95)
     * PIC clause: 9(02)
     */
    @JsonProperty("CDEMO_MENU_OPT_NUM")
    private Integer cdemoMenuOptNum;

    /**
     * Original: CDEMO-MENU-OPT-NAME X(35) (line 96)
     * PIC clause: X(35)
     */
    @JsonProperty("CDEMO_MENU_OPT_NAME")
    private String cdemoMenuOptName;

    /**
     * Original: CDEMO-MENU-OPT-PGMNAME X(08) (line 97)
     * PIC clause: X(08)
     */
    @JsonProperty("CDEMO_MENU_OPT_PGMNAME")
    private String cdemoMenuOptPgmname;

    /**
     * Original: CDEMO-MENU-OPT-USRTYPE X(01) (line 98)
     * PIC clause: X(01)
     */
    @JsonProperty("CDEMO_MENU_OPT_USRTYPE")
    private String cdemoMenuOptUsrtype;

}
