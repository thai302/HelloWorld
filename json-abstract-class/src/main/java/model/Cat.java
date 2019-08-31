package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cat implements Animal {
    private String name;
    private Integer livesLeft;

//    @JsonCreator
//    public Cat(@JsonProperty("name") String par1, @JsonProperty("livesLeft") Integer par2) {
//        this.name = par1;
//        this.livesLeft = par2;
//    }

    @JsonCreator
    public Cat(@JsonProperty("livesLeft") Integer livesLeft){
//        this.name = name;
        this.livesLeft = livesLeft;
    }
}