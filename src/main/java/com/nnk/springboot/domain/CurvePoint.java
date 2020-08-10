package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "curvepoint")
public class CurvePoint implements Serializable {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Digits(message = "CurveId must be integer number", integer = 4, fraction = 0)
    private Integer curveId;

    @NotNull(message = "AsOfDate is mandatory")
    private Timestamp asOfDate;

    @Digits(message = "Term must be digits number", integer = 3, fraction = 2)
    private Double term;

    @Digits(message = "Value must be digits number", integer = 3, fraction = 2)
    private Double value;

    @NotNull(message = "CreationDate is mandatory")
    private Timestamp creationDate;

    public CurvePoint() {
    }

    public CurvePoint(@Digits(message = "CurveId must be integer number", integer = 4, fraction = 0) Integer curveId, @NotNull(message = "AsOfDate is mandatory") Timestamp asOfDate, @Digits(message = "Term must be digits number", integer = 3, fraction = 2) Double term, @Digits(message = "Value must be digits number", integer = 3, fraction = 2) Double value, @NotNull(message = "CreationDate is mandatory") Timestamp creationDate) {
        this.curveId = curveId;
        this.asOfDate = asOfDate;
        this.term = term;
        this.value = value;
        this.creationDate = creationDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = asOfDate;
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
