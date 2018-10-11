package com.apap.tutorial5.service;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;

public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	PilotModel getPilotDetailById(Long id);
	void addPilot(PilotModel pilot);
	void removePilot(long pilot);
	void updatePilot(PilotModel pilotModel);
}

