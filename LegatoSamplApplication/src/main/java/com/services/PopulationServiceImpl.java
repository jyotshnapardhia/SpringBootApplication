package com.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lgtbsample.data.PopulationData;
import com.lgtsample.util.LGTCSVReader;

@Service
public class PopulationServiceImpl implements PopulationService {
    
    @Resource
    LGTCSVReader lGTCSVReader;
    
    @Override
    public List<PopulationData> getPopulation(String fileName,String groupBy){
	System.out.println("Inside getPopulation service ===============>>>>>>>>>>"+fileName);
	List<PopulationData> populationData = lGTCSVReader.readDataFromCSV(fileName);
	System.out.println("Size::::"+populationData.size());
	
	List<List<PopulationData>> dataGroupedByMap = groupByCountryType(populationData,groupBy);
	System.out.println("dataGroupedByMap size======>>>>>>"+dataGroupedByMap.size());
	//System.out.println("::::::"+dataGroupedByMap.size());
	List<PopulationData> updatedList = new ArrayList<PopulationData>();
		
	for(int i=0;i<dataGroupedByMap.size();i++){
	    updatedList.add(dataGroupedByMap.get(i).get(0));
	}
	return updatedList;
    }
    
   
    
    public List<List<PopulationData>> groupByCountryType(List<PopulationData> list,String groupBy) {
	Map<String,List<PopulationData>> groupByObject = new HashMap<>();
	
	
	if(groupBy.equalsIgnoreCase("country")) {
	    System.out.println("groupByCountry.B..."+groupBy);
	    groupByObject = list.stream() .collect(Collectors.groupingBy(PopulationData::getCountry));
	    System.out.println("groupByCountry...."+groupBy);
	}
	else if(groupBy.equalsIgnoreCase("state")) {
	    System.out.println("groupByState..B.."+groupBy);
	    groupByObject = list.stream() .collect(Collectors.groupingBy(PopulationData::getState));
	    System.out.println("groupByState...."+groupBy);
	}
	else if(groupBy.equalsIgnoreCase("district")) {
	    System.out.println("groupByDistrict..B.."+groupBy);
	    groupByObject = list.stream() .collect(Collectors.groupingBy(PopulationData::getDistrict));
	    System.out.println("groupByDistrict...."+groupBy);
	}
	
	//personBySate = list.stream() .collect(Collectors.groupingBy(PopulationData::getState));
	//System.out.println("Person grouped by cities in Java 8: " + personBySate);
	//List<PopulationData> list = personBySate.values();
	List<List<PopulationData>> values = groupByObject.values().stream().collect(Collectors.toList());
	//System.out.println("List:::"+values.get(3).get(0).getPopulation());
	//List<PopulationData> list = new ArrayList<PopulationData>(personBySate.values());
	return values;
	
	}

    @Override
    public String getTest(){
	return "Hello!!!!!!!";
    }
}
