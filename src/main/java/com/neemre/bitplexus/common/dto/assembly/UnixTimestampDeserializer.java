package com.neemre.bitplexus.common.dto.assembly;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.neemre.bitplexus.common.util.DateUtils;

public class UnixTimestampDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException, 
			JsonProcessingException {
		String unixTimestamp = parser.getText().trim();
		return DateUtils.toDate(unixTimestamp);
	}
}