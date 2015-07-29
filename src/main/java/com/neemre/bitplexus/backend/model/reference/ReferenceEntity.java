package com.neemre.bitplexus.backend.model.reference;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.neemre.bitplexus.backend.model.BaseEntity;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class ReferenceEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
}