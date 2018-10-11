package com.apap.tutorial5.controller;

import java.util.List;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.PilotService;
import com.apap.tutorial5.service.FlightService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;

	@RequestMapping("/")
	private String home() {
		return "home";
	}

	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}

	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	private String viewPilot(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		model.addAttribute("pilot", pilot);
		return "view-pilot";
	}
	
//	@RequestMapping(value = "/pilot/view")
//	private String viewPilot(@RequestParam("licenseNumber") String licenseNumber, Model model) {
//		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
//		if (archive == null) {
//			return "view-error";
//		}
//		else {
//			model.addAttribute("pilot", archive);
//			return "view-pilot";
//		}
//	}
	
	@RequestMapping(value = "/pilot/update/{id}", method = RequestMethod.GET)
	private String update(@PathVariable Long id, Model model) {
		PilotModel archive = pilotService.getPilotDetailById(id);
		model.addAttribute("pilot", archive);
		return "update-pilot";
	}

	@RequestMapping(value = "/pilot/update/{id}", method = RequestMethod.POST)
	private String updateSubmit(@PathVariable Long id, @ModelAttribute PilotModel pilot) {
		pilot.setId(id);
		pilotService.updatePilot(pilot);
		return "updated";
	}
	
	@RequestMapping(value= "/pilot/delete/{id}")
	private String delete(@PathVariable Long id) {
		pilotService.removePilot(id);
		return "deleted";
	}
}
