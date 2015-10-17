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
        "from",
        "to",
        "rotation",
        "shade",
        "faces"
})
public class Element {

    @JsonProperty("from")
    private List<Integer> from = new ArrayList<Integer>();
    @JsonProperty("to")
    private List<Integer> to = new ArrayList<Integer>();
    @JsonProperty("rotation")
    private Rotation rotation;
    @JsonProperty("shade")
    private Boolean shade;
    @JsonProperty("faces")
    private Faces faces;

    /**
     * No args constructor for use in serialization
     *
     */
    public Element() {
    }

    /**
     *
     * @param to
     * @param faces
     * @param rotation
     * @param from
     * @param shade
     */
    public Element(List<Integer> from, List<Integer> to, Rotation rotation, Boolean shade, Faces faces) {
        this.from = from;
        this.to = to;
        this.rotation = rotation;
        this.shade = shade;
        this.faces = faces;
    }

    /**
     *
     * @return
     * The from
     */
    @JsonProperty("from")
    public List<Integer> getFrom() {
        return from;
    }

    /**
     *
     * @param from
     * The from
     */
    @JsonProperty("from")
    public void setFrom(List<Integer> from) {
        this.from = from;
    }

    /**
     *
     * @return
     * The to
     */
    @JsonProperty("to")
    public List<Integer> getTo() {
        return to;
    }

    /**
     *
     * @param to
     * The to
     */
    @JsonProperty("to")
    public void setTo(List<Integer> to) {
        this.to = to;
    }

    /**
     *
     * @return
     * The rotation
     */
    @JsonProperty("rotation")
    public Rotation getRotation() {
        return rotation;
    }

    /**
     *
     * @param rotation
     * The rotation
     */
    @JsonProperty("rotation")
    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    /**
     *
     * @return
     * The shade
     */
    @JsonProperty("shade")
    public Boolean getShade() {
        return shade;
    }

    /**
     *
     * @param shade
     * The shade
     */
    @JsonProperty("shade")
    public void setShade(Boolean shade) {
        this.shade = shade;
    }

    /**
     *
     * @return
     * The faces
     */
    @JsonProperty("faces")
    public Faces getFaces() {
        return faces;
    }

    /**
     *
     * @param faces
     * The faces
     */
    @JsonProperty("faces")
    public void setFaces(Faces faces) {
        this.faces = faces;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(from).append(to).append(rotation).append(shade).append(faces).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Element) == false) {
            return false;
        }
        Element rhs = ((Element) other);
        return new EqualsBuilder().append(from, rhs.from).append(to, rhs.to).append(rotation, rhs.rotation).append(shade, rhs.shade).append(faces, rhs.faces).isEquals();
    }

}