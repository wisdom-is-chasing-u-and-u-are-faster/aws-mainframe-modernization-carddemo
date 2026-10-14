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
 * Modernized JPA Entity generated from Copybook: COADM01
 * @citation app/bms/COADM01.bms
 * Generated entity schema with primary key and record payload.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coadm01")
public class Coadm01 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @JsonProperty("ID")
    private String id;

    @Column(name = "record_data", length = 4000)
    @JsonProperty("RECORD_DATA")
    private String recordData;

}
