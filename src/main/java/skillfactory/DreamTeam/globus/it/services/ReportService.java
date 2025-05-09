package skillfactory.DreamTeam.globus.it.services;

import com.itextpdf.html2pdf.HtmlConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.PersonEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.ProfileEntity;
import skillfactory.DreamTeam.globus.it.dto.auth.WalletUserDetails;
import skillfactory.DreamTeam.globus.it.dto.operation.OperationFilter;
import skillfactory.DreamTeam.globus.it.dto.report.ReportDefinition.HolderType;
import skillfactory.DreamTeam.globus.it.dto.report.ReportDefinition.Item;
import skillfactory.DreamTeam.globus.it.enums.OperationType;
import skillfactory.DreamTeam.globus.it.services.operation.OperationService;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OperationService operationService;
    private final ProfileService profileService;
    private final TemplateEngine pdfTemplateEngine;


    public byte[] generate(OperationFilter filter, WalletUserDetails principal) {
        ProfileEntity profile = profileService.findById(principal.getUserId()).orElseThrow(() -> new UsernameNotFoundException("User not present!"));
        List<OperationEntity> operations = operationService.getAllByFilter(filter);
        Context context = new Context();
        ByteArrayOutputStream outputBytes = new ByteArrayOutputStream();
        context.setVariable("holderName", profile.getName());
        context.setVariable("holderInn", profile.getInn());
        context.setVariable("items", convert(operations));
        HtmlConverter.convertToPdf(pdfTemplateEngine.process("/Report.html", context), outputBytes);
        return outputBytes.toByteArray();
    }

    private List<Item> convert(List<OperationEntity> operations) {
        if (operations == null || operations.isEmpty()) return new ArrayList<>();
        return operations.stream().map( operation -> operation.getType() == OperationType.CREDIT ? buildCreditItem(operation) : buildDebitItem(operation)).toList();
    }

    private Item buildCreditItem(OperationEntity operation) {
        return Item.builder()
                .contactType(operation.getContact() instanceof PersonEntity ? HolderType.INDIVIDUAL : HolderType.COMPANY)
                .createDateTime(operation.getCreateDateTime())
                .type(OperationType.CREDIT)
                .description(operation.getDescription())
                .amount(format(operation.getAmount()))
                .status(operation.getStatus())
                .sendBank(operation.getAccount().getBank().getName())
                .sendBankAccount(operation.getAccount().getAccountNumber())
                .recipientBank(operation.getContact().getBankAccount().getBank().getName())
                .recipientBankAccount(operation.getContact().getBankAccount().getAccountNumber())
                .inn(operation.getContact().getInn())
                .category(operation.getCategory().getTitle())
                .recipientNumber(operation.getContact().getPhone())
                .build();
    }

    private Item buildDebitItem(OperationEntity operation) {
        return Item.builder()
                .contactType(operation.getContact() instanceof PersonEntity ? HolderType.INDIVIDUAL : HolderType.COMPANY)
                .createDateTime(operation.getCreateDateTime())
                .type(OperationType.DEBIT)
                .description(operation.getDescription())
                .amount(format(operation.getAmount()))
                .status(operation.getStatus())
                .recipientBank(operation.getAccount().getBank().getName())
                .recipientBankAccount(operation.getAccount().getAccountNumber())
                .sendBank(operation.getContact().getBankAccount().getBank().getName())
                .sendBankAccount(operation.getContact().getBankAccount().getAccountNumber())
                .inn(operation.getContact().getInn())
                .category(operation.getCategory().getTitle())
                .recipientNumber(operation.getAccount().getHolder().getPhone())
                .build();
    }

    private String format(BigDecimal amount) {
        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.ENGLISH);
        format.applyPattern("#,##0.00000");
        return format.format(amount);
    }

}
