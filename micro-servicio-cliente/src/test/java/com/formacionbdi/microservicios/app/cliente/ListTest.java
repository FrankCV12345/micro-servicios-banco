package com.formacionbdi.microservicios.app.cliente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

class ListTest {

	@Test
	void letsMockListSizeMethod() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		assertEquals(2,listMock.size());
	}
	@Test
	void letsMockListSize_returnMultipleValues() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2).thenReturn(3);
		assertEquals(2,listMock.size());
		assertEquals(2,listMock.size());
	}
	
	@Test
	void letsMockListGet() {
		List listMock = mock(List.class);
		when(listMock.get(0)).thenReturn(2);
		assertEquals(2,listMock.get(0));
	}

}
