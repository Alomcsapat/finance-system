package skillfactory.DreamTeam.globus.it.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import skillfactory.DreamTeam.globus.it.dto.auth.WalletUserDetails;
import skillfactory.DreamTeam.globus.it.dto.operation.OperationFilter;
import skillfactory.DreamTeam.globus.it.enums.OperationType;
import skillfactory.DreamTeam.globus.it.enums.Status;
import skillfactory.DreamTeam.globus.it.services.ReportService;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<byte[]> downloadInternal(
            @RequestParam(required = false) OperationType type,
            @RequestParam(required = false) Long accountId,
            @RequestParam(required = false) LocalDateTime createDateTime,
            @RequestParam(required = false) LocalDateTime createDateTimeMin,
            @RequestParam(required = false) LocalDateTime createDateTimeMax,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) String inn,
            @RequestParam(required = false) BigDecimal amountMin,
            @RequestParam(required = false) BigDecimal amountMax,
            @RequestParam(required = false) Long categoryId,
            Authentication authentication
    ) {
        byte[] file = reportService.generate(OperationFilter.builder()
                .type(type)
                .accountId(accountId)
                .createDateTime(createDateTime)
                .createDateTimeMin(createDateTimeMin)
                .createDateTimeMax(createDateTimeMax)
                .status(status)
                .inn(inn)
                .amountMin(amountMin)
                .amountMax(amountMax)
                .categoryId(categoryId)
                .build(), (WalletUserDetails) authentication.getPrincipal());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String encodedFilename = URLEncoder.encode("Report", StandardCharsets.UTF_8).replace("+", "%20");
        headers.add("content-disposition", "inline;filename*=UTF-8''" + encodedFilename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

}
