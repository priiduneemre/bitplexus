package com.neemre.bitplexus.common.dto.virtual;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	@NotNull
	@Size(max = 8)
	@JsonProperty("base")
	private String source;
	@NotNull
	@Size(max = 8)
	private String target;
	@NotNull
	@Digits(integer = 15, fraction = 8)
	@DecimalMin(value = "0", inclusive = false)
	private BigDecimal price;
	@NotNull
	@Digits(integer = 15, fraction = 8)
	@DecimalMin("0")
	private BigDecimal volume;
	@NotNull
	@Digits(integer = 15, fraction = 8)
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