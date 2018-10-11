package com.apap.tutorial5.service;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.repository.PilotDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	@Autowired
	private PilotDb pilotDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}
	
	@Override
	public void addPilot(PilotModel pilot){
		pilotDb.save(pilot);
	}
	
	@Override
	public void removePilot (long pilot) {
		pilotDb.deleteById(pilot);
	}
	
	@Override
	public PilotModel getPilotDetailById(Long id) {
		return pilotDb.getOne(id);
	}
	
	@Override
	public void updatePilot(PilotModel pilotModel) {
	    PilotModel pilot = pilotDb.getOne(pilotModel.getId());
	    pilot.setName(pilotModel.getName());
	    pilot.setFlyHour(pilotModel.getFlyHour());
	    pilotDb.save(pilot);
	}
}
