package com.lambdaschool.javacountries;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/population")
public class PopulationController
{

    // localhost:2020/population/size/{people}
    @GetMapping(value = "/size/{people}",
                    produces = "application/json")
    public ResponseEntity<?> getCountriesByNameLength(@PathVariable
                                                                  long people)
    {
        ArrayList<Country> rtnPop =
                    JavacountriesApplication.ourCountryList.findCountries(c -> c.getPopulation() >= people);
        rtnPop.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnPop, HttpStatus.OK);
    }


    // localhost:2020/population/max
    @GetMapping(value = "/max",
                    produces = "application/json")
    public ResponseEntity<?> findLargestPopulation()
    {
        ArrayList<Country> rtnPop = JavacountriesApplication.ourCountryList.countryList;

        rtnPop.sort((c1,c2)->(int)(c2.getPopulation() - c1.getPopulation()));

        return new ResponseEntity<>(rtnPop.get(0), HttpStatus.OK);
    }

    // localhost:2020/population/min
    @GetMapping(value = "/min",
                    produces = "application/json")
    public ResponseEntity<?> findLowestPopulation()
    {
        ArrayList<Country> rtnPop = JavacountriesApplication.ourCountryList.countryList;

        rtnPop.sort((c1,c2)->(int)(c1.getPopulation() - c2.getPopulation()));

        return new ResponseEntity<>(rtnPop.get(0), HttpStatus.OK);
    }
    }

