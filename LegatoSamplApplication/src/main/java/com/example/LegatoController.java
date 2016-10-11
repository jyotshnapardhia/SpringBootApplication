package com.example;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgtbsample.data.PopulationData;
import com.services.PopulationService;

@RestController
public class LegatoController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    PopulationService censusService;

    @RequestMapping(value = "/population")
    public PopulationData getPopultion(
	    @RequestParam(value = "code", defaultValue = "12345") String code) {

	System.out.println("Test value is::" + code);
	PopulationData populationData = new PopulationData();
	populationData.setCountry("India");
	populationData.setDistrict("Ranga Reddy");
	populationData.setState("Telangana");
	populationData.setPopulation("11111");
	return populationData;
	// return lGTSCensusService.getCensus(code);
    }

    @RequestMapping(value = "/populationList")
    public List<PopulationData> getPopulationList(@RequestParam String groupBy,
	    @RequestParam String accumulation) {

	System.out.println("Group By:::" + groupBy);
	System.out.println("accumulation:::" + accumulation);
	String fileName = "E:/CSV/testData.csv";
	System.out.println("Hii!" + censusService.getTest());

	List<PopulationData> list = censusService.getPopulation(fileName,
		groupBy);
	System.out.println("list.size:::::====>>>>" + list.size());
	return list;
    }

    public PopulationService getCensusService() {
	return censusService;
    }

    public void setCensusService(PopulationService censusService) {
	this.censusService = censusService;
    }

}