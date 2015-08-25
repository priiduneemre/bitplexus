package com.neemre.bitplexus.backend.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"addressTypes"})
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "chain", schema = "public")
@SequenceGenerator(name = "seq_chain_id", sequenceName = "seq_chain_chain_id", allocationSize = 1)
public class Chain extends BaseEntity {
	
	public static final Ordering<Chain> NAME_ORDERING = Ordering.natural().nullsLast()
			.onResultOf(new NameExtractor());
	public static final Ordering<Chain> OPERATIONALITY_ORDERING = Ordering.natural().reverse()
			.nullsLast().onResultOf(new OperationalityExtractor());
	public static final Ordering<Chain> NATURAL_ORDERING = OPERATIONALITY_ORDERING
			.compound(NAME_ORDERING);
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_chain_id")
	@Column(name = "chain_id", insertable = false, updatable = false)
	private Short chainId;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "currency_id", updatable = false)
	private Currency currency;
	@NotNull
	@Size(max = 30)
	@Column(name = "code")
	private String code;
	@NotNull
	@Size(max = 60)
	@Column(name = "name")
	private String name;
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "started_on", updatable = false)
	private Date startedOn;
	@NotNull
	@Digits(integer = 15, fraction = 8)
	@DecimalMin("0")
	@Column(name = "available_supply")
	private BigDecimal availableSupply;
	@NotNull
	@Column(name = "is_operational")
	private Boolean isOperational;
	@Past
	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", insertable = false, updatable = false)
	private Date createdAt;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "created_by", updatable = false)
	private Employee createdBy;
	@Past
	@Generated(GenerationTime.ALWAYS)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Date updatedAt;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "updated_by", insertable = false)
	private Employee updatedBy;
	
	@Setter(AccessLevel.NONE)
	@OneToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "chain")
	@OrderBy("name, leadingSymbol")
	private List<AddressType> addressTypes = new ArrayList<AddressType>();
	
	
	public void setCurrency(Currency currency) {
		if (this.currency != currency) {
			if (this.currency != null) {
				this.currency.removeChain(this);
			}
			this.currency = currency;
			if (currency != null) {
				currency.addChain(this);
			} 
		} 
	}
	
	public List<AddressType> getAddressTypes() {
		return Collections.unmodifiableList(addressTypes);
	}
	
	public void addAddressType(AddressType addressType) {
		if (addressType != null) {
			if (!addressTypes.contains(addressType)) {
				addressTypes.add(addressType);
				Collections.sort(addressTypes, AddressType.NATURAL_ORDERING);
				addressType.setChain(this);
			}
		}
	}

	public void removeAddressType(AddressType addressType) {
		if (addressType != null) {
			if (addressTypes.contains(addressType)) {
				addressTypes.remove(addressType);
				addressType.setChain(null);
			}
		}
	}
	
	private static class NameExtractor implements Function<Chain, String> {

		@Override
		public String apply(Chain chain) {
			return chain.getName();
		}
	}

	private static class OperationalityExtractor implements Function<Chain, Boolean> {

		@Override
		public Boolean apply(Chain chain) {
			return chain.getIsOperational();
		}
	}
}