/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.bbh.compugain.api.impl;

import akka.Done;
import com.bbh.compugain.api.BBH540Message;
import com.lightbend.lagom.javadsl.persistence.cassandra.CassandraSession;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Singleton
public class CompugainStreamRepository {
  private final CassandraSession uninitialisedSession;

  // Will return the session when the Cassandra tables have been successfully created
  private volatile CompletableFuture<CassandraSession> initialisedSession;

  @Inject
  public CompugainStreamRepository(CassandraSession uninitialisedSession) {
    this.uninitialisedSession = uninitialisedSession;
    // Eagerly create the session
    session();
  }

  private CompletionStage<CassandraSession> session() {
    // If there's no initialised session, or if the initialised session future completed
    // with an exception, then reinitialise the session and attempt to create the tables
    if (initialisedSession == null || initialisedSession.isCompletedExceptionally()) {
      initialisedSession = uninitialisedSession.executeCreateTable(
          "CREATE TABLE IF NOT EXISTS bbh_swift_mt_540_messages_details (" +
                  "    swift_message_date bigint," +
                  "    swift_message_id uuid," +
                  "    applicationid text," +
                  "    banking_priority text," +
                  "    block_1_header_block text," +
                  "    block_1_identifier text," +
                  "    block_2_application_header text," +
                  "    block_2_identifier text," +
                  "    block_3_identifier text," +
                  "    block_3_user_header text," +
                  "    block_4_identifier text," +
                  "    block_5_identifier text," +
                  "    buyr_95p text," +
                  "    create_db_datetime text," +
                  "    create_db_user text," +
                  "    deag_95p text," +
                  "    deliverymonitoring text," +
                  "    etprty_pset_16s text," +
                  "    fiac_16r text," +
                  "    fiac_16s text," +
                  "    fiac_36b text," +
                  "    fiac_97a text," +
                  "    genl_16r text," +
                  "    genl_16s text," +
                  "    input_output_identifier text," +
                  "    isin_35b text," +
                  "    logicalterminal text," +
                  "    message_datetime bigint," +
                  "    message_user_preference text," +
                  "    messagepriority text," +
                  "    messagetype text," +
                  "    newg_23g text," +
                  "    obsolescenceperiod text," +
                  "    pset_95p text," +
                  "    reag_95p text," +
                  "    receiveraddress text," +
                  "    reciever_id text," +
                  "    reciever_name text," +
                  "    recu_95p text," +
                  "    second_element text," +
                  "    seller_95p text," +
                  "    seme_20c text," +
                  "    send_recieve_status text," +
                  "    sender_id text," +
                  "    sender_name text," +
                  "    sequencenumber text," +
                  "    serviceid text," +
                  "    sessionnumber text," +
                  "    setdet_16r text," +
                  "    setdet_16s text," +
                  "    setprty_buyr_16r text," +
                  "    setprty_buyr_16s text," +
                  "    setprty_delivery_agent_16r text," +
                  "    setprty_delivery_agent_16s text," +
                  "    setprty_pset_16r text," +
                  "    setprty_recieving_agent_16r text," +
                  "    setprty_recieving_agent_16s text," +
                  "    setprty_recieving_custd_16r text," +
                  "    setprty_recieving_custd_16s text," +
                  "    setprty_seller_16r text," +
                  "    setprty_seller_16s text," +
                  "    setr_22f text," +
                  "    sett_98a text," +
                  "    swift_message text," +
                  "    third_element text," +
                  "    trad_98a text," +
                  "    traddet_16r text," +
                  "    traddet_16s text," +
                  "    validation_status boolean," +
                  "    INSTRUMENTTYPE_12A text,"+
                  "    DATEOFISSUE_98A text,"+
                  "    SELLER_95R text,"+
                  "    CURRENCY_11A text,"+
                  "    SETTLEMENTAMOUNT_36B text,"+
                  "    cola_22f  text,"+
                  "    matu_98a text," +
                  "    PRIMARY KEY ((swift_message_date), swift_message_id)" +
                  ") "
      ).thenApply(done -> uninitialisedSession).toCompletableFuture();
    }
    return initialisedSession;
  }

