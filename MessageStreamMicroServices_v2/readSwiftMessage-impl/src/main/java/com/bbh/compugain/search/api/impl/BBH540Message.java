package com.bbh.compugain.search.api.impl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;

/**
 * Created by smutyala on 5/5/2017.
 */
//@Immutable
@Value
//@Builder
//@Wither
@JsonDeserialize
public final class BBH540Message {
    BBH540Message(Long SWIFT_MESSAGE_DATE, String SWIFT_MESSAGE_ID, String applicationId,String BANKING_PRIORITY,String BLOCK_1_HEADER_BLOCK,String BLOCK_1_IDENTIFIER,String BLOCK_2_APPLICATION_HEADER,String BLOCK_2_IDENTIFIER,String BLOCK_3_IDENTIFIER,String BLOCK_3_USER_HEADER,String BLOCK_4_IDENTIFIER,String BLOCK_5_IDENTIFIER,String BUYR_95P,String CREATE_DB_DATETIME,String CREATE_DB_USER,String CURRENCY_11A,String DATEOFISSUE_98A,String DEAG_95P,String deliveryMonitoring,String ETPRTY_PSET_16S,String FIAC_16R,String FIAC_16S,String FIAC_36B,String FIAC_97A,String GENL_16R,String GENL_16S,String INPUT_OUTPUT_IDENTIFIER,String INSTRUMENTTYPE_12A,String ISIN_35B,String logicalTerminal,Long MESSAGE_DATETIME,String MESSAGE_USER_PREFERENCE,String messagePriority,String messageType,String NEWG_23G,String obsolescencePeriod,String PSET_95P,String REAG_95P,String receiverAddress,String RECIEVER_ID,String RECIEVER_NAME,String RECU_95P,String SECOND_ELEMENT,String SELLER_95P, String SELLER_95R,String SEME_20C,String SEND_RECIEVE_STATUS,String SENDER_ID,String SENDER_NAME,String sequenceNumber,String serviceId,String sessionNumber,String SETDET_16R,String SETDET_16S,String SETPRTY_BUYR_16R,String SETPRTY_BUYR_16S,String SETPRTY_DELIVERY_AGENT_16R,String SETPRTY_DELIVERY_AGENT_16S,String SETPRTY_PSET_16R,String SETPRTY_RECIEVING_AGENT_16R,String SETPRTY_RECIEVING_AGENT_16S,String SETPRTY_RECIEVING_CUSTD_16R,String SETPRTY_RECIEVING_CUSTD_16S,String SETPRTY_SELLER_16R,String SETPRTY_SELLER_16S,String SETR_22F,String SETT_98A,String SETTLEMENTAMOUNT_36B,String THIRD_ELEMENT,String TRAD_98A,String TRADDET_16R,String TRADDET_16S,boolean VALIDATION_STATUS, String COLA_22F, String DATEOFMATURITY_98A, String SWIFT_MESSAGE){
        this.SWIFT_MESSAGE_ID = SWIFT_MESSAGE_ID;
        this.SWIFT_MESSAGE_DATE = SWIFT_MESSAGE_DATE;
        this.SENDER_ID=SENDER_ID;
        this.SENDER_NAME=SENDER_NAME;
        this.RECIEVER_ID=RECIEVER_ID;
        this.RECIEVER_NAME=RECIEVER_NAME;

        this.BLOCK_1_IDENTIFIER=BLOCK_1_IDENTIFIER;
        this.applicationId=applicationId;
        this.serviceId =serviceId ;
        this.logicalTerminal =logicalTerminal ;
        this.sessionNumber=sessionNumber;
        this.sequenceNumber =sequenceNumber ;
        this.BLOCK_1_HEADER_BLOCK =BLOCK_1_HEADER_BLOCK ;

        this.BLOCK_2_IDENTIFIER =BLOCK_2_IDENTIFIER ;
        this.INPUT_OUTPUT_IDENTIFIER=INPUT_OUTPUT_IDENTIFIER;
        this.messageType =messageType ;
        this.receiverAddress=receiverAddress;
        this.messagePriority=messagePriority;
        this.deliveryMonitoring=deliveryMonitoring;
        this.obsolescencePeriod=obsolescencePeriod;
        this.BLOCK_2_APPLICATION_HEADER=BLOCK_2_APPLICATION_HEADER;

        this.BLOCK_3_IDENTIFIER =BLOCK_3_IDENTIFIER ;
        this.BANKING_PRIORITY=BANKING_PRIORITY;
        this.MESSAGE_USER_PREFERENCE=MESSAGE_USER_PREFERENCE;
        this.BLOCK_3_USER_HEADER=BLOCK_3_USER_HEADER;

        this.BLOCK_4_IDENTIFIER =BLOCK_4_IDENTIFIER ;
        this.GENL_16R=GENL_16R;
        this.SEME_20C=SEME_20C;
        this.NEWG_23G=NEWG_23G;
        this.GENL_16S=GENL_16S;

        this.TRADDET_16R=TRADDET_16R;
        this.TRAD_98A=TRAD_98A;
        this.SETT_98A=SETT_98A;
        this.ISIN_35B=ISIN_35B;
        this.TRADDET_16S=TRADDET_16S;

        this.FIAC_16R=FIAC_16R;
        this.FIAC_36B=FIAC_36B;
        this.FIAC_97A=FIAC_97A;
        this.FIAC_16S=FIAC_16S;

        this.SETDET_16R=SETDET_16R;
        this.SETR_22F=SETR_22F;

        this.SETPRTY_SELLER_16R =SETPRTY_SELLER_16R ;
        this.SELLER_95P=SELLER_95P;
        this.SETPRTY_SELLER_16S =SETPRTY_SELLER_16S ;

        this.SETPRTY_DELIVERY_AGENT_16R=SETPRTY_DELIVERY_AGENT_16R;
        this.DEAG_95P=DEAG_95P;
        this.SETPRTY_DELIVERY_AGENT_16S=SETPRTY_DELIVERY_AGENT_16S;

        this.SETPRTY_RECIEVING_AGENT_16R=SETPRTY_RECIEVING_AGENT_16R;
        this.REAG_95P =REAG_95P ;
        this.SETPRTY_RECIEVING_AGENT_16S=SETPRTY_RECIEVING_AGENT_16S;

        this.SETPRTY_RECIEVING_CUSTD_16R=SETPRTY_RECIEVING_CUSTD_16R;
        this.RECU_95P =RECU_95P ;
        this.SETPRTY_RECIEVING_CUSTD_16S=SETPRTY_RECIEVING_CUSTD_16S;

        this.SETPRTY_BUYR_16R=SETPRTY_BUYR_16R;
        this.BUYR_95P =BUYR_95P ;
        this.SETPRTY_BUYR_16S=SETPRTY_BUYR_16S;

        this.SETPRTY_PSET_16R=SETPRTY_PSET_16R;
        this.PSET_95P =PSET_95P ;
        this.ETPRTY_PSET_16S=ETPRTY_PSET_16S;
        this.SETDET_16S=SETDET_16S;

        this.BLOCK_5_IDENTIFIER =BLOCK_5_IDENTIFIER ;
        this.SECOND_ELEMENT=SECOND_ELEMENT;
        this.THIRD_ELEMENT=THIRD_ELEMENT;
        this.MESSAGE_DATETIME=MESSAGE_DATETIME;
        this.VALIDATION_STATUS=VALIDATION_STATUS;
        this.SEND_RECIEVE_STATUS=SEND_RECIEVE_STATUS;
        this.CREATE_DB_DATETIME =CREATE_DB_DATETIME ;
        this.CREATE_DB_USER=CREATE_DB_USER;
        this.INSTRUMENTTYPE_12A=INSTRUMENTTYPE_12A;
        this.DATEOFISSUE_98A=DATEOFISSUE_98A;
        this.SELLER_95R=SELLER_95R;
        this.CURRENCY_11A=CURRENCY_11A;
        this.SETTLEMENTAMOUNT_36B=SETTLEMENTAMOUNT_36B;
        this.COLA_22F = COLA_22F;
        this.DATEOFMATURITY_98A = DATEOFMATURITY_98A;
        this.SWIFT_MESSAGE = SWIFT_MESSAGE;
    };

