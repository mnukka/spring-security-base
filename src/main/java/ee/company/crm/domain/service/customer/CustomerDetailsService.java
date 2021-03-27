package ee.company.crm.domain.service.customer;

import ee.company.crm.domain.persistence.customer.SectorDao;
import ee.company.crm.domain.persistence.customer.SectorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerDetailsService {

    private SectorDao sectorDao;

    public CustomerDetailsService(SectorDao sectorDao) {
        this.sectorDao = sectorDao;
    }

    public SectorDto getCountryById(Long id) {
        final ModelMapper modelMapper = new ModelMapper();
        final SectorEntity sectorEntity = sectorDao.findById(id);
        return modelMapper.map(sectorEntity, SectorDto.class);
    }

    public List<SectorDto> findAllCountries() {
        final ModelMapper modelMapper = new ModelMapper();
        final List<SectorEntity> sectorEntities = sectorDao.findAll();
        return sectorEntities.stream().map(p -> modelMapper.map(p, SectorDto.class)).collect(Collectors.toList());
    }
}
