package cfagent.partner;

import cfagent.address.Address;
import cfagent.insurance.Insurance;
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

    @Transactional
    public PartnerDTO addAddressToPartner(long id, AddAddressCommand command) {
        Partner partner = partnerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Partner not found: " + id));
        Address address = new Address(command.getPostcode(), command.getCity(), command.getStreet());
        partner.addAddress(address);
        return modelMapper.map(partner, PartnerDTO.class);
    }

    @Transactional
    public PartnerDTO addInsuranceToPartner(long id, AddInsuranceCommand command) {
        Partner partner = partnerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Partner not found: " + id));
        Insurance insurance = new Insurance(command.getCompany(), command.getType(), command.getInsurance(), command.isActive());
        partner.addInsurance(insurance);
        return modelMapper.map(partner, PartnerDTO.class);
    }
}