    String SWIFT_MESSAGE_ID;
    Long SWIFT_MESSAGE_DATE;

    String SENDER_ID;
    String SENDER_NAME;
    String RECIEVER_ID;
    String RECIEVER_NAME;

    String BLOCK_1_IDENTIFIER;
    String applicationId;
    String serviceId ;
    String logicalTerminal ;
    String sessionNumber;
    String sequenceNumber ;
    String BLOCK_1_HEADER_BLOCK ;

    String BLOCK_2_IDENTIFIER ;
    String INPUT_OUTPUT_IDENTIFIER;
    String messageType ;
    String receiverAddress;
    String messagePriority;
    String deliveryMonitoring;
    String obsolescencePeriod;
    String BLOCK_2_APPLICATION_HEADER;

    String BLOCK_3_IDENTIFIER ;
    String BANKING_PRIORITY;
    String MESSAGE_USER_PREFERENCE;
    String BLOCK_3_USER_HEADER;

    String BLOCK_4_IDENTIFIER ;
    String GENL_16R;
    String SEME_20C;
    String NEWG_23G;
    String GENL_16S;

    String TRADDET_16R;
    String TRAD_98A;
    String SETT_98A;
    String ISIN_35B;
    String TRADDET_16S;

    String FIAC_16R;
    String FIAC_36B;
    String FIAC_97A;
    String FIAC_16S;

    String SETDET_16R;
    String SETR_22F;

    String SETPRTY_SELLER_16R ;
    String SELLER_95P;
    String SETPRTY_SELLER_16S ;

    String SETPRTY_DELIVERY_AGENT_16R;
    String DEAG_95P;
    String SETPRTY_DELIVERY_AGENT_16S;

    String SETPRTY_RECIEVING_AGENT_16R;
    String REAG_95P ;
    String SETPRTY_RECIEVING_AGENT_16S;

    String SETPRTY_RECIEVING_CUSTD_16R;
    String RECU_95P ;
    String SETPRTY_RECIEVING_CUSTD_16S;

    String SETPRTY_BUYR_16R;
    String BUYR_95P ;
    String SETPRTY_BUYR_16S;

    String SETPRTY_PSET_16R;
    String PSET_95P ;
    String ETPRTY_PSET_16S;
    String SETDET_16S;

    String BLOCK_5_IDENTIFIER ;
    String SECOND_ELEMENT;
    String THIRD_ELEMENT;
    Long MESSAGE_DATETIME;
    boolean VALIDATION_STATUS;
    String SEND_RECIEVE_STATUS;
    String CREATE_DB_DATETIME ;
    String CREATE_DB_USER;
    String INSTRUMENTTYPE_12A;
    String DATEOFISSUE_98A;
    String SELLER_95R;
    String CURRENCY_11A;
    String SETTLEMENTAMOUNT_36B;

    String COLA_22F;
    String DATEOFMATURITY_98A;
    String SWIFT_MESSAGE;
}
