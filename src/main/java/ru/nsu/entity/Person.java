package ru.nsu.entity;

import java.util.Set;

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
    @CollectionTable(name = "person_favourites", joinColumns = @JoinColumn(name = "person_id"))
    @Enumerated(EnumType.STRING)
    private Set<EventType> favourites;
}
