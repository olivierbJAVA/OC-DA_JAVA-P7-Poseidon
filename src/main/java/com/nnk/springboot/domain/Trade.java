package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "TradeDate is mandatory")
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

    @NotBlank(message = "CreationDate is mandatory")
    private Timestamp creationDate;

    @NotBlank(message = "RevisionName is mandatory")
    private String revisionName;

    @NotBlank(message = "RevisionDate is mandatory")
    private Timestamp revisionDate;

    @NotBlank(message = "DealName is mandatory")
    private String dealName;

    @NotBlank(message = "DealType is mandatory")
    private String dealType;

    @NotBlank(message = "SourceListId is mandatory")
    private String sourceListId;

    @NotBlank(message = "Side is mandatory")
    private String side;


}
