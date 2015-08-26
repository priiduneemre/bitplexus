package com.neemre.bitplexus.common.dto.assembly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import lombok.Data;

import com.google.common.collect.Iterables;
import com.inspiresoftware.lib.dto.geda.DTOFactory;
import com.inspiresoftware.lib.dto.geda.assembler.DTOAssembler;
import com.neemre.bitplexus.common.Errors;

@Data
public class DtoAssemblerImpl implements DtoAssembler {

	private DTOFactory beanFactory;


	@Override
	public <T, S> S assemble(T entity, Class<T> entityClazz, Class<S> dtoClazz) {
		try {
			return Iterables.getOnlyElement(assemble(new ArrayList<T>(Arrays.asList(entity)),
					entityClazz, dtoClazz));
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public <T, S> List<S> assemble(List<T> entities, Class<T> entityClazz, Class<S> dtoClazz) {
		if ((entityClazz == null) || (dtoClazz == null)) {
			throw new IllegalArgumentException(Errors.TODO.getDescription());
		}
		if (entities == null) {
			entities = new ArrayList<T>();
		}
		List<S> dtos = new ArrayList<S>();
		entities.removeAll(Collections.singleton(null));
		DTOAssembler.newAssembler(dtoClazz, entityClazz).assembleDtos(dtos, entities, null, 
				beanFactory);
		return dtos;
	}

	@Override
	public <T, S> T disassemble(S dto, Class<S> dtoClazz, Class<T> entityClazz) {
		try {
			return Iterables.getOnlyElement(disassemble(new ArrayList<S>(Arrays.asList(dto)),
					dtoClazz, entityClazz));
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public <T, S> List<T> disassemble(List<S> dtos, Class<S> dtoClazz, Class<T> entityClazz) {
		if ((dtoClazz == null) || (entityClazz == null)) {
			throw new IllegalArgumentException(Errors.TODO.getDescription());
		}
		if (dtos == null) {
			dtos = new ArrayList<S>();
		}
		List<T> entities = new ArrayList<T>();
		dtos.removeAll(Collections.singleton(null));
		DTOAssembler.newAssembler(dtoClazz, entityClazz).assembleEntities(dtos, entities, null, 
				beanFactory);
		return entities;
	}
}