package cfagent.address;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressService {

    private AddressRepository addressRepository;

    private ModelMapper modelMapper;

    public List<AddressDTO> listAdresses(Optional<String> postcode, Optional<String> city, Optional<String> street) {
        return addressRepository.findAll().stream()
                .filter(a -> postcode.isEmpty() || a.getPostcode().toLowerCase().contains(postcode.get().toLowerCase()))
                .filter(a -> city.isEmpty() || a.getCity().toLowerCase().contains(city.get().toLowerCase()))
                .filter(a -> street.isEmpty() || a.getStreet().toLowerCase().contains(street.get().toLowerCase()))
                .map(a -> modelMapper.map(a, AddressDTO.class))
                .collect(Collectors.toList());
    }

    public AddressDTO createAddress(CreateAddressCommand command) {
        Address address = new Address(command.getPostcode(), command.getCity(), command.getStreet());

        addressRepository.save(address);

        return modelMapper.map(address, AddressDTO.class);
    }

    @Transactional
    public AddressDTO updateAddress(long id, UpdateAddressCommand command) {
        Address address = addressRepository.getById(id);
        if(address == null){
            throw new IllegalArgumentException("Address not found!" + id);
        }
        if (!address.getPostcode().equals(command.getPostcode())
                || !address.getCity().equals(command.getCity())
                || !address.getStreet().equals(command.getStreet())) {

            address.setPostcode(command.getPostcode());
            address.setCity(command.getCity());
            address.setStreet(command.getStreet());

        }
        return modelMapper.map(address, AddressDTO.class);
    }
}
