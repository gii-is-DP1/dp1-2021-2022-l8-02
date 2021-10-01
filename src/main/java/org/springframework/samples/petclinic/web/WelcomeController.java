package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {


	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {

          List<Person> people = new ArrayList<Person>();
          Person person1 = new Person();
          Person person2 = new Person();
          Person person3 = new Person();
          Person person4 = new Person();
          Person person5 = new Person();
          Person person6 = new Person();
          person1.setFirstName("Francisco Javier");
          person1.setLastName("Vila");
          person2.setFirstName("Juan Antonio");
          person2.setLastName("García");
          person3.setFirstName("Miguel Ángel");
          person3.setLastName("Gómez");
          person4.setFirstName("Mariano");
          person4.setLastName("Martín");
          person5.setFirstName("Marta");
          person5.setLastName("Reyes");
          person6.setFirstName("José María");
          person6.setLastName("Contreras");
          people.add(person1);
          people.add(person2);
          people.add(person3);
          people.add(person4);
          people.add(person5);
          people.add(person6);
          model.put("people", people);
          model.put("title", "PetClinic");
          model.put("group", "L8-02");

	    return "welcome";
	  }

}
