package com.bbh.compugain.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

import javax.annotation.concurrent.Immutable;
import java.util.UUID;

/**
 * Created by smutyala on 5/5/2017.
 */
//@Immutable
@Value
//@Builder
//@Wither
@JsonDeserialize
public final class BBH540Message {
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
