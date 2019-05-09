package com.previred.restGDD.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.previred.restGDD.models.GddRequest;
import com.previred.restGDD.models.GddResponse;

@Service
public class GddServiceImpl implements GddService {
	
	@Override
	public GddResponse getPeriodos(GddRequest request){
		
		GddResponse response = new GddResponse();
		Random random = new Random();
		List<String> fechasFaltantes = new ArrayList<String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar;
		Date fechaCreacion = null;
		Date fechaFin = null;
		String fechaCompara;
		
		//Se asignan los datos principales del request hacia el response
		response.setId(request.getId());
		response.setFechaCreacion(request.getFechaCreacion());
		response.setFechaFin(request.getFechaFin());
		
		try {
			fechaCreacion = format.parse(request.getFechaCreacion());
			fechaFin = format.parse(request.getFechaFin());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//Avanza desde la fecha inicial hasta la fecha final
		while(!fechaCreacion.equals(fechaFin)) {
			
			calendar = Calendar.getInstance();
			calendar.setTime(fechaCreacion);
			calendar.add(Calendar.MONTH, 1);
			fechaCreacion = calendar.getTime();
			fechaCompara = format.format(calendar.getTime());
			
			//Valida si la fecha se encuentra en la lista de fechas de entrada
			if(!request.getFechas().contains(fechaCompara) 
					&& !fechaCompara.equals(request.getFechaFin())) {
				
				//Random True-False para evaluar si se registra la fecha
				if(random.nextBoolean()) fechasFaltantes.add(format.format(fechaCreacion).toString());
				
			}
			
			//Valida el límite de fechas permitidas para la respuesta
			if(fechasFaltantes.size() == 100) fechaCreacion = fechaFin;
			
		}
		
		response.setFechasFaltantes(fechasFaltantes);
		
		return response;
		
	}
	
}
