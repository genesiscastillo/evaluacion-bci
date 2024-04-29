package cl.genesiscastillo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.genesiscastillo.entity.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>{

}
