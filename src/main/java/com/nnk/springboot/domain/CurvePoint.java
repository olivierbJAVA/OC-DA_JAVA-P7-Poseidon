package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Class materializing a CurvePoint.
 */
@Entity
@Table(name = "curvepoint")
public class CurvePoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Digits(message = "CurveId must be integer number with a maximum of 2 digits", integer = 2, fraction = 0)
    @NotNull(message = "CurveId must not been null")
    private Integer curveId;

    private Timestamp asOfDate;

    @Digits(message = "Term must be digits number with a maximum of 6 digits before the decimal point and 2 digits after", integer = 6, fraction = 2)
    @NotNull(message = "Term must not been null")
    private Double term;

    @Digits(message = "Value must be digits number with a maximum of 6 digits before the decimal point and 2 digits after", integer = 6, fraction = 2)
    @NotNull(message = "Value must not been null")
    private Double value;

    private Timestamp creationDate;

    public CurvePoint() {
    }

    /**
     * Constructs a new CurvePoint with the mandatory fields.
     *
     * @param curveId the curveId of the CurvePoint
     * @param term    the term of the CurvePoint
     * @param value   the bidQuantity of the CurvePoint
     */
    public CurvePoint(@Digits(message = "CurveId must be integer number with a maximum of 2 digits", integer = 2, fraction = 0) @NotNull(message = "CurveId must not been null") Integer curveId, @Digits(message = "Term must be digits number with a maximum of 6 digits before the decimal point and 2 digits after", integer = 6, fraction = 2) @NotNull(message = "Term must not been null") Double term, @Digits(message = "Value must be digits number with a maximum of 6 digits before the decimal point and 2 digits after", integer = 6, fraction = 2) @NotNull(message = "Value must not been null") Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
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
