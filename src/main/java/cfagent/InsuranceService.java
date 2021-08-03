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
public class InsuranceService {

    private InsuranceReposity insuranceReposity;

    private ModelMapper modelMapper;

    public List<InsuranceDTO> listInsurances(Optional<String> company, Optional<String> type, Optional<String> insurance) {
        return insuranceReposity.findAll().stream()
                .filter(a -> company.isEmpty() || a.getCompany().toLowerCase().contains(company.get().toLowerCase()))
                .filter(a -> type.isEmpty() || a.getType().toLowerCase().contains(type.get().toLowerCase()))
                .filter(a -> insurance.isEmpty() || a.getInsurance().toLowerCase().contains(insurance.get().toLowerCase()))
                .map(a -> modelMapper.map(a, InsuranceDTO.class))
                .collect(Collectors.toList());
    }

    public InsuranceDTO createInsurance(CreateInsuranceCommand command) {
        Insurance insurance = new Insurance(command.getCompany(), command.getType(), command.getInsurance(), command.isActive());

        insuranceReposity.save(insurance);

        return modelMapper.map(insurance, InsuranceDTO.class);

    }

    @Transactional
    public InsuranceDTO updateInsurance(long id, UpdateInsuranceCommand command) {
        Insurance insurance = insuranceReposity.getById(id);
        if(insurance == null){
            throw new IllegalArgumentException("Insurance not found!" + id);
        }
        if (!insurance.getCompany().equals(command.getCompany())
                || !insurance.getType().equals(command.getType())
                || !insurance.getInsurance().equals(command.getInsurance())
                || !insurance.isActive() == command.isActive()) {

            insurance.setCompany(command.getCompany());
            insurance.setType(command.getType());
            insurance.setInsurance(command.getInsurance());
            insurance.setActive(command.isActive());

        }
        return modelMapper.map(insurance, InsuranceDTO.class);
    }
}
