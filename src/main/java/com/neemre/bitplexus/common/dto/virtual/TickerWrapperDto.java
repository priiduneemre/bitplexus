package com.neemre.bitplexus.common.dto.virtual;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

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

	@NotNull
	private TickerDto ticker;
	@NotNull
	@Past
	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonProperty("timestamp")
	private Date updatedAt;
	@NotNull
	@JsonProperty("success")
	private Boolean isSuccess;
	@NotNull
	private String error;
}