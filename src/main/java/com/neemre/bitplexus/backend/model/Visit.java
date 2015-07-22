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
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_visit_id")
	@Column(name = "visit_id", insertable = false, updatable = false)
	private Long visitId;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "member_id", updatable = false)
	private Member member;
	@Column(name = "ip_address", updatable = false)
	private String ipAddress;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "login_at", insertable = false, updatable = false)
	private Date loginAt;
}