package com.client.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modernized POJO DTO generated from Copybook: PAUTBPCB
 * @citation app/app-authorization-ims-db2-mq/cpy/PAUTBPCB.CPY
 * 8 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pautbpcb implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: PAUT-DBDNAME X(08) (line 18)
     * PIC clause: X(08)
     */
    @JsonProperty("PAUT_DBDNAME")
    private String pautDbdname;

    /**
     * Original: PAUT-SEG-LEVEL X(02) (line 19)
     * PIC clause: X(02)
     */
    @JsonProperty("PAUT_SEG_LEVEL")
    private String pautSegLevel;

    /**
     * Original: PAUT-PCB-STATUS X(02) (line 20)
     * PIC clause: X(02)
     */
    @JsonProperty("PAUT_PCB_STATUS")
    private String pautPcbStatus;

    /**
     * Original: PAUT-PCB-PROCOPT X(04) (line 21)
     * PIC clause: X(04)
     */
    @JsonProperty("PAUT_PCB_PROCOPT")
    private String pautPcbProcopt;

    /**
     * Original: PAUT-SEG-NAME X(08) (line 23)
     * PIC clause: X(08)
     */
    @JsonProperty("PAUT_SEG_NAME")
    private String pautSegName;

    /**
     * Original: PAUT-KEYFB-NAME S9(05) (line 24)
     * PIC clause: S9(05)
     */
    @JsonProperty("PAUT_KEYFB_NAME")
    private Integer pautKeyfbName;

    /**
     * Original: PAUT-NUM-SENSEGS S9(05) (line 25)
     * PIC clause: S9(05)
     */
    @JsonProperty("PAUT_NUM_SENSEGS")
    private Integer pautNumSensegs;

    /**
     * Original: PAUT-KEYFB X(255) (line 26)
     * PIC clause: X(255)
     */
    @JsonProperty("PAUT_KEYFB")
    private String pautKeyfb;

}
