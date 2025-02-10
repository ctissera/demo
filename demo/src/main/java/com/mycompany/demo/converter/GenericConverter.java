package com.mycompany.demo.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericConverter<A, B> {

	public abstract A toDto(B entity) throws IOException;
	
	public List<A> toDtoList(List<B> entities) throws IOException {
		List<A> result = new ArrayList<>();
		for (B b : entities) {
			result.add(toDto(b));
		}
		return result;
	}
	
	
	public abstract B toEntity(A dto) throws Exception;

}
