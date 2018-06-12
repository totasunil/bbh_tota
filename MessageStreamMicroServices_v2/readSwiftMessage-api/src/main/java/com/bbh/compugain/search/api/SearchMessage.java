package com.bbh.compugain.search.api;

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
public final class SearchMessage {
    String SWIFT_MESSAGE_TYPE;
    Long SWIFT_MESSAGE_FROM_DATE;
    Long SWIFT_MESSAGE_TO_DATE;
}
