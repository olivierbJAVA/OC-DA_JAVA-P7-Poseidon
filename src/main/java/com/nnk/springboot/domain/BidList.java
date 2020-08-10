package com.nnk.springboot.domain;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList implements Serializable {
    // TODO: Map columns in data table BIDLIST with corresponding java fields

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer BidListId;

    @NotBlank(message = "Account is mandatory")
    private String account;

    @NotBlank(message = "Type is mandatory")
    private String type;

    @Digits(message = "BidQuantity must be digits number", integer = 6, fraction = 2)
    private Double bidQuantity;

    @Digits(message = "AskQuantity must be digits number", integer = 6, fraction = 2)
    private Double askQuantity;

    @Digits(message = "Bid must be digits number", integer = 6, fraction = 2)
    private Double bid;

    @Digits(message = "Ask must be digits number", integer = 6, fraction = 2)
    private Double ask;

    @NotBlank(message = "Benchmark is mandatory")
    private String benchmark;

    @NotNull(message = "BidListDate is mandatory")
    private Timestamp bidListDate;

    private String commentary;

    @NotBlank(message = "Security is mandatory")
    private String security;

    @NotBlank(message = "Status is mandatory")
    private String status;

    @NotBlank(message = "Trader is mandatory")
    private String trader;

    @NotBlank(message = "Book is mandatory")
    private String book;

    @NotBlank(message = "CreationName is mandatory")
    private String creationName;

    @NotNull(message = "CreationDate is mandatory")
    private Timestamp creationDate;

    private String revisionName;

    private Timestamp revisionDate;

    @NotBlank(message = "DealName is mandatory")
    private String dealName;

    @NotBlank(message = "DealType is mandatory")
    private String dealType;

    @NotBlank(message = "SourceListId is mandatory")
    private String sourceListId;

    @NotBlank(message = "Side is mandatory")
    private String side;

    public BidList() {
    }

    public BidList(@NotBlank(message = "Account is mandatory") String account, @NotBlank(message = "Type is mandatory") String type, @Digits(message = "BidQuantity must be digits number", integer = 6, fraction = 0) Double bidQuantity, @Digits(message = "AskQuantity must be digits number", integer = 6, fraction = 0) Double askQuantity, @Digits(message = "Bid must be digits number", integer = 3, fraction = 2) Double bid, @Digits(message = "Ask must be digits number", integer = 3, fraction = 2) Double ask, @NotBlank(message = "Benchmark is mandatory") String benchmark, @NotNull(message = "BidListDate is mandatory") Timestamp bidListDate, String commentary, @NotBlank(message = "Security is mandatory") String security, @NotBlank(message = "Status is mandatory") String status, @NotBlank(message = "Trader is mandatory") String trader, @NotBlank(message = "Book is mandatory") String book, @NotBlank(message = "CreationName is mandatory") String creationName, @NotNull(message = "CreationDate is mandatory") Timestamp creationDate, String revisionName, Timestamp revisionDate, @NotBlank(message = "DealName is mandatory") String dealName, @NotBlank(message = "DealType is mandatory") String dealType, @NotBlank(message = "SourceListId is mandatory") String sourceListId, @NotBlank(message = "Side is mandatory") String side) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
        this.askQuantity = askQuantity;
        this.bid = bid;
        this.ask = ask;
        this.benchmark = benchmark;
        this.bidListDate = bidListDate;
        this.commentary = commentary;
        this.security = security;
        this.status = status;
        this.trader = trader;
        this.book = book;
        this.creationName = creationName;
        this.creationDate = creationDate;
        this.revisionName = revisionName;
        this.revisionDate = revisionDate;
        this.dealName = dealName;
        this.dealType = dealType;
        this.sourceListId = sourceListId;
        this.side = side;
    }

    public Integer getBidListId() {
        return BidListId;
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
