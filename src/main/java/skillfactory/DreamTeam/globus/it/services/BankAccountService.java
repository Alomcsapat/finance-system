package skillfactory.DreamTeam.globus.it.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankAccountEntity;
import skillfactory.DreamTeam.globus.it.dao.repositories.BankAccountRepository;
import skillfactory.DreamTeam.globus.it.dto.bank.BankCreationRequests;

@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final BankService bankService;
    private final ProfileService profileService;

    public BankAccountEntity createAccount(BankCreationRequests.CreateBankAccount request) {
        return bankAccountRepository.save(
            BankAccountEntity.builder()
                .holder(profileService.findById(request.holderId()).orElseThrow(() -> new IllegalArgumentException("Holder not found")))
                .bank(bankService.findById(request.bankId()).orElseThrow(() -> new IllegalArgumentException("Bank not found")))
                .balance(request.balance())
                .accountNumber(request.accountNumber())
                .title(request.title())
                .build());
    }

    public BankAccountEntity findById(Long id) {
        return bankAccountRepository.findById(id).orElse(null);
    }

    public BankAccountEntity getAccountByHolderId(Long id) {
        return bankAccountRepository.findByHolderId(id);
    }

    // public BankAccountEntity getByRecepientDate(LocalDateTime date) {
    //     return bankAccountRepository.findByRecepientDate(date).orElse(null);
    // }
}
