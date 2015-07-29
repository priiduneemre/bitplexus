package com.neemre.bitplexus.backend.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

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
@ToString(callSuper = true, exclude = {"wallets"})
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "customer", schema = "public")
@PrimaryKeyJoinColumn(name = "customer_id")
public class Customer extends Person {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name = "customer_id", insertable = false, updatable = false)
	private Integer customerId;
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", insertable = false, updatable = false)
	private Date createdAt;
	
	@Setter(AccessLevel.NONE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	@OrderBy("walletStateType, name")
	private List<Wallet> wallets = new ArrayList<Wallet>();
	
	
	public List<Wallet> getWallets() {
		return Collections.unmodifiableList(wallets);
	}
	
	public void addWallet(Wallet wallet) {
		if (wallet != null) {
			if (!wallets.contains(wallet)) {
				wallets.add(wallet);
				Collections.sort(wallets, Wallet.NATURAL_ORDERING);
				wallet.setCustomer(this);
			}
		}
	}

	public void removeWallet(Wallet wallet) {
		if (wallet != null) {
			if (wallets.contains(wallet)) {
				wallets.remove(wallet);
				wallet.setCustomer(null);
			}
		}
	}
}