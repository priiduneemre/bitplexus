package com.neemre.bitplexus.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@SequenceGenerator(name = "seq_visit_visit_id", sequenceName = "seq_visit_visit_id", 
		allocationSize = 1)
public class Visit extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_visit_visit_id")
	@Column(name = "visit_id")
	private Long visitId;
	private Member member;
	@Column(name = "ip_address")
	private String ipAddress;
	@Column(name = "login_at")
	private Date loginAt;
}