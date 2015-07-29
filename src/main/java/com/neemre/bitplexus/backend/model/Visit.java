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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
@Table(name = "visit", schema = "public")
@SequenceGenerator(name = "seq_visit_id", sequenceName = "seq_visit_visit_id", allocationSize = 1)
public class Visit extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_visit_id")
	@Column(name = "visit_id", insertable = false, updatable = false)
	private Long visitId;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "member_id", updatable = false)
	private Member member;
	@NotNull 
	@Size(min = 7, max = 45)
	@Pattern(regexp = "^[0-9a-f:.]*$")
	@Column(name = "ip_address", updatable = false)
	private String ipAddress;
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "login_at", insertable = false, updatable = false)
	private Date loginAt;
}