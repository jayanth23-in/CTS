package DeepSkilling.Week3.SpringRest.src.main.java.com.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import DeepSkilling.Week3.SpringRest.src.main.java.com.Country;
import DeepSkilling.Week3.SpringRest.src.main.java.com.service.CountryService;
import DeepSkilling.Week3.SpringRest.src.main.java.com.service.exception.CountryNotFoundException;

@RestController
@RequestMapping
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    // GET: http://localhost:8080/country
    @GetMapping("/country")
    public Country getCountryIndia() {
        LOGGER.info("START: getCountryIndia endpoint");
        Country country = countryService.getCountryIndia();
        LOGGER.info("END: getCountryIndia endpoint");
        return country;
    }

    // GET: http://localhost:8080/countries
    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        LOGGER.info("START: getAllCountries endpoint");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.info("END: getAllCountries endpoint");
        return countries;
    }

    // GET: http://localhost:8080/countries/{code}
    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("START: getCountry endpoint for code: {}", code);
        Country country = countryService.getCountry(code);
        LOGGER.info("END: getCountry endpoint");
        return country;
    }
}