package com.neemre.bitplexus.common.dto.assembly;

import java.util.List;

public interface DtoAssembler {

	<T, S> S assemble(T entity, Class<T> entityClazz, Class<S> dtoClazz);
	
	<T, S> List<S> assemble(List<T> entities, Class<T> entityClazz, Class<S> dtoClazz);

	<T, S> T disassemble(S dto, Class<S> dtoClazz, Class<T> entityClazz);

	<T, S> List<T> disassemble(List<S> dtos, Class<S> dtoClazz, Class<T> entityClazz);
}