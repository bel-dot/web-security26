package chnu.edu.websecurity26.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Game {
    private String id;
    private String name;
    private String description;
    private String genre;
}