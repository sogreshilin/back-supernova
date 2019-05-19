package ru.nsu.recommender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.nsu.entity.Event;
import ru.nsu.entity.Person;

public class TransformedData {

    private Map<Long, Map<Long, Double>> data;
    private Map<Long, Map<Long, Double>> transposed_data;
    private Map<Long, Double> personMeanRatings;
    private Map<Long, Double> eventMeanRatings;
    private List<Person> personList;

    private int numOfRatings;


    public TransformedData(List<Person> personList) {
        this.personList = personList;
        data = new HashMap<>();
        personMeanRatings = new HashMap<>();
        eventMeanRatings = new HashMap<>();
        transposed_data = new HashMap<>();

        numOfRatings = 0;
        for (Person person : personList) {
            long personId = person.getId();
            List<Event> likedEvents = person.getFavouriteEvents();
            for (Event event : likedEvents) {
                long eventId = event.getId();
                double weight = 1;
                numOfRatings++;

                if (data.containsKey(personId)) {
                    data.get(personId).put(eventId, weight);
                } else {
                    Map<Long, Double> userRatings = new HashMap<>();
                    userRatings.put(eventId, weight);
                    data.put(personId, userRatings);
                }

                if (transposed_data.containsKey(eventId)) {
                    transposed_data.get(eventId).put(personId, weight);
                } else {
                    Map<Long, Double> userRatings = new HashMap<>();
                    userRatings.put(personId, weight);
                    transposed_data.put(eventId, userRatings);
                }

                if (personMeanRatings.containsKey(personId)) {
                    personMeanRatings.put(personId, personMeanRatings.get(personId) + weight);
                } else {
                    personMeanRatings.put(personId, weight);
                }
                if (eventMeanRatings.containsKey(eventId)) {
                    eventMeanRatings.put(eventId, eventMeanRatings.get(eventId) + weight);
                } else {
                    eventMeanRatings.put(eventId, weight);
                }
            }

            List<Event> dislikedEvents = person.getDislikedEvents();
            for (Event event : dislikedEvents) {
                long eventId = event.getId();
                double weight = 0;
                numOfRatings++;

                if (data.containsKey(personId)) {
                    data.get(personId).put(eventId, weight);
                } else {
                    Map<Long, Double> userRatings = new HashMap<>();
                    userRatings.put(eventId, weight);
                    data.put(personId, userRatings);
                }

                if (transposed_data.containsKey(eventId)) {
                    transposed_data.get(eventId).put(personId, weight);
                } else {
                    Map<Long, Double> userRatings = new HashMap<>();
                    userRatings.put(personId, weight);
                    transposed_data.put(eventId, userRatings);
                }

                if (personMeanRatings.containsKey(personId)) {
                    personMeanRatings.put(personId, personMeanRatings.get(personId) + weight);
                } else {
                    personMeanRatings.put(personId, weight);
                }
                if (eventMeanRatings.containsKey(eventId)) {
                    eventMeanRatings.put(eventId, eventMeanRatings.get(eventId) + weight);
                } else {
                    eventMeanRatings.put(eventId, weight);
                }
            }
        }

        for (Long personId : personMeanRatings.keySet())
            personMeanRatings.put(personId, personMeanRatings.get(personId) / data.get(personId).size());
    }

    public double getWeight(Long personId,
                            Long eventId) {
        if (data.containsKey(personId)) {
            Map<Long, Double> userRatings = data.get(personId);
            if (userRatings.containsKey(eventId)) {
                return userRatings.get(eventId);
            } else {
                return 0;
            }
        } else {
            throw new IllegalArgumentException(String.format("Training data does not contain any rating for person %d.", personId));
        }
    }

    public double getPersonMeanRating(Long personId) {
        return personMeanRatings.getOrDefault(personId, 3.5);
    }

    public double getEventMeanRating(Long eventId) {
        return eventMeanRatings.getOrDefault(eventId, 3.5);
    }

    public Set<Long> getPersons() {
        return data.keySet();
    }

    public Set<Long> getEvents(Long personId) {
        return data.get(personId).keySet();
    }

    public Set<Long> getAllEvents() {
        return transposed_data.keySet();
    }

    public int numTrainRatings() {
        return numOfRatings;
    }

    public int numTrainRatingsForUser(Long personId) {
        return data.get(personId).size();
    }

    public int numTrainRatingsForItem(Long eventId) {
        return transposed_data.get(eventId).size();
    }

    public List<Person> getPersonList() {
        return personList;
    }
}
