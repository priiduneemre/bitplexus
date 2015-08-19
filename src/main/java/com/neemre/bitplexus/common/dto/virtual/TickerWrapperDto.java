package com.neemre.bitplexus.common.dto.virtual;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neemre.bitplexus.common.dto.assembly.UnixTimestampDeserializer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerWrapperDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private TickerDto ticker;
	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonProperty("timestamp")
	private Date updatedAt;
	@JsonProperty("success")
	private Boolean isSuccess;
	private String error;
}