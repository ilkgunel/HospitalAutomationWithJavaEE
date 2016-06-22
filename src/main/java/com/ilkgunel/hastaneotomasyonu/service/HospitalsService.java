/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.service;

import com.ilkgunel.hastaneotomasyonu.entity.Hastaneler;
import com.ilkgunel.hastaneotomasyonu.entity.Ilceler;
import com.ilkgunel.hastaneotomasyonu.facade.HospitalFacade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 010533
 */
public class HospitalsService {
    @Autowired
    private DistrictService districtService;
    
    @Autowired
    private HospitalFacade hospitalFacade;
    
    private List<String> hospitalNames;
    private List<Hastaneler> hospitalResults;
    
    public List<String> getAllHospitalNames(String currentDistrict) throws Exception{
        System.out.println("Güncel İlçe Adı:"+currentDistrict);
        int districtId=0;
        hospitalNames = new ArrayList<>();
        
        for(Ilceler i:districtService.getDistrictResults())
        {
            if(currentDistrict.equals(i.getIlce()))
            {
                districtId = i.getId();
                break;
            }
        }
        System.out.println("Güncel İlçe ID'si:"+districtId);
        Map parameters = new HashMap();
        parameters.put("ilceid", districtId);
        hospitalResults = hospitalFacade.findListByNamedQuery("Hastaneler.findByIlceid",parameters);
        
        if(hospitalResults != null){
            System.out.println("---hospitalresults is not null!!---\n");
        }
        
        for(Hastaneler h:hospitalResults)
        {
            hospitalNames.add(h.getHastaneadi());
        }
        
        return hospitalNames;
    }

    public List<Hastaneler> getHospitalResults() {
        return hospitalResults;
    }

    public List<String> getHospitalNames() {
        return hospitalNames;
    }
}
