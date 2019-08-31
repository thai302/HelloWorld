import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Animal;
import model.AnimalsContainer;
import model.Cat;
import model.Dog;

import java.util.List;

public class AnimalsTest {
    public static void main(String[] args) throws Exception {
        // given
        String json = "{\n" +
                "  \"animals\": [\n" +
                "    {\n" +
                "      \"name\": \"Szarik\",\n" +
                "      \"ageInYears\": 3,\n" +
                "      \"type\": \"dog\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Filemon\",\n" +
                "      \"livesLeft\": 6,\n" +
                "      \"type\": \"cat\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        AnimalsContainer animalsContainer =  new AnimalsContainer();
        Cat cat = new Cat(1);
        cat.setName("cat name");
        animalsContainer.getAnimals().add(cat);

        Dog dog = new Dog();
        dog.setName("dog name");
        animalsContainer.getAnimals().add(dog);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        json = objectMapper.writeValueAsString(animalsContainer);

        // when
        List<Animal> animals = objectMapper.readValue(json, AnimalsContainer.class).getAnimals();
        System.out.println(animals);
        // then
//        assertEquals(2, animals.size());
//        assertEquals(model.Dog.class, animals.get(0).getClass());
//        assertEquals(3, ((model.Dog) animals.get(0)).getAgeInYears().intValue());
//        assertEquals(model.Cat.class, animals.get(1).getClass());
//        assertEquals(6, ((model.Cat) animals.get(1)).getLivesLeft().intValue());
    }
}
