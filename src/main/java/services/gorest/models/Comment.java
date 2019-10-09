package services.gorest.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import services.gorest.models.nodes.Links;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "post_id",
        "name",
        "email",
        "body",
        "_links"
})
@NoArgsConstructor
@Data
public class Comment {
    @JsonProperty("id")
    private String id;
    @JsonProperty("post_id")
    private int post_id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("body")
    private String body;
    @JsonProperty("_links")
    private Links links;

    public Comment(int post_id, String name, String email, String body) {
        this.post_id = post_id;
        this.name = name;
        this.email = email;
        this.body = body;
    }
}
