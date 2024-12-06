package vinhnguyen.ptithcm.banking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vinhnguyen.ptithcm.banking.common.web.response.ApiResponse;
import vinhnguyen.ptithcm.banking.common.web.response.enumeration.ResponseCode;
import vinhnguyen.ptithcm.banking.dto.AccountDto;
import vinhnguyen.ptithcm.banking.service.AccountService;

@Slf4j
@Controller
@RequestMapping("banking_user/account")
@RequiredArgsConstructor
public class AccountController {
    public final AccountService accountService;
    @PostMapping
    public ResponseEntity<ApiResponse<AccountDto>> createAccount(@RequestBody AccountDto request) {
        try {
            log.info("Account data: {}", request);
            var result = accountService.createAccount(request);
            var data =  ApiResponse.<AccountDto>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getMessage())
                    .data(result)
                    .success(true)
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(data);
        }catch (Exception e) {
            log.error("Account creation failed: {}", e.getMessage());
            var data =  ApiResponse.<AccountDto>builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getMessage())
                    .success(false)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
        }
    }
}
