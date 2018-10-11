package com.apap.tutorial5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
//	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
//	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
//		FlightModel flight = new FlightModel();
//		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
//		flight.setPilotFlight(pilot);
//		
//		model.addAttribute("flight", flight);
//		return "addFlight";
//	}
//	
//	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
//	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
//		flightService.addFlight(flight);
//		return "add";
//	}
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable (value = "licenseNumber") String licenseNumber, Model model) {
		//FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		pilot.setPilotFlight(new ArrayList<FlightModel>());
		pilot.getPilotFlight().add(new FlightModel());
		
		model.addAttribute("pilot", pilot);
		return "addFlight";
		
	}
	
	@RequestMapping(value="/flight/add/{licenseNumber}", method = RequestMethod.POST, params= {"submit"})
	private String addFlightSubmit(@ModelAttribute PilotModel pilot) {
		PilotModel thePilot = pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber());
		for (FlightModel flight : pilot.getPilotFlight()) {
			flight.setPilot(thePilot);
			flightService.addFlight(flight);
		}
		return "add";
		
	}
	
	@RequestMapping(value="/flight/add/{licenseNumber}", params={"addRow"})
	public String addRow(final PilotModel pilot, final BindingResult bindingResult, Model model) {
	    pilot.getPilotFlight().add(new FlightModel());
	    
	    model.addAttribute("pilot", pilot);
	    return "addFlight";
	}

	@RequestMapping(value="/flight/add/{licenseNumber}", params={"removeRow"})
	public String removeRow(
	        final PilotModel pilot, final BindingResult bindingResult, 
	        final HttpServletRequest req, Model model) {
	    final int rowId = Integer.valueOf(req.getParameter("removeRow"));
	    pilot.getPilotFlight().remove(rowId);
	    model.addAttribute("pilot",pilot);
	    return "addFlight";
	}
	
	@RequestMapping(value= "/flight/viewall")
    private String viewAll(Model model) {
    	List<FlightModel> archive = flightService.getAllFlight();
    	model.addAttribute("flight", archive);
    	return "viewall-flight";
    }
	
	@RequestMapping(value = "/flight/update/{id}", method = RequestMethod.GET)
    private String update(@PathVariable Long id, Model model) {
        FlightModel archive = flightService.flightById(id);
        model.addAttribute("flight", archive);
        return "update-flight";
    }

    @RequestMapping(value = "/flight/update/{id}", method = RequestMethod.POST)
    private String updateSubmit(@PathVariable Long id, @ModelAttribute FlightModel flight) {
        flight.setId(id);
        flightService.updateFlight(flight);
        return "updated";
    }
    
    @RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
    private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
    	for(FlightModel flight : pilot.getPilotFlight()) {
    		flightService.removeFlight(flight.getId());
    	}
    	return "deleted";
    }
    
    @RequestMapping(value= "/flight/delete/{id}")
    private String delete(@PathVariable Long id) {
    	flightService.removeFlight(id);
    	return "deleted";
    }
}
