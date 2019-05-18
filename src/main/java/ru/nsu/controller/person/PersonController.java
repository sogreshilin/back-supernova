package ru.nsu.controller.person;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.entity.Person;
import ru.nsu.service.PersonService;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @PatchMapping("/{personId}/favourites")
    public Person addFavourite(@PathVariable long personId, @RequestBody FavouriteDto favouriteDto) {
        return personService.add(personId, favouriteDto.getType());
    }
}
