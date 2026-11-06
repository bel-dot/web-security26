package chnu.edu.websecurity26.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
@Getter
@Setter
public class Game extends Auditable {
    private String id;
    private String name;
    private String description;
    private String genre;
}