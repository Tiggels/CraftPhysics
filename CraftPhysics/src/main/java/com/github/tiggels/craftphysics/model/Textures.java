package com.github.tiggels.craftphysics.model;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "particle"
})
public class Textures {

    @JsonProperty("particle")
    private String particle;

    /**
     * No args constructor for use in serialization
     *
     */
    public Textures() {
    }

    /**
     *
     * @param particle
     */
    public Textures(String particle) {
        this.particle = particle;
    }

    /**
     *
     * @return
     * The particle
     */
    @JsonProperty("particle")
    public String getParticle() {
        return particle;
    }

    /**
     *
     * @param particle
     * The particle
     */
    @JsonProperty("particle")
    public void setParticle(String particle) {
        this.particle = particle;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(particle).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Textures) == false) {
            return false;
        }
        Textures rhs = ((Textures) other);
        return new EqualsBuilder().append(particle, rhs.particle).isEquals();
    }

}