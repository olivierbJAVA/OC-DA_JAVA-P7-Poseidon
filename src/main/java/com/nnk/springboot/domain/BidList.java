package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList implements Serializable {
    // TODO: Map columns in data table BIDLIST with corresponding java fields

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bidListId;

    @Length(max=30, message = "Maximum length = 30 characters")
    @NotBlank(message = "Account is mandatory")
    private String account;

    @Length(max=30, message = "Maximum length = 30 characters")
    @NotBlank(message = "Type is mandatory")
    private String type;

    @Digits(message = "BidQuantity must be digits number", integer = 6, fraction = 2)
    @NotNull(message = "BidQuantity must not been null")
    private Double bidQuantity;

    @Digits(message = "AskQuantity must be digits number", integer = 6, fraction = 2)
    private Double askQuantity;

    @Digits(message = "Bid must be digits number", integer = 6, fraction = 2)
    private Double bid;

    @Digits(message = "Ask must be digits number", integer = 6, fraction = 2)
    private Double ask;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String benchmark;

    private Timestamp bidListDate;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String commentary;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String security;

    @Length(max=10, message = "Maximum length = 10 characters")
    private String status;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String trader;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String book;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String creationName;

    private Timestamp creationDate;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String revisionName;

    private Timestamp revisionDate;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String dealName;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String dealType;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String sourceListId;

    @Length(max=125, message = "Maximum length = 125 characters")
    private String side;

    public BidList() {
    }

    public BidList(@Length(max = 30, message = "Maximum length = 30 characters") @NotBlank(message = "Account is mandatory") String account, @Length(max = 30, message = "Maximum length = 30 characters") @NotBlank(message = "Type is mandatory") String type, @Digits(message = "BidQuantity must be digits number", integer = 6, fraction = 2) @NotNull(message = "BidQuantity must not been null") Double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    public void setBidListId(Integer bidListId) {
        this.bidListId = bidListId;
    }

    public Integer getBidListId() {
        return bidListId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public Double getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(Double askQuantity) {
        this.askQuantity = askQuantity;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getBidListDate() {
        return bidListDate;
    }

    public void setBidListDate(Timestamp bidListDate) {
        this.bidListDate = bidListDate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getRevisionName() {
        return revisionName;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public Timestamp getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Timestamp revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
