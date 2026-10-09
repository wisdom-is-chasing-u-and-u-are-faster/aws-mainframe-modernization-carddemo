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
 * Modernized JPA Entity generated from Copybook: CVTRA03Y
 * @citation app/cpy/CVTRA03Y.cpy
 * 2 field(s) parsed from the copybook source.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cvtra03y")
public class Cvtra03y implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Original: TRAN-TYPE X(02) (line 5)
     * PIC clause: X(02)
     */
    @Id
    @Column(name = "tran_type", nullable = false)
    @JsonProperty("TRAN_TYPE")
    private String tranType;

    /**
     * Original: TRAN-TYPE-DESC X(50) (line 6)
     * PIC clause: X(50)
     */
    @Column(name = "tran_type_desc")
    @JsonProperty("TRAN_TYPE_DESC")
    private String tranTypeDesc;

}
