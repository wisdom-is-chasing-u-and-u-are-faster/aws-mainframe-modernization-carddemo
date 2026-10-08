package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized POJO DTO generated from Copybook: PASFLPCB
 * @citation app/app-authorization-ims-db2-mq/cpy/PASFLPCB.CPY
 * 8 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pasflpcb implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: PASFL-DBDNAME X(08) (line 18)
     * PIC clause: X(08)
     */
    @JsonProperty("PASFL_DBDNAME")
    private String pasflDbdname;

    /**
     * Original: PASFL-SEG-LEVEL X(02) (line 19)
     * PIC clause: X(02)
     */
    @JsonProperty("PASFL_SEG_LEVEL")
    private String pasflSegLevel;

    /**
     * Original: PASFL-PCB-STATUS X(02) (line 20)
     * PIC clause: X(02)
     */
    @JsonProperty("PASFL_PCB_STATUS")
    private String pasflPcbStatus;

    /**
     * Original: PASFL-PCB-PROCOPT X(04) (line 21)
     * PIC clause: X(04)
     */
    @JsonProperty("PASFL_PCB_PROCOPT")
    private String pasflPcbProcopt;

    /**
     * Original: PASFL-SEG-NAME X(08) (line 23)
     * PIC clause: X(08)
     */
    @JsonProperty("PASFL_SEG_NAME")
    private String pasflSegName;

    /**
     * Original: PASFL-KEYFB-NAME S9(05) (line 24)
     * PIC clause: S9(05)
     */
    @JsonProperty("PASFL_KEYFB_NAME")
    private Integer pasflKeyfbName;

    /**
     * Original: PASFL-NUM-SENSEGS S9(05) (line 25)
     * PIC clause: S9(05)
     */
    @JsonProperty("PASFL_NUM_SENSEGS")
    private Integer pasflNumSensegs;

    /**
     * Original: PASFL-KEYFB X(100) (line 26)
     * PIC clause: X(100)
     */
    @JsonProperty("PASFL_KEYFB")
    private String pasflKeyfb;

}
