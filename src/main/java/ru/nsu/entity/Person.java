package ru.nsu.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.nsu.entity.enums.EventType;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String vkId;

    @Column
    private String lastName;

    @Column
    private String firstName;

    @ElementCollection
    @Column(name = "type")
    @CollectionTable(name = "person_favourite_event_type", joinColumns = @JoinColumn(name = "person_id"))
    @Enumerated(EnumType.STRING)
    private Set<EventType> favouriteEventTypes = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "person_favourite_event",
        joinColumns = {@JoinColumn(name = "person_id")},
        inverseJoinColumns = {@JoinColumn(name = "event_id")}
    )
    private List<Event> favouriteEvents = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Event> createdEvents = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "person_disliked_event",
        joinColumns = {@JoinColumn(name = "person_id")},
        inverseJoinColumns = {@JoinColumn(name = "event_id")}
    )
    private List<Event> dislikedEvents = new ArrayList<>();
}
