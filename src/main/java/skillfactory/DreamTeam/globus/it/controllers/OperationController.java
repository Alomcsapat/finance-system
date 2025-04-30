package skillfactory.DreamTeam.globus.it.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import skillfactory.DreamTeam.globus.it.dto.operation.*;
import skillfactory.DreamTeam.globus.it.enums.Status;
import skillfactory.DreamTeam.globus.it.services.operation.OperationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/operations")
public class OperationController {
    private final OperationService operationService;


     @PostMapping("/new")
     public OperationDTO newOperation(@RequestBody NewOperationRequest request) throws InterruptedException {
         var operation = operationService.newOperation(request);
         return OperationDTO.builder()
                 .accountId(operation.getAccount().getId())
                 .type(operation.getType())
                 .status(operation.getStatus())
                 .amount(operation.getAmount())
                 .categoryTitle(operation.getCategory().getTitle())
                 .contactId(operation.getContact().getId())
                 .description(operation.getDescription())
                 .build();

     }

    @PutMapping("/cancel")
    public OperationDTO cancelOperation(@RequestBody CancelOperationRequest request) {
        var operation = operationService.cancelOperation(request);
        return OperationDTO.builder()
                .accountId(operation.getAccount().getId())
                .type(operation.getType())
                .status(operation.getStatus())
                .amount(operation.getAmount())
                .categoryTitle(operation.getCategory().getTitle())
                .contactId(operation.getContact().getId())
                .description(operation.getDescription())
                .build();
    }

    @PutMapping("/сonfirm")
    public OperationDTO confirmOperation(@RequestBody ConfirmOperationRequest request) {
        var operation = operationService.confirmOperation(request);
        return OperationDTO.builder()
                .accountId(operation.getAccount().getId())
                .type(operation.getType())
                .status(operation.getStatus())
                .amount(operation.getAmount())
                .categoryTitle(operation.getCategory().getTitle())
                .contactId(operation.getContact().getId())
                .description(operation.getDescription())
                .build();
    }

    @DeleteMapping("/delete")
    public OperationDTO deleteOperation(@RequestBody DeleteOperationRequest request) {
        var operation = operationService.deleteOperation(request);
        return OperationDTO.builder()
                .accountId(operation.getAccount().getId())
                .type(operation.getType())
                .status(operation.getStatus())
                .amount(operation.getAmount())
                .categoryTitle(operation.getCategory().getTitle())
                .contactId(operation.getContact().getId())
                .description(operation.getDescription())
                .build();
    }

    @PostMapping("/processing")
    public OperationDTO processingOperation(@RequestBody ProcessingOperationRequest request) {
        var operation = operationService.processingOperation(request);
        return OperationDTO.builder()
                .accountId(operation.getAccount().getId())
                .type(operation.getType())
                .status(operation.getStatus())
                .amount(operation.getAmount())
                .categoryTitle(operation.getCategory().getTitle())
                .contactId(operation.getContact().getId())
                .description(operation.getDescription())
                .build();
    }

    @PostMapping("/completed")
    public OperationDTO completedOperation(@RequestBody CompletedOperationRequest request) {
        var operation = operationService.completedOperation(request);
        return OperationDTO.builder()
                .accountId(operation.getAccount().getId())
                .type(operation.getType())
                .status(operation.getStatus())
                .amount(operation.getAmount())
                .categoryTitle(operation.getCategory().getTitle())
                .contactId(operation.getContact().getId())
                .description(operation.getDescription())
                .build();
    }

    @PostMapping("/refunded")
    public OperationDTO refundedOperation(@RequestBody RefundedOperationRequest request) {
        var operation = operationService.refundedOperation(request);
        return OperationDTO.builder()
                .accountId(operation.getAccount().getId())
                .type(operation.getType())
                .status(operation.getStatus())
                .amount(operation.getAmount())
                .categoryTitle(operation.getCategory().getTitle())
                .contactId(operation.getContact().getId())
                .description(operation.getDescription())
                .build();
    }

}
