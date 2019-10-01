package services.gorest.models.nodes;

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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "limit",
        "remaining",
        "reset"
})
public class RateLimit {
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("remaining")
    private Integer remaining;
    @JsonProperty("reset")
    private Integer reset;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @JsonProperty("remaining")
    public Integer getRemaining() {
        return remaining;
    }

    @JsonProperty("remaining")
    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    @JsonProperty("reset")
    public Integer getReset() {
        return reset;
    }

    @JsonProperty("reset")
    public void setReset(Integer reset) {
        this.reset = reset;
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
        return new HashCodeBuilder().append(limit).append(remaining).append(reset).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RateLimit) == false) {
            return false;
        }
        RateLimit rhs = ((RateLimit) other);
        return new EqualsBuilder().append(limit, rhs.limit).append(remaining, rhs.remaining).append(reset, rhs.reset).append(additionalProperties, rhs.additionalProperties).isEquals();
    }
}
