package vinhnguyen.ptithcm.banking.common.web.response.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS("0000", "Operation successful"),
    ERROR("9999", "Something went wrong, please try again"),
    DUPLICATE_ID("1000", "Duplicate ID"),
    NOT_FOUND_ID("1001", "Not found ID"),;
    private String code;
    private String message;
}
