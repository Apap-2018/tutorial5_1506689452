package com.apap.tutorial5.service;

import java.util.List;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;

public interface FlightService {
	void addFlight(FlightModel flight);
	void updateFlight(FlightModel flightModel);
	void removeFlight(Long id);
	FlightModel flightById(Long id);
	List<FlightModel> getAllFlight();
}
