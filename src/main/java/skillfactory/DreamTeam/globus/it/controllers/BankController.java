package skillfactory.DreamTeam.globus.it.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skillfactory.DreamTeam.globus.it.services.BankService;
import skillfactory.DreamTeam.globus.it.services.BankAccountService;
import skillfactory.DreamTeam.globus.it.dto.bank.BankCreationRequests;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankAccountEntity;
import skillfactory.DreamTeam.globus.it.dto.bank.BankAccountDTO;
import skillfactory.DreamTeam.globus.it.dto.bank.BankDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank")
public class BankController {
    private final BankService bankService;
    private final BankAccountService bankAccountService;

    @PostMapping("/new")
    public BankDTO createBank(@RequestBody BankCreationRequests.CreateBank request) {
        var bank = bankService.createBank(request);
        return BankDTO.builder()
                .id(bank.getId())
                .name(bank.getName())
                .build();
    }

    @PostMapping("/account/new")
    public BankAccountDTO createAccount(@RequestBody BankCreationRequests.CreateBankAccount request) {
        BankAccountEntity bankAccount = bankAccountService.createAccount(request);
        return BankAccountDTO.builder()
                .id(bankAccount.getId())
                .holderId(request.holderId())
                .bankId(request.bankId())
                .balance(bankAccount.getBalance())
                .accountNumber(bankAccount.getAccountNumber())
                .title(bankAccount.getTitle())
                .build();
    }

    @GetMapping("/account/{id}")
    public BankAccountDTO getAccountByHolderId(@PathVariable Long id) {
        BankAccountEntity bankAccount = bankAccountService.findById(id);
        return BankAccountDTO.builder()
                .id(bankAccount.getId())
                .holderId(bankAccount.getHolder().getId())
                .bankId(bankAccount.getBank().getId())
                .balance(bankAccount.getBalance())
                .accountNumber(bankAccount.getAccountNumber())
                .title(bankAccount.getTitle())
                .build();
    }

}
