/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.service;

import com.ilkgunel.hastaneotomasyonu.entity.Klinikyerleri;
import com.ilkgunel.hastaneotomasyonu.facade.ClinicPlacesFacade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 010533
 */
public class ClinicPlacesService {
    @Autowired
    private ClinicPlacesFacade clinicPlacesFacade;
    
    List<String> clinicPlaceNames;
    List<Klinikyerleri> clinicPlaces;
    
    public List<String> getAllClinicNames(int hospitalId) throws Exception{
        clinicPlaceNames = new ArrayList<>();
        Map map = new HashMap();
        map.put("hastaneid", hospitalId);
        clinicPlaces = clinicPlacesFacade.findListByNamedQuery("Klinikyerleri.findByHastaneid", map);
        for(Klinikyerleri c:clinicPlaces)
        {
            clinicPlaceNames.add(c.getKlinikyeri());
        }
        
        return clinicPlaceNames;
                
    }
}
