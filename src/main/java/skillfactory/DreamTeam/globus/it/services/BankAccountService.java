package skillfactory.DreamTeam.globus.it.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankAccountEntity;
import skillfactory.DreamTeam.globus.it.dao.repositories.BankAccountRepository;
import skillfactory.DreamTeam.globus.it.dto.bank.BankCreationRequests;

@Service
public class BankAccountService {
    private BankAccountRepository bankAccountRepository;
    private BankService bankService;
    private ProfileService profileService;

    public BankAccountEntity createAccount(BankCreationRequests.CreateBankAccount request) {
        return bankAccountRepository.save(
            BankAccountEntity.builder()
                .holder(request.holder())
                .bank(request.bank())
                .balance(request.balance())
                .accountNumber(request.accountNumber())
                .title(request.title())
                .build());
    }

//    public BankAccountEntity getAccountByHolderId(Long id) {
//        return bankAccountRepository.findByHolderId(id).orElse(null);
//    }
//
//    public BankAccountEntity getByRecepientDate(LocalDateTime date) {
//        return bankAccountRepository.findByRecepientDate(date).orElse(null);
//    }
}
