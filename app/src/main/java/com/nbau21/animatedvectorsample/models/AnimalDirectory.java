package com.nbau21.animatedvectorsample.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalDirectory {

    public static final List<Animal> ANIMALS = new ArrayList<>();
    public static final Map<String, Animal> ANIMAL_MAP = new HashMap<>();

    private static final int COUNT = 25;

    static {
        for (int i = 1; i <= COUNT; i++) {
            addAnimal(createAnimalEntry(i));
        }
    }

    private static void addAnimal(Animal animal) {
        ANIMALS.add(animal);
        ANIMAL_MAP.put(animal.id, animal);
    }

    private static Animal createAnimalEntry(int position) {
        return animalFactory(position);
    }

    public static class Animal {
        public final String id;
        public final String type;

        public Animal(String id, String type) {
            this.id = id;
            this.type = type;
        }
    }

    private static Animal animalFactory(int position) {
        if (position % 2 == 0) {
            return new Animal(position + "", "Cat");
        } else {
            return new Animal(position + "", "Dog");
        }
    }
}
