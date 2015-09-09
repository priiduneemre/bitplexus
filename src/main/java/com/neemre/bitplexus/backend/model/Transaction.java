package com.neemre.bitplexus.backend.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.neemre.bitplexus.backend.model.reference.TransactionStatusType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"transactionEndpoints"})
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "transaction", schema = "public")
@SequenceGenerator(name = "seq_transaction_id", sequenceName = "seq_transaction_transaction_id", 
		allocationSize = 1)
@NamedStoredProcedureQueries(value = {
@NamedStoredProcedureQuery(name = "estimateFeeByHexTxnAndFeeCoefficient",
		procedureName = "f_estimate_transaction_fee", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_currency_name", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_hex_transaction", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_fee_coefficient", type = BigDecimal.class)}),
@NamedStoredProcedureQuery(name = "updateTransactionStatusTypesToCompleted", 
		procedureName = "f_complete_transactions", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_confirmation_count", type = Short.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_block_height", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_block_time", type = Timestamp.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_chain_code", type = String.class)}),
@NamedStoredProcedureQuery(name = "updateTransactionStatusTypesToConfirmed", 
		procedureName = "f_confirm_transactions", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_network_uids_csv", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_block_height", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_block_time", type = Timestamp.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_chain_code", type = String.class)}),
@NamedStoredProcedureQuery(name = "updateTransactionStatusTypesToDropped", 
		procedureName = "f_drop_transactions", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_txn_timeout", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "in_chain_code", type = String.class)})
})
public class Transaction extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transaction_id")
	@Column(name = "transaction_id", insertable = false, updatable = false)
	private Long transactionId;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "transaction_status_type_id")
	private TransactionStatusType transactionStatusType;
	@Size(min = 36, max = 36)
	@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$")
	@Generated(GenerationTime.INSERT)
	@Column(name = "local_uid", insertable = false, updatable = false)
	private String localUid;
	@NotNull
	@Size(min = 64, max = 64)
	@Pattern(regexp = "^[0-9a-f]*$")
	@Column(name = "network_uid", updatable = false)
	private String networkUid;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "received_at")
	private Date receivedAt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "confirmed_at")
	private Date confirmedAt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "completed_at")
	private Date completedAt;
	@Min(0)
	@Column(name = "block_height")
	private Integer blockHeight;
	@NotNull
	@Min(1)
	@Max(1000000)
	@Column(name = "binary_size", updatable = false)
	private Integer binarySize;
	@NotNull
	@Digits(integer = 15, fraction = 8)
	@DecimalMin("0")
	@Column(name = "fee", updatable = false)
	private BigDecimal fee;
	@NotNull
	@Digits(integer = 15, fraction = 8)
	@DecimalMin(value = "0", inclusive = false)
	@Column(name = "unit_price", updatable = false)
	private BigDecimal unitPrice;
	@Size(max = 255)
	@Column(name = "note", updatable = false)
	private String note;
	@Past
	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "logged_at", insertable = false, updatable = false)
	private Date loggedAt;
	@Past
	@Generated(GenerationTime.ALWAYS)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Date updatedAt;
	
	@Setter(AccessLevel.NONE)
	@OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "transaction")
	@OrderBy("transactionEndpointType")
	private List<TransactionEndpoint> transactionEndpoints = new ArrayList<TransactionEndpoint>();
	
	
	public List<TransactionEndpoint> getTransactionEndpoints() {
		return Collections.unmodifiableList(transactionEndpoints);
	}
	
	public void addTransactionEndpoint(TransactionEndpoint transactionEndpoint) {
		if (transactionEndpoint != null) {
			if (!transactionEndpoints.contains(transactionEndpoint)) {
				transactionEndpoints.add(transactionEndpoint);
				Collections.sort(transactionEndpoints, TransactionEndpoint.NATURAL_ORDERING);
				transactionEndpoint.setTransaction(this);
			}
		}
	}

	public void removeTransactionEndpoint(TransactionEndpoint transactionEndpoint) {
		if (transactionEndpoint != null) {
			if (transactionEndpoints.contains(transactionEndpoint)) {
				transactionEndpoints.remove(transactionEndpoint);
				transactionEndpoint.setTransaction(null);
			}
		}
	}
}