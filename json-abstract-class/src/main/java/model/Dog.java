package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dog implements Animal {
    private String name;
    private Integer ageInYears;

//    @JsonCreator
//    public Dog(@JsonProperty("name") String name, Integer ageInYears) {
//        this.name = name;
//        this.ageInYears = ageInYears;
//    }
}
