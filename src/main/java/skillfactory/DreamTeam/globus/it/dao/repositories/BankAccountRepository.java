package skillfactory.DreamTeam.globus.it.dao.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankAccountEntity;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
//    public Optional<BankAccountEntity> findByHolderId(Long id);
//
//    public Optional<BankAccountEntity> findByRecepientDate(LocalDateTime date);
}
