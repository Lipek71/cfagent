package cfagent;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PartnerService {

    private PartnerRepository partnerRepository;

    private ModelMapper modelMapper;

    public List<PartnerDTO> listPartners(Optional<String> name) {
        return partnerRepository.findAll().stream()
                .filter(a -> name.isEmpty() || a.getName().toLowerCase().contains(name.get().toLowerCase()))
                .map(a -> modelMapper.map(a, PartnerDTO.class))
                .collect(Collectors.toList());
    }

    public PartnerDTO createPartner(CreatePartnerCommand command) {

        Partner partner = new Partner(command.getName(), command.isCompany());

        partner.setActive(true);

        partnerRepository.save(partner);

        return modelMapper.map(partner, PartnerDTO.class);
    }

    @Transactional
    public PartnerDTO updatePartner(long id, UpdatePartnerCommand command) {
        Partner partner = partnerRepository.getById(id);
        if(partner == null){
            throw new IllegalArgumentException("Partner not found!" + id);
        }
        if (!partner.getName().equals(command.getName())
                || !partner.isCompany() == command.isCompany()
                || !partner.isActive() == command.isActive()) {

            partner.setName(command.getName());
            partner.setCompany(command.isCompany());
            partner.setActive(command.isActive());

        }
        return modelMapper.map(partner, PartnerDTO.class);
    }

    @Transactional
    public void deletePartner(long id) {
        Partner partner = partnerRepository.getById(id);
        if(partner == null){
            throw new IllegalArgumentException("Training class not found!" + id);
        }
        partnerRepository.deleteById(id);

    }
}