  public CompletionStage<Done> updateMessage(BBH540Message bbh540Message) {
    return session().thenCompose(session ->
        session.executeWrite("INSERT INTO bbh_swift_mt_540_messages_details(" +
                        "swift_message_date,swift_message_id,applicationid,banking_priority,block_1_header_block,block_1_identifier,block_2_application_header,block_2_identifier,block_3_identifier,block_3_user_header,block_4_identifier,block_5_identifier,buyr_95p,create_db_datetime,create_db_user,deag_95p,deliverymonitoring,etprty_pset_16s,fiac_16r,fiac_16s,fiac_36b,fiac_97a,genl_16r,genl_16s,input_output_identifier,isin_35b,logicalterminal,message_datetime ,message_user_preference,messagepriority,messagetype,newg_23g,obsolescenceperiod,pset_95p,reag_95p,receiveraddress,reciever_id,reciever_name,recu_95p,second_element,seller_95p,seme_20c,send_recieve_status,sender_id,sender_name,sequencenumber,serviceid,sessionnumber,setdet_16r,setdet_16s,setprty_buyr_16r,setprty_buyr_16s,setprty_delivery_agent_16r,setprty_delivery_agent_16S,setprty_pset_16r,setprty_recieving_agent_16r,setprty_recieving_agent_16s,setprty_recieving_custd_16r,setprty_recieving_custd_16s,setprty_seller_16r,setprty_seller_16s,setr_22f,sett_98a,third_element,trad_98a,traddet_16r,traddet_16s,validation_status,INSTRUMENTTYPE_12A,DATEOFISSUE_98A,SELLER_95R,CURRENCY_11A,SETTLEMENTAMOUNT_36B,cola_22f,matu_98a, SWIFT_MESSAGE)"+
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                bbh540Message.getSWIFT_MESSAGE_DATE(), UUID.fromString(bbh540Message.getSWIFT_MESSAGE_ID()), bbh540Message.getApplicationId(), bbh540Message.getBANKING_PRIORITY(),
                bbh540Message.getBLOCK_1_HEADER_BLOCK(), bbh540Message.getBLOCK_1_IDENTIFIER(), bbh540Message.getBLOCK_2_APPLICATION_HEADER(),bbh540Message.getBLOCK_2_IDENTIFIER(),
                bbh540Message.getBLOCK_3_IDENTIFIER(), bbh540Message.getBLOCK_3_USER_HEADER(), bbh540Message.getBLOCK_4_IDENTIFIER(), bbh540Message.getBLOCK_5_IDENTIFIER(),
                bbh540Message.getBUYR_95P(), new Date().getTime()+"", bbh540Message.getCREATE_DB_USER(), bbh540Message.getDEAG_95P(),
                bbh540Message.getDeliveryMonitoring(), bbh540Message.getETPRTY_PSET_16S(), bbh540Message.getFIAC_16R(), bbh540Message.getFIAC_16S(),
                bbh540Message.getFIAC_36B(), bbh540Message.getFIAC_97A(), bbh540Message.getGENL_16R(), bbh540Message.getGENL_16S(),
                bbh540Message.getINPUT_OUTPUT_IDENTIFIER(), bbh540Message.getISIN_35B(), bbh540Message.getLogicalTerminal(), bbh540Message.getMESSAGE_DATETIME(),
                bbh540Message.getMESSAGE_USER_PREFERENCE(), bbh540Message.getMessagePriority(), bbh540Message.getMessageType(), bbh540Message.getNEWG_23G(),
                bbh540Message.getObsolescencePeriod(), bbh540Message.getPSET_95P(), bbh540Message.getREAG_95P(), bbh540Message.getReceiverAddress(),
                bbh540Message.getRECIEVER_ID(), bbh540Message.getRECIEVER_NAME(), bbh540Message.getRECU_95P(), bbh540Message.getSECOND_ELEMENT(),
                bbh540Message.getSELLER_95P(), bbh540Message.getSEME_20C(), bbh540Message.getSEND_RECIEVE_STATUS(), bbh540Message.getSENDER_ID(),
                bbh540Message.getSENDER_NAME(), bbh540Message.getSequenceNumber(), bbh540Message.getServiceId(), bbh540Message.getSessionNumber(),
                bbh540Message.getSETDET_16R(), bbh540Message.getSETDET_16S(), bbh540Message.getSETPRTY_BUYR_16R(), bbh540Message.getSETPRTY_BUYR_16S(),
                bbh540Message.getSETPRTY_DELIVERY_AGENT_16R(), bbh540Message.getSETPRTY_DELIVERY_AGENT_16S(), bbh540Message.getSETPRTY_PSET_16R(), bbh540Message.getSETPRTY_RECIEVING_AGENT_16R(),
                bbh540Message.getSETPRTY_RECIEVING_AGENT_16S(), bbh540Message.getSETPRTY_RECIEVING_CUSTD_16R(), bbh540Message.getSETPRTY_RECIEVING_CUSTD_16S(), bbh540Message.getSETPRTY_SELLER_16R(),
                bbh540Message.getSETPRTY_SELLER_16S(), bbh540Message.getSETR_22F(), bbh540Message.getSETT_98A(), bbh540Message.getTHIRD_ELEMENT(),
                bbh540Message.getTRAD_98A(), bbh540Message.getTRADDET_16R(), bbh540Message.getTRADDET_16S(), bbh540Message.isVALIDATION_STATUS(),
                bbh540Message.getINSTRUMENTTYPE_12A(), bbh540Message.getDATEOFISSUE_98A(), bbh540Message.getSELLER_95R(), bbh540Message.getCURRENCY_11A(),bbh540Message.getSETTLEMENTAMOUNT_36B(),
                bbh540Message.getCOLA_22F(),bbh540Message.getDATEOFMATURITY_98A(),bbh540Message.getSWIFT_MESSAGE())
    );
  }

}
