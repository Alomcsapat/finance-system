package skillfactory.DreamTeam.globus.it.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationEntity;
import skillfactory.DreamTeam.globus.it.dto.operation.*;
import skillfactory.DreamTeam.globus.it.enums.OperationType;
import skillfactory.DreamTeam.globus.it.enums.Status;
import skillfactory.DreamTeam.globus.it.services.operation.OperationService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/operations")
public class OperationController {
    private final OperationService operationService;

    @GetMapping("")
    public List<OperationDTO> operations(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String accountId,
            @RequestParam(required = false) String createDateTime,
            @RequestParam(required = false) String createDateTimeMin,
            @RequestParam(required = false) String createDateTimeMax,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String inn,
            @RequestParam(required = false) String amountMin,
            @RequestParam(required = false) String amountMax,
            @RequestParam(required = false) String categoryId
    ) {
        var filters = new OperationQueryParams();
        try {
            if (type != null && !type.isEmpty()) {
                var operationType = OperationType.valueOf(type.toUpperCase());
                filters.setType(operationType);
            }

            if (accountId != null && !accountId.isEmpty()) {
                filters.setAccountId(Long.parseLong(accountId));
            }

            if (createDateTime != null && !createDateTime.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                LocalDateTime dateTime = LocalDateTime.parse(createDateTime, formatter);
                filters.setCreateDateTime(dateTime);
            }

            if (createDateTimeMin != null && !createDateTimeMin.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                LocalDateTime dateTimeMin = LocalDateTime.parse(createDateTimeMin, formatter);
                filters.setCreateDateTimeMin(dateTimeMin);
            }

            if (createDateTimeMax != null && !createDateTimeMax.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                LocalDateTime dateTimeMax = LocalDateTime.parse(createDateTimeMax, formatter);
                filters.setCreateDateTimeMax(dateTimeMax);
            }

            if (status != null && !status.isEmpty()) {
                var operationStatus = Status.valueOf(status.toUpperCase());
                filters.setStatus(operationStatus);
            }

            if (inn != null && !inn.isEmpty()) {
                filters.setInn(inn);
            }

            if (amountMin != null && !amountMin.isEmpty()) {
                var operationAmount = new BigDecimal(amountMin);
                filters.setAmountMin(operationAmount);
            }

            if (amountMax != null && !amountMax.isEmpty()) {
                var operationAmount = new BigDecimal(amountMax);
                filters.setAmountMax(operationAmount);
            }

            if (categoryId != null && !categoryId.isEmpty()) {
                filters.setCategoryId(Long.parseLong(categoryId));
            }

        } catch (Exception e) {
                return null;
        }

        List<OperationEntity> operations = operationService.operations(filters);
        return operations.stream()
                .map(operation -> OperationDTO.builder()
                        .accountId(operation.getAccount().getId())
                        .type(operation.getType())
                        .createDateTime(operation.getCreateDateTime())
                        .createDateTimeMin(operation.getCreateDateTime())
                        .createDateTimeMax(operation.getCreateDateTime())
                        .status(operation.getStatus())
                        .inn(operation.getContact().getInn())
                        .amount(operation.getAmount())
                        .categoryTitle(operation.getCategory().getTitle())
                        .contactId(operation.getContact().getId())
                        .description(operation.getDescription())
                        .build()
                ).collect(Collectors.toList());

    }


     @PostMapping("/new")
     public OperationDTO newOperation(@RequestBody CreateOperationRequest request) throws InterruptedException {
         var operation = operationService.createOperation(request);
         return OperationDTO.builder()
                 .accountId(operation.getAccount().getId())
                 .type(operation.getType())
                 .createDateTime(operation.getCreateDateTime())
                 .createDateTimeMin(operation.getCreateDateTime())
                 .createDateTimeMax(operation.getCreateDateTime())
                 .status(operation.getStatus())
                 .inn(operation.getContact().getInn())
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
                .createDateTime(operation.getCreateDateTime())
                .createDateTimeMin(operation.getCreateDateTime())
                .createDateTimeMax(operation.getCreateDateTime())
                .status(operation.getStatus())
                .inn(operation.getContact().getInn())
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
                .createDateTime(operation.getCreateDateTime())
                .createDateTimeMin(operation.getCreateDateTime())
                .createDateTimeMax(operation.getCreateDateTime())
                .status(operation.getStatus())
                .inn(operation.getContact().getInn())
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
                .createDateTime(operation.getCreateDateTime())
                .createDateTimeMin(operation.getCreateDateTime())
                .createDateTimeMax(operation.getCreateDateTime())
                .status(operation.getStatus())
                .inn(operation.getContact().getInn())
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
                .createDateTime(operation.getCreateDateTime())
                .createDateTimeMin(operation.getCreateDateTime())
                .createDateTimeMax(operation.getCreateDateTime())
                .status(operation.getStatus())
                .inn(operation.getContact().getInn())
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
                .createDateTime(operation.getCreateDateTime())
                .createDateTimeMin(operation.getCreateDateTime())
                .createDateTimeMax(operation.getCreateDateTime())
                .status(operation.getStatus())
                .inn(operation.getContact().getInn())
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
                .createDateTime(operation.getCreateDateTime())
                .createDateTimeMin(operation.getCreateDateTime())
                .createDateTimeMax(operation.getCreateDateTime())
                .status(operation.getStatus())
                .inn(operation.getContact().getInn())
                .amount(operation.getAmount())
                .categoryTitle(operation.getCategory().getTitle())
                .contactId(operation.getContact().getId())
                .description(operation.getDescription())
                .build();
    }

}
