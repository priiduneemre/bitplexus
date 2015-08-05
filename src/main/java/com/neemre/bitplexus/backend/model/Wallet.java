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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.neemre.bitplexus.backend.model.reference.WalletStateType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "wallet", schema = "public")
@SequenceGenerator(name = "seq_wallet_id", sequenceName = "seq_wallet_wallet_id", allocationSize = 1)
public class Wallet extends BaseEntity {

	public static final Ordering<Wallet> NAME_ORDERING = Ordering.natural().nullsLast()
			.onResultOf(new NameExtractor());
	public static final Ordering<Wallet> WALLET_STATE_TYPE_ORDERING = Ordering.natural()
			.nullsLast().onResultOf(new WalletStateTypeExtractor());
	public static final Ordering<Wallet> NATURAL_ORDERING = WALLET_STATE_TYPE_ORDERING
			.compound(NAME_ORDERING);
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_wallet_id")
	@Column(name = "wallet_id", insertable = false, updatable = false)
	private Integer walletId;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id", updatable = false)
	private Customer customer;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "wallet_state_type_id", insertable = false)
	private WalletStateType walletStateType;
	@NotNull
	@Size(max = 50)
	@Column(name = "name")
	private String name;
	@Past
	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", insertable = false, updatable = false)
	private Date createdAt;
	@Past
	@Generated(GenerationTime.ALWAYS)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Date updatedAt;
	

	public void setCustomer(Customer customer) {
		if (this.customer != customer) {
			if (this.customer != null) {
				this.customer.removeWallet(this);
			}
			this.customer = customer;
			if (customer != null) {
				customer.addWallet(this);
			} 
		} 
	}

	private static class NameExtractor implements Function<Wallet, String> {

		@Override
		public String apply(Wallet wallet) {
			return wallet.getName();
		}
	}

	private static class WalletStateTypeExtractor implements Function<Wallet, Short> {

		@Override
		public Short apply(Wallet wallet) {
			if (wallet.getWalletStateType() != null) {
				return wallet.getWalletStateType().getWalletStateTypeId();
			}
			return null;
		}
	}
}