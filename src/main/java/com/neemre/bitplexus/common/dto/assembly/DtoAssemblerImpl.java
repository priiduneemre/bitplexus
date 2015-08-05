package com.neemre.bitplexus.common.dto.assembly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

import com.google.common.collect.Iterables;
import com.inspiresoftware.lib.dto.geda.DTOFactory;
import com.inspiresoftware.lib.dto.geda.assembler.DTOAssembler;

@Data
public class DtoAssemblerImpl implements DtoAssembler {
	
	private DTOFactory beanFactory;

	
	@Override
	public <T, S> S assemble(T entity, Class<T> entityClazz, Class<S> dtoClazz) {
		S dto = Iterables.getOnlyElement(assemble(Arrays.asList(entity), entityClazz, dtoClazz));
		return dto;
	}
	
	@Override
	public <T, S> List<S> assemble(List<T> entities, Class<T> entityClazz, Class<S> dtoClazz) {
		List<S> dtos = new ArrayList<S>();
		DTOAssembler.newAssembler(dtoClazz, entityClazz).assembleDtos(dtos, entities, null, 
				beanFactory);
		return dtos;
	}
	
	@Override
	public <T, S> T disassemble(S dto, Class<S> dtoClazz, Class<T> entityClazz) {
		T entity = Iterables.getOnlyElement(disassemble(Arrays.asList(dto), dtoClazz, entityClazz));
		return entity;
	}

	@Override
	public <T, S> List<T> disassemble(List<S> dtos, Class<S> dtoClazz, Class<T> entityClazz) {
		List<T> entities = new ArrayList<T>();
		DTOAssembler.newAssembler(dtoClazz, entityClazz).assembleEntities(dtos, entities, null, 
				beanFactory);
		return entities;
	}
}