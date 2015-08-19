package com.neemre.bitplexus.common.dto.virtual;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.neemre.bitplexus.common.Defaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("base")
	private String source;
	private String target;
	private BigDecimal price;
	private BigDecimal volume;
	@JsonProperty("change")
	private BigDecimal priceChange;

	public void setPrice(BigDecimal price) {
		this.price = price.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}

	public void setPriceChange(BigDecimal priceChange) {
		this.priceChange = priceChange.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}
}