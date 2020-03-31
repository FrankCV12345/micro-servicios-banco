package com.formacionbdi.microservicios.app.cliente.models.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UtilitarioRetrofit {
	private Retrofit retrofit =null;

	public UtilitarioRetrofit(String baseUrl ) {
		this.retrofit = new Retrofit
				.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}

	public Retrofit getRetrofit() {
		return retrofit;
	}

	
	
}
