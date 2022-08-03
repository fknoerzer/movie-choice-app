package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "criteria")
public class Criteria {

    @Id
    private String id;

    private String minReleaseDate;

    private Integer maxRuntime;

    private Number minRating;

    private List<Genres> genres;

    private List<Cast> cast;

    private List<WatchProvider> watchProviders;

}
