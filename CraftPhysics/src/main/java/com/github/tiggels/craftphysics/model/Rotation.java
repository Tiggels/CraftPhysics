package com.github.tiggels.craftphysics.model;

import java.util.ArrayList;
import java.util.List;
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
        "origin",
        "axis",
        "angle",
        "rescale"
})
public class Rotation {

    @JsonProperty("origin")
    private List<Integer> origin = new ArrayList<Integer>();
    @JsonProperty("axis")
    private String axis;
    @JsonProperty("angle")
    private Integer angle;
    @JsonProperty("rescale")
    private Boolean rescale;

    /**
     * No args constructor for use in serialization
     *
     */
    public Rotation() {
    }

    /**
     *
     * @param axis
     * @param rescale
     * @param origin
     * @param angle
     */
    public Rotation(List<Integer> origin, String axis, Integer angle, Boolean rescale) {
        this.origin = origin;
        this.axis = axis;
        this.angle = angle;
        this.rescale = rescale;
    }

    /**
     *
     * @return
     * The origin
     */
    @JsonProperty("origin")
    public List<Integer> getOrigin() {
        return origin;
    }

    /**
     *
     * @param origin
     * The origin
     */
    @JsonProperty("origin")
    public void setOrigin(List<Integer> origin) {
        this.origin = origin;
    }

    /**
     *
     * @return
     * The axis
     */
    @JsonProperty("axis")
    public String getAxis() {
        return axis;
    }

    /**
     *
     * @param axis
     * The axis
     */
    @JsonProperty("axis")
    public void setAxis(String axis) {
        this.axis = axis;
    }

    /**
     *
     * @return
     * The angle
     */
    @JsonProperty("angle")
    public Integer getAngle() {
        return angle;
    }

    /**
     *
     * @param angle
     * The angle
     */
    @JsonProperty("angle")
    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    /**
     *
     * @return
     * The rescale
     */
    @JsonProperty("rescale")
    public Boolean getRescale() {
        return rescale;
    }

    /**
     *
     * @param rescale
     * The rescale
     */
    @JsonProperty("rescale")
    public void setRescale(Boolean rescale) {
        this.rescale = rescale;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(origin).append(axis).append(angle).append(rescale).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Rotation) == false) {
            return false;
        }
        Rotation rhs = ((Rotation) other);
        return new EqualsBuilder().append(origin, rhs.origin).append(axis, rhs.axis).append(angle, rhs.angle).append(rescale, rhs.rescale).isEquals();
    }

}