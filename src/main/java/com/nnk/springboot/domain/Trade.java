package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table(name = "trade")
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tradeId;

    @NotBlank(message = "Account is mandatory")
    private String account;

    @NotBlank(message = "Type is mandatory")
    private String type;

    @Digits(message = "BuyQuantity must be digits number", integer = 6, fraction = 0)
    private Double buyQuantity;

    @Digits(message = "SellQuantity must be digits number", integer = 6, fraction = 0)
    private Double sellQuantity;

    @Digits(message = "BuyPrice must be digits number", integer = 3, fraction = 2)
    private Double buyPrice;

    @Digits(message = "SellPrice must be digits number", integer = 3, fraction = 2)
    private Double sellPrice;

    @NotBlank(message = "Benchmark is mandatory")
    private String benchmark;

    @NotNull(message = "TradeDate is mandatory")
    private Timestamp tradeDate;

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

    @NotBlank(message = "RevisionName is mandatory")
    private String revisionName;

    @NotNull(message = "RevisionDate is mandatory")
    private Timestamp revisionDate;

    @NotBlank(message = "DealName is mandatory")
    private String dealName;

    @NotBlank(message = "DealType is mandatory")
    private String dealType;

    @NotBlank(message = "SourceListId is mandatory")
    private String sourceListId;

    @NotBlank(message = "Side is mandatory")
    private String side;

    public Trade() {
    }

    public Trade(Integer tradeId, @NotBlank(message = "Account is mandatory") String account, @NotBlank(message = "Type is mandatory") String type, @Digits(message = "BuyQuantity must be digits number", integer = 6, fraction = 0) Double buyQuantity, @Digits(message = "SellQuantity must be digits number", integer = 6, fraction = 0) Double sellQuantity, @Digits(message = "BuyPrice must be digits number", integer = 3, fraction = 2) Double buyPrice, @Digits(message = "SellPrice must be digits number", integer = 3, fraction = 2) Double sellPrice, @NotBlank(message = "Benchmark is mandatory") String benchmark, @NotBlank(message = "TradeDate is mandatory") Timestamp tradeDate, @NotBlank(message = "Security is mandatory") String security, @NotBlank(message = "Status is mandatory") String status, @NotBlank(message = "Trader is mandatory") String trader, @NotBlank(message = "Book is mandatory") String book, @NotBlank(message = "CreationName is mandatory") String creationName, @NotBlank(message = "CreationDate is mandatory") Timestamp creationDate, @NotBlank(message = "RevisionName is mandatory") String revisionName, @NotBlank(message = "RevisionDate is mandatory") Timestamp revisionDate, @NotBlank(message = "DealName is mandatory") String dealName, @NotBlank(message = "DealType is mandatory") String dealType, @NotBlank(message = "SourceListId is mandatory") String sourceListId, @NotBlank(message = "Side is mandatory") String side) {
        this.tradeId = tradeId;
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
        this.sellQuantity = sellQuantity;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.benchmark = benchmark;
        this.tradeDate = tradeDate;
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

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
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

    public Double getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public Double getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(Double sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
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
