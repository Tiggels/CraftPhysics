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
        "down",
        "up",
        "west",
        "east",
        "north",
        "south"
})
public class Faces {

    @JsonProperty("down")
    private Down down;
    @JsonProperty("up")
    private Up up;
    @JsonProperty("west")
    private West west;
    @JsonProperty("east")
    private East east;
    @JsonProperty("north")
    private North north;
    @JsonProperty("south")
    private South south;

    /**
     * No args constructor for use in serialization
     *
     */
    public Faces() {
    }

    /**
     *
     * @param south
     * @param north
     * @param east
     * @param down
     * @param west
     * @param up
     */
    public Faces(Down down, Up up, West west, East east, North north, South south) {
        this.down = down;
        this.up = up;
        this.west = west;
        this.east = east;
        this.north = north;
        this.south = south;
    }

    /**
     *
     * @return
     * The down
     */
    @JsonProperty("down")
    public Down getDown() {
        return down;
    }

    /**
     *
     * @param down
     * The down
     */
    @JsonProperty("down")
    public void setDown(Down down) {
        this.down = down;
    }

    /**
     *
     * @return
     * The up
     */
    @JsonProperty("up")
    public Up getUp() {
        return up;
    }

    /**
     *
     * @param up
     * The up
     */
    @JsonProperty("up")
    public void setUp(Up up) {
        this.up = up;
    }

    /**
     *
     * @return
     * The west
     */
    @JsonProperty("west")
    public West getWest() {
        return west;
    }

    /**
     *
     * @param west
     * The west
     */
    @JsonProperty("west")
    public void setWest(West west) {
        this.west = west;
    }

    /**
     *
     * @return
     * The east
     */
    @JsonProperty("east")
    public East getEast() {
        return east;
    }

    /**
     *
     * @param east
     * The east
     */
    @JsonProperty("east")
    public void setEast(East east) {
        this.east = east;
    }

    /**
     *
     * @return
     * The north
     */
    @JsonProperty("north")
    public North getNorth() {
        return north;
    }

    /**
     *
     * @param north
     * The north
     */
    @JsonProperty("north")
    public void setNorth(North north) {
        this.north = north;
    }

    /**
     *
     * @return
     * The south
     */
    @JsonProperty("south")
    public South getSouth() {
        return south;
    }

    /**
     *
     * @param south
     * The south
     */
    @JsonProperty("south")
    public void setSouth(South south) {
        this.south = south;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(down).append(up).append(west).append(east).append(north).append(south).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Faces) == false) {
            return false;
        }
        Faces rhs = ((Faces) other);
        return new EqualsBuilder().append(down, rhs.down).append(up, rhs.up).append(west, rhs.west).append(east, rhs.east).append(north, rhs.north).append(south, rhs.south).isEquals();
    }

}