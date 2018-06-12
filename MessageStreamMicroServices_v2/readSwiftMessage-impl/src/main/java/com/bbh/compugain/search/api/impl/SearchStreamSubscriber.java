/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package com.bbh.compugain.search.api.impl;

import akka.Done;
import akka.stream.javadsl.Flow;
import com.bbh.compugain.search.api.SearchEvent;
import com.bbh.compugain.search.api.SearchService;
import com.lightbend.lagom.javadsl.persistence.cassandra.CassandraSession;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

/**
 * This subscribes to the SearchService event stream.
 */
public class SearchStreamSubscriber {

  @Inject
  public SearchStreamSubscriber(SearchService searchService,  CassandraSession db) {
    // Create a subscriber
      searchService.searchEvent().subscribe()
      // And subscribe to it with at least once processing semantics.
      .atLeastOnce(
        // Create a flow that emits a Done for each message it processes
        Flow.<SearchEvent>create().mapAsync(1, event -> {

          if (event instanceof SearchEvent.SearchQueryEvent) {
              SearchEvent.SearchQueryEvent searchQuery = (SearchEvent.SearchQueryEvent) event;

              CompletionStage<List<BBH540Message>> results = db.selectAll("SELECT * FROM bbh_swift_mt_540_messages_details")
                      .thenApply(rows -> {
                          return rows.stream().filter(r -> r.getLong("swift_message_date") >= searchQuery.getSearchMessage().getSWIFT_MESSAGE_FROM_DATE() && r.getLong("swift_message_date") <= searchQuery.getSearchMessage().getSWIFT_MESSAGE_TO_DATE())
                                  .map(r-> new BBH540Message(r.getLong("swift_message_date"),r.getUUID("swift_message_id").toString(),r.getString("applicationid"),r.getString("banking_priority"),r.getString("block_1_header_block"),r.getString("block_1_identifier"),r.getString("block_2_application_header"),r.getString("block_2_identifier"),r.getString("block_3_identifier"),r.getString("block_3_user_header"),r.getString("block_4_identifier"),r.getString("block_5_identifier"),r.getString("buyr_95p"),r.getString("create_db_datetime"),r.getString("create_db_user"),r.getString("currency_11a"),r.getString("dateofissue_98a"),r.getString("deag_95p"),r.getString("deliverymonitoring"),r.getString("etprty_pset_16s"),r.getString("fiac_16r"),r.getString("fiac_16s"),r.getString("fiac_36b"),r.getString("fiac_97a"),r.getString("genl_16r"),r.getString("genl_16s"),r.getString("input_output_identifier"),r.getString("instrumenttype_12a"),r.getString("isin_35b"),r.getString("logicalterminal"),r.getLong("message_datetime"),r.getString("message_user_preference"),r.getString("messagepriority"),r.getString("messagetype"),r.getString("newg_23g"),r.getString("obsolescenceperiod"),r.getString("pset_95p"),r.getString("reag_95p"),r.getString("receiveraddress"),r.getString("reciever_id"),r.getString("reciever_name"),r.getString("recu_95p"),r.getString("second_element"),r.getString("seller_95p"),r.getString("seller_95r"),r.getString("seme_20c"),r.getString("send_recieve_status"),r.getString("sender_id"),r.getString("sender_name"),r.getString("sequencenumber"),r.getString("serviceid"),r.getString("sessionnumber"),r.getString("setdet_16r"),r.getString("setdet_16s"),r.getString("setprty_buyr_16r"),r.getString("setprty_buyr_16s"),r.getString("setprty_delivery_agent_16r"),r.getString("setprty_delivery_agent_16s"),r.getString("setprty_pset_16r"),r.getString("setprty_recieving_agent_16r"),r.getString("setprty_recieving_agent_16s"),r.getString("setprty_recieving_custd_16r"),r.getString("setprty_recieving_custd_16s"),r.getString("setprty_seller_16r"),r.getString("setprty_seller_16s"),r.getString("setr_22f"),r.getString("sett_98a"),r.getString("settlementamount_36b"),r.getString("third_element"),r.getString("trad_98a"),r.getString("traddet_16r"),r.getString("traddet_16s"),r.getBool("validation_status"),r.getString("cola_22f"),r.getString("matu_98a"), r.getString("SWIFT_MESSAGE"))).collect(Collectors.toList());
                      });
              results.thenApply(r->{
                  BBHProducer bbhProducer = new BBHProducer();
                  bbhProducer.sendMessage(r);
                  return CompletableFuture.completedFuture(Done.getInstance());
              });
              return CompletableFuture.completedFuture(Done.getInstance());
          } else {
            // Ignore all other events
            return CompletableFuture.completedFuture(Done.getInstance());
          }
        })
      );

  }
}
