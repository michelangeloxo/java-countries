package com.lambdaschool.javacountries;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/names")
public class CountryController
{
    // return the names of all the countries alphabetically
    // localhost:2020/names/all
    @GetMapping(value = "/all",
                produces = {"application/json"})
    public ResponseEntity<?> getAllCountries()
    {
        JavacountriesApplication.ourCountryList.countryList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(JavacountriesApplication.ourCountryList.countryList, HttpStatus.OK);
    }

    // return the countries alphabetically that begin with the given letter
    // localhost:2020/names/{type a letter} for example localhost:2020/names/m
    @GetMapping(value = "/{letter}",
                produces = {"application/json"})
    public ResponseEntity<?> getCountryByLetter(@PathVariable char letter)
    {
        ArrayList<Country> rtnCountry = JavacountriesApplication.ourCountryList.findCountries(c -> c.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter));
        rtnCountry.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
    }

    // localhost:2020/names/size/{number}
    @GetMapping(value = "/size/{number}",
                produces = {"application/json"})
    public ResponseEntity<?> countryByNameLength(@PathVariable int number)
    {
        ArrayList<Country> rtnCnt =
                JavacountriesApplication.ourCountryList.findCountries(c -> c.getName().length() >= number);
        rtnCnt.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCnt, HttpStatus.OK);
    }
}
