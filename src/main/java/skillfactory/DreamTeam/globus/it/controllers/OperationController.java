package skillfactory.DreamTeam.globus.it.controllers;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.ContactEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.ProfileEntity;
import skillfactory.DreamTeam.globus.it.dto.auth.WalletUserDetails;
import skillfactory.DreamTeam.globus.it.dto.operation.*;
import skillfactory.DreamTeam.globus.it.dto.profile.Profile;
import skillfactory.DreamTeam.globus.it.dto.profile.ProfileCreationRequests;
import skillfactory.DreamTeam.globus.it.enums.OperationType;
import skillfactory.DreamTeam.globus.it.enums.Status;
import skillfactory.DreamTeam.globus.it.services.ProfileService;
import skillfactory.DreamTeam.globus.it.services.operation.OperationService;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/operations")
public class OperationController {
    private final OperationService operationService;
    private final ProfileService profileService;

    @PostMapping("/contacts/persons")
    public Profile createContact(@RequestBody ProfileCreationRequests.CreatePerson request) {
        ProfileEntity profile = profileService.createPerson(request);
        return Profile.builder()
                .id(profile.getId())
                .inn(profile.getInn())
                .name(profile.getName())
                .email(profile.getEmail())
                .phone(profile.getPhone())
                .build();
    }

    @PostMapping("/contacts/companies")
    public Profile createContact(@RequestBody ProfileCreationRequests.CreateCompany request) {
        ProfileEntity profile = profileService.createCompany(request);
        return Profile.builder()
                .id(profile.getId())
                .inn(profile.getInn())
                .name(profile.getName())
                .email(profile.getEmail())
                .phone(profile.getPhone())
                .build();
    }

    @GetMapping
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
            @RequestParam(required = false) String categoryId,
            @Min(0) @RequestParam(required = false, defaultValue = "0") Integer page,
            @Min(1) @RequestParam(required = false, defaultValue = "20") Integer size
    ) {
        var filters = OperationFilter.builder()
                .page(page)
                .size(size)
                .build();
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
                filters.setAmountMin(operationAmount);
            }

            if (categoryId != null && !categoryId.isEmpty()) {
                filters.setCategoryId(Long.parseLong(categoryId));
            }

        } catch (Exception e) {
                return null;
        }

        List<OperationEntity> operations = operationService.getPageByFilter(filters);
        return operations.stream()
                .map(operation -> OperationDTO.builder()
                        .id(operation.getId())
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
     public OperationDTO newOperation(@RequestBody CreateOperationRequest request, Authentication authentication) throws InterruptedException {
         var operation = operationService.createOperation(request, (WalletUserDetails) authentication.getPrincipal());
         return OperationDTO.builder()
                 .id(operation.getId())
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
                .id(operation.getId())
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

    @PutMapping("/confirm")
    public OperationDTO confirmOperation(@RequestBody ConfirmOperationRequest request) {
        var operation = operationService.confirmOperation(request);
        return OperationDTO.builder()
                .id(operation.getId())
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
                .id(operation.getId())
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
                .id(operation.getId())
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
                .id(operation.getId())
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
                .id(operation.getId())
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
