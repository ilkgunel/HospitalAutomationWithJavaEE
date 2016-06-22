/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.service;

import com.ilkgunel.hastaneotomasyonu.entity.Ilceler;
import com.ilkgunel.hastaneotomasyonu.entity.Iller;
import com.ilkgunel.hastaneotomasyonu.facade.DistrictsFacade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author İlkay Günel
 */
public class DistrictService {
    private List<Ilceler> districtResults;
    private List<String> districtNames;
    
    @Autowired
    private DistrictsFacade districtsFacade;
    
    @Autowired
    private CitiesService citiesService;
    
    public List<String> getAllDistrictNames(String currentCity) throws Exception{
        int cityId=0;
        for(Iller i:citiesService.getAllResults())
        {
            if(i.getSehir().equals(currentCity))
            {
                cityId=i.getId();
                break;
            }
        }
        Map parameters = new HashMap();
        parameters.put("sehir", cityId);
        districtResults=districtsFacade.findListByNamedQuery("Ilceler.findBySehir", parameters);
        
        districtNames=new ArrayList<>();
        
        for(Ilceler i:districtResults)
        {
            districtNames.add(i.getIlce());
        }
        
        return districtNames;
    }

    public List<Ilceler> getDistrictResults() {
        return districtResults;
    }

    public List<String> getDistrictNames() {
        return districtNames;
    }
    
}
