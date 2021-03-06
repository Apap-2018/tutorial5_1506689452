package com.apap.tutorial5.service;

import java.util.List;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.repository.FlightDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FlightServiceImpl implements FlightService{
	@Autowired
	private FlightDb flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
			flightDb.save(flight);
	}
	
	public List<FlightModel> getAllFlight() {
		return flightDb.findAll();
	}
	
	@Override
	public FlightModel flightById(Long id) {
		return flightDb.getOne(id);
	}

	@Override
	public void updateFlight(FlightModel flightModel) {
	    FlightModel flight = flightDb.getOne(flightModel.getId());
	    flight.setDestination(flightModel.getDestination());
	    flight.setFlightNumber(flightModel.getFlightNumber());
	    flight.setOrigin(flightModel.getOrigin());
	    flight.setTime(flightModel.getTime());
	    flightDb.save(flight);
	}
	
	@Override
	public void removeFlight(Long id) {
		flightDb.deleteById(id);
	}
	
//	@Override
//	public List<FlightModel> getFlightDetailByPilot(String licenseNumber) {
//		return flightDb.findByPilotLicenseNumber(licenseNumber);
//	}
}
