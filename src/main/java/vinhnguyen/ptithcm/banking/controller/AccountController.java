package vinhnguyen.ptithcm.banking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vinhnguyen.ptithcm.banking.common.web.response.ApiResponse;
import vinhnguyen.ptithcm.banking.common.web.response.PageResponse;
import vinhnguyen.ptithcm.banking.common.web.response.enumeration.ResponseCode;
import vinhnguyen.ptithcm.banking.dto.AccountDto;
import vinhnguyen.ptithcm.banking.service.AccountService;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("banking_user/account")
@RequiredArgsConstructor
public class AccountController {
    public final AccountService accountService;
    @PostMapping("create")
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
            log.error(e.getMessage());
            var data =  ApiResponse.<AccountDto>builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(ResponseCode.ERROR.getMessage())
                    .success(false)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<AccountDto>> getAccountById(@PathVariable UUID id) {
        try {
            var result = accountService.getAccount(id);
            var data =  ApiResponse.<AccountDto>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getMessage())
                    .data(result)
                    .success(true)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }catch (Exception e) {
            log.error(e.getMessage());
            var data =  ApiResponse.<AccountDto>builder()
                    .code(ResponseCode.NOT_FOUND_ID.getCode())
                    .message(ResponseCode.NOT_FOUND_ID.getMessage())
                    .success(false)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<AccountDto>>> getAccounts(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) {
        try {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            var result = accountService.getAccounts(pageable);
            var pageData = PageResponse.<AccountDto>builder()
                    .data(result.getContent())
                    .currentPage(page)
                    .totalPages(result.getTotalPages())
                    .build();
            var data =  ApiResponse.<PageResponse<AccountDto>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getMessage())
                    .data(pageData)
                    .success(true)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }catch (Exception e) {
            log.error(e.getMessage());
            var data =  ApiResponse.<PageResponse<AccountDto>>builder()
                    .code(ResponseCode.NOT_FOUND_ID.getCode())
                    .message(ResponseCode.NOT_FOUND_ID.getMessage())
                    .success(false)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
        }
    }

    @PutMapping("deposit/{id}")
    public ResponseEntity<ApiResponse<AccountDto>> deposit(@PathVariable UUID id, @RequestBody Map<String, Double> amount) {
        try {
            var result = accountService.deposit(id, amount.get("amount"));
            var data =  ApiResponse.<AccountDto>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getMessage())
                    .data(result)
                    .success(true)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }catch (Exception e) {
            log.error(e.getMessage());
            var data =  ApiResponse.<AccountDto>builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(e.getMessage())
                    .success(false)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
        }
    }
    @PutMapping("withdraw/{id}")
    public ResponseEntity<ApiResponse<AccountDto>> withdraw(@PathVariable UUID id, @RequestBody Map<String, Double> amount) {
        try {
            var result = accountService.withdraw(id, amount.get("amount"));
            var data =  ApiResponse.<AccountDto>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getMessage())
                    .data(result)
                    .success(true)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }catch (Exception e) {
            log.error(e.getMessage());
            var data =  ApiResponse.<AccountDto>builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(e.getMessage())
                    .success(false)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<AccountDto>> deleteAccount(@PathVariable UUID id) {
        try {
            accountService.deleteAccount(id);
            var data =  ApiResponse.<AccountDto>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getMessage())
                    .success(true)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }catch (Exception e) {
            log.error(e.getMessage());
            var data =  ApiResponse.<AccountDto>builder()
                    .code(ResponseCode.NOT_FOUND_ID.getCode())
                    .message(ResponseCode.NOT_FOUND_ID.getMessage())
                    .success(false)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<AccountDto>> updateAccount(@PathVariable UUID id, @RequestBody Map<String, Double> amount) {
        try {
            var result = accountService.updateAccount(id, amount.get("amount"));
            var data =  ApiResponse.<AccountDto>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getMessage())
                    .data(result)
                    .success(true)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            var data =  ApiResponse.<AccountDto>builder()
                    .code(ResponseCode.ERROR.getCode())
                    .message(e.getMessage())
                    .success(false)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
        }
    }
}
