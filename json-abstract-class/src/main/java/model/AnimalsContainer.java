package model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class AnimalsContainer {
    private final List<Animal> animals = new LinkedList<>();
}