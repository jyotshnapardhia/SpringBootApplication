package com.lgtsample.util;

import java.util.List;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.lgtbsample.data.PopulationData;

@Service
public class LGTCSVReader {

    public List<PopulationData> readDataFromCSV(String fileName) {

	List<PopulationData> populationData = new ArrayList<>();
	Path pathToFile = Paths.get(fileName);

	try (BufferedReader br = Files.newBufferedReader(pathToFile,
		StandardCharsets.US_ASCII)) {
	    String line = br.readLine();
	    while (line != null) {
		String[] attributes = line.split(",");
		PopulationData censusData = createPopulationData(attributes);
		populationData.add(censusData);
		line = br.readLine();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return populationData;

    }

    private static PopulationData createPopulationData(String[] metadata) {
	PopulationData censusData = new PopulationData();
	censusData.setCountry(metadata[0]);
	censusData.setDistrict(metadata[1]);
	censusData.setState(metadata[2]);
	censusData.setPopulation(metadata[3]);
	return censusData;
    }

}
