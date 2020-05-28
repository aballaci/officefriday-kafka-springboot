package com.springboot.model;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class ModelGenerator {

    static Faker faker = new Faker(new Locale("de"));

    public static Mitarbeiter generateMitarbeiter(int id){
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setAlter(String.valueOf(faker.random().nextInt(18, 65)));
        mitarbeiter.setName(faker.name().firstName());
        mitarbeiter.setNachname(faker.name().lastName());
        mitarbeiter.setUserId(String.valueOf(id));
        Anscrift anscrift = new Anscrift();
        anscrift.setHousenummer(faker.address().buildingNumber());
        anscrift.setEmail(mitarbeiter.getName().toLowerCase() + "@officefriday.com");
        anscrift.setPlz(faker.numerify("90###"));
        anscrift.setStadt(faker.address().city());
        anscrift.setStrasse(faker.address().streetName());
        mitarbeiter.setAnscrift(anscrift);
        return mitarbeiter;
    }
}
