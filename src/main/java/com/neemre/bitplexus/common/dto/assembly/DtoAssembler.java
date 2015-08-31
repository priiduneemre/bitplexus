package com.neemre.bitplexus.common.dto.assembly;

import java.util.List;

public interface DtoAssembler {

	<T, S> S assemble(T entity, Class<T> entityClass, Class<S> dtoClass);
	
	<T, S> List<S> assemble(List<T> entities, Class<T> entityClass, Class<S> dtoClass);

	<T, S> T disassemble(S dto, Class<S> dtoClass, Class<T> entityClass);

	<T, S> List<T> disassemble(List<S> dtos, Class<S> dtoClass, Class<T> entityClass);
}