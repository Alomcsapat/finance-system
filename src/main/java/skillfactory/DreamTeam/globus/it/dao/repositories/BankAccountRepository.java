package skillfactory.DreamTeam.globus.it.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankAccountEntity;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
    public BankAccountEntity findByHolderId(Long id);
}
