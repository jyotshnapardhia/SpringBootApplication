package com.services;

import java.util.List;

import com.lgtbsample.data.PopulationData;

public interface PopulationService {


    String getTest();

    List<PopulationData> getPopulation(String fileName, String groupBy);

}
