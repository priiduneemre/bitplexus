package com.neemre.bitplexus.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "address_type", schema = "public")
@SequenceGenerator(name = "seq_address_type_id", sequenceName = "seq_address_type_address_type_id",
		allocationSize = 1)
@NamedStoredProcedureQueries(value = {
@NamedStoredProcedureQuery(name = "findIdByAddressAndChainCode", 
		procedureName = "f_get_address_type_id", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_address", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_chain_code", type = String.class)})
})
public class AddressType extends BaseEntity {

	public static final Ordering<AddressType> LEADING_SYMBOL_ORDERING = Ordering.natural().nullsLast()
			.onResultOf(new LeadingSymbolExtractor());
	public static final Ordering<AddressType> NAME_ORDERING = Ordering.natural().nullsLast()
			.onResultOf(new NameExtractor());
	public static final Ordering<AddressType> NATURAL_ORDERING = NAME_ORDERING
			.compound(LEADING_SYMBOL_ORDERING);
	private static final long serialVersionUID = 1L;

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address_type_id")
	@Column(name = "address_type_id", insertable = false, updatable = false)
	private Short addressTypeId;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "chain_id", updatable = false)
	private Chain chain;
	@NotNull
	@Size(max = 30)
	@Column(name = "code")
	private String code;
	@NotNull
	@Size(max = 60)
	@Column(name = "name")
	private String name;
	@NotNull
	@Size(min = 1, max = 1)
	@Pattern(regexp = "^[1-9a-km-zA-HJ-NP-Z]*$")
	@Column(name = "leading_symbol", updatable = false)
	private String leadingSymbol;
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
	

	public void setChain(Chain chain) {
		if (this.chain != chain) {
			if (this.chain != null) {
				this.chain.removeAddressType(this);
			}
			this.chain = chain;
			if (chain != null) {
				chain.addAddressType(this);
			} 
		} 
	}
	
	private static class LeadingSymbolExtractor implements Function<AddressType, String> {

		@Override
		public String apply(AddressType addressType) {
			return addressType.getLeadingSymbol();
		}
	}

	private static class NameExtractor implements Function<AddressType, String> {

		@Override
		public String apply(AddressType addressType) {
			return addressType.getName();
		}
	}
}