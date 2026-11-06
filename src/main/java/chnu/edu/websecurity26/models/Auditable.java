package chnu.edu.websecurity26.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public abstract class Auditable {
    @CreatedDate
    private LocalDateTime createdDate;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @LastModifiedBy
    private String lastModifiedBy;
    @Version
    private Long version;
}