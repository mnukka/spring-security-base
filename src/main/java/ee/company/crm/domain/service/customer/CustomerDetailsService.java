package ee.company.crm.domain.service.customer;

import ee.company.crm.domain.persistence.customer.CountryDao;
import ee.company.crm.domain.persistence.customer.CountryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerDetailsService {

    private CountryDao countryDao;

    public CustomerDetailsService(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public CountryDto getCountryById(Long id) {
        final ModelMapper modelMapper = new ModelMapper();
        final CountryEntity countryEntity = countryDao.findById(id);
        return modelMapper.map(countryEntity, CountryDto.class);
    }

    public List<CountryDto> findAllCountries() {
        final ModelMapper modelMapper = new ModelMapper();
        final List<CountryEntity> countryEntities = countryDao.findAll();
        return countryEntities.stream().map(p -> modelMapper.map(p, CountryDto.class)).collect(Collectors.toList());
    }
}
