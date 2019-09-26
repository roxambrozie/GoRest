package services.gorest.pojo.responses;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import services.gorest.pojo.User;
import services.gorest.pojo.node._Meta;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_meta",
        "result"
})
public class GetUserResponse {
    @JsonProperty("_meta")
    private _Meta _meta;
    @JsonProperty("result")
    private User result;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("_meta")
    public _Meta get_meta() {
        return _meta;
    }

    @JsonProperty("_meta")
    public void set_meta(_Meta _meta) {
        this._meta = _meta;
    }

    @JsonProperty("result")
    public User getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(User result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(_meta).append(result).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GetUserResponse) == false) {
            return false;
        }
        GetUserResponse rhs = ((GetUserResponse) other);
        return new EqualsBuilder().append(_meta, rhs._meta).append(result, rhs.result).append(additionalProperties, rhs.additionalProperties).isEquals();
    }


}
