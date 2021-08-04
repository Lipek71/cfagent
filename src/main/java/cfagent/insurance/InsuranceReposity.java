package cfagent.insurance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceReposity extends JpaRepository<Insurance, Long> {
}
