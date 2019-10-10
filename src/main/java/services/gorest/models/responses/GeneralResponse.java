package services.gorest.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.gorest.models.nodes._Meta;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneralResponse {

    private _Meta _meta;

}
