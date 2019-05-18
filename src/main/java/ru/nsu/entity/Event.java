package ru.nsu.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.nsu.entity.embeddable.Interval;
import ru.nsu.entity.enums.EventType;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @OneToOne
    private Person author;

    @ElementCollection
    @Column(name = "type")
    @CollectionTable(name = "event_type", joinColumns = @JoinColumn(name = "event_id"))
    @Enumerated(EnumType.STRING)
    private Set<EventType> types = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "event_person",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    @Enumerated(value = EnumType.STRING)
    private List<Person> members = new ArrayList<>();

    @Embedded
    @AttributeOverride(name = "from", column = @Column(name = "from_datetime"))
    @AttributeOverride(name = "to", column = @Column(name = "to_datetime"))
    private Interval interval;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column
    private Double rating;

    @Column
    private String siteUrl;

    @Column
    private String phone;

    @Column
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "person_event_likes",
        joinColumns = {@JoinColumn(name = "event_id")},
        inverseJoinColumns = {@JoinColumn(name = "person_id")}
    )
    private List<Person> likedPersons = new ArrayList<>();
}
