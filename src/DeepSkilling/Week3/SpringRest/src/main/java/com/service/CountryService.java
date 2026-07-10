package DeepSkilling.Week3.SpringRest.src.main.java.com.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import DeepSkilling.Week3.SpringRest.src.main.java.com.Country;
import DeepSkilling.Week3.SpringRest.src.main.java.com.service.exception.CountryNotFoundException;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    public Country getCountryIndia() {
        LOGGER.info("START: getCountryIndia service");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("in", Country.class);
        LOGGER.info("END: getCountryIndia service");
        return country;
    }

    @SuppressWarnings("unchecked")
    public List<Country> getAllCountries() {
        LOGGER.info("START: getAllCountries service");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countries = context.getBean("countryList", ArrayList.class);
        LOGGER.info("END: getAllCountries service");
        return countries;
    }

    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("START: getCountry service with code: {}", code);
        List<Country> countries = getAllCountries();

        Country result = countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.error("Country with code {} not found!", code);
                    return new CountryNotFoundException("Country not found");
                });

        LOGGER.info("END: getCountry service");
        return result;
    }
}