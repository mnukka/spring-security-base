package ee.company.crm.domain.service.sector;

import ee.company.crm.application.web.profile.SectorDto;
import ee.company.crm.domain.persistence.sector.SectorDao;
import ee.company.crm.domain.persistence.sector.SectorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectorService {

    private SectorDao sectorDao;

    public SectorService(SectorDao sectorDao) {
        this.sectorDao = sectorDao;
    }

    public SectorDto getSectorById(Long id) {
        final ModelMapper modelMapper = new ModelMapper();
        final SectorEntity sectorEntity = sectorDao.findById(id);
        return modelMapper.map(sectorEntity, SectorDto.class);
    }

    public List<SectorDto> findAllSectors() {
        final ModelMapper modelMapper = new ModelMapper();
        final List<SectorEntity> sectorEntities = sectorDao.findAll();
        return sectorEntities.stream().map(p -> modelMapper.map(p, SectorDto.class)).collect(Collectors.toList());
    }
}
