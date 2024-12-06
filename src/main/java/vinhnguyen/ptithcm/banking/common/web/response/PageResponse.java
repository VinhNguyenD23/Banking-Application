package vinhnguyen.ptithcm.banking.common.web.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
public class PageResponse<T> {
    List<T> data;
    int currentPage;
    int totalPages;
}


