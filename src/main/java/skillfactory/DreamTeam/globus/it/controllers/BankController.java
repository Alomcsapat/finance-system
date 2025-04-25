package skillfactory.DreamTeam.globus.it.controllers;
import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.boot.actuate.endpoint.OperationType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skillfactory.DreamTeam.globus.it.services.BankService;
import skillfactory.DreamTeam.globus.it.services.BankAccountService;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankEntity;
import skillfactory.DreamTeam.globus.it.dto.bank.BankCreationRequests;
import skillfactory.DreamTeam.globus.it.dto.bank.OperationFilter;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankAccountEntity;
import skillfactory.DreamTeam.globus.it.dto.bank.BankAccountDTO;
import skillfactory.DreamTeam.globus.it.dto.bank.BankDTO;
import lombok.RequiredArgsConstructor;

import java.io.ObjectInputFilter.Status;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class BankController {
    private final BankService bankService;
    private final BankAccountService bankAccountService;
    //private final OperationService operationService;
    
    //Test method to check if the controller is working
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("BankController is working!");
    }

    @PostMapping("/bank/new")
    public BankDTO createBank(@RequestBody BankCreationRequests.CreateBank request) {
        var bank = bankService.createBank(request);
        return BankDTO.builder()
                .id(bank.getId())
                .name(bank.getName())
                .build();
    }

    @PostMapping("/bank/account/new")
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

    @GetMapping("/bank/account/{id}")
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

    // @PostMapping("/operations")
    // public ResponseEntity<OperationDTO> createOperation(@RequestBody OperationCreationRequest request) {
    //     return ResponseEntity.ok(operationService.createOperation(request));
    // }

    // @PutMapping("/operations/{id}")
    // public ResponseEntity<OperationDTO> updateOperation(@PathVariable Long id, @RequestBody OperationUpdateRequest request) {
    //     return ResponseEntity.ok(operationService.updateOperation(id, request));
    // }

    // @GetMapping("/operations")
    // public ResponseEntity<List<OperationDTO>> getAllOperations(@ModelAttribute OperationFilter filter) {
    //     return ResponseEntity.ok(operationService.getAllOperations(filter));
    // }

    // @GetMapping("/operations/export")
    // public ResponseEntity<Resource> exportOperationsToPdf(@ModelAttribute OperationFilter filter) {
    //     return ResponseEntity.ok(operationService.exportOperationsToPdf(filter));
    // }

    // @GetMapping("/dashboard/bank-sender")
    // public ResponseEntity<List<OperationDTO>> getOperationsBySenderBank(@RequestParam String bankName) {
    //     return ResponseEntity.ok(operationService.getOperationsBySenderBank(bankName));
    // }

    // @GetMapping("/dashboard/bank-receiver")
    // public ResponseEntity<List<OperationDTO>> getOperationsByReceiverBank(@RequestParam String bankName) {
    //     return ResponseEntity.ok(operationService.getOperationsByReceiverBank(bankName));
    // }

    // @GetMapping("/dashboard/date")
    // public ResponseEntity<List<OperationDTO>> getOperationsByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    //     return ResponseEntity.ok(operationService.getOperationsByDate(date));
    // }

    // @GetMapping("/dashboard/date-range")
    // public ResponseEntity<List<OperationDTO>> getOperationsByDateRange(
    //     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
    //     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
    //     return ResponseEntity.ok(operationService.getOperationsByDateRange(startDate, endDate));
    // }

    // @GetMapping("/dashboard/status")
    // public ResponseEntity<List<OperationDTO>> getOperationsByStatus(@RequestParam(required=false) Status status) {
    //     return ResponseEntity.ok(operationService.getOperationsByStatus(status));
    // }

    // @GetMapping("/dashboard/inn")
    // public ResponseEntity<List<OperationDTO>> getOperationsByINN(@RequestParam String inn) {
    //     return ResponseEntity.ok(operationService.getOperationsByINN(inn));
    // }

    // @GetMapping("/dashboard/amount")
    // public ResponseEntity<List<OperationDTO>> getOperationsByAmount(@RequestParam BigDecimal amount) {
    //     return ResponseEntity.ok(operationService.getOperationsByAmount(amount));
    // }

    // @GetMapping("/dashboard/type")
    // public ResponseEntity<List<OperationDTO>> getOperationsByType(@RequestParam(required=false) OperationType type) {
    //     return ResponseEntity.ok(operationService.getOperationsByType(type));
    // }

    // @GetMapping("/dashboard/category")
    // public ResponseEntity<List<OperationDTO>> getOperationsByCategory(@RequestParam String category) {
    //     return ResponseEntity.ok(operationService.getOperationsByCategory(category));
    // }
}
