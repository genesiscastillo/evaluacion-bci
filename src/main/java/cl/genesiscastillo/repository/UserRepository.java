package cl.genesiscastillo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.genesiscastillo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query("SELECT u FROM User u WHERE u.email = ?1")
	List<User> findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.id = ?1")
	Optional<User> findById(String id);
	
}
