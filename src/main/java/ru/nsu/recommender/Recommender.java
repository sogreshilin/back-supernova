package ru.nsu.recommender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.nsu.entity.Event;
import ru.nsu.entity.Person;
import ru.nsu.entity.enums.EventType;

public class Recommender {

    private TransformedData transformedData;
    private int NUM_FEATURES;
    private static final int MIN_ITER = 100;
    private static final double MIN_IMPROVEMENT = 0.0001;
    private double ALPHA = 0.001;
    private static final double BETA = 0.015;
    private double glAverage;
    private Map<Long, Double>[] U;
    private Map<Long, Double>[] V;
    private Map<Long, Double> personAvg;
    private Map<Long, Double> eventAvg;
    private List<Event> eventList;

    @SuppressWarnings("unchecked")
    public Recommender(TransformedData transformedData,
                       List<Event> eventList) {

        this.transformedData = transformedData;
        this.eventList = eventList;

        NUM_FEATURES = EventType.values().length;

        List<Person> personList = transformedData.getPersonList();

        U = new HashMap[NUM_FEATURES];
        for (int f = 0; f < NUM_FEATURES; f++) {
            U[f] = new HashMap<>();
            for (Person person : personList) {
                if (person.getFavouriteEventTypes().contains(EventType.values()[f])) {
                    U[f].put(person.getId(), 1.d);
                } else {
                    U[f].put(person.getId(), 0.d);
                }
            }
        }

        NUM_FEATURES = EventType.values().length;

        V = new HashMap[NUM_FEATURES];

        for (int f = 0; f < NUM_FEATURES; f++) {
            V[f] = new HashMap<>();
            for (Event event : eventList) {
                if (event.getTypes().contains(EventType.values()[f])) {
                    V[f].put(event.getId(), 1.d);
                } else {
                    V[f].put(event.getId(), 0.d);
                }
            }
        }

        computeAvgs();
    }

    public void train() {

        double rmse = 2.0, rmse_last = 10.0, sq, R, P, err, cf, mf, bi, bu;
        int numTotalRatings = transformedData.numTrainRatings();

        for (int f = 0; f < NUM_FEATURES; f++) {
            for (int step = 0; (step < MIN_ITER) || (rmse <= rmse_last - MIN_IMPROVEMENT); step++) {
                sq = 0.0;
                rmse_last = rmse;

                for (Long personId : transformedData.getPersons()) {
                    for (Long eventId : transformedData.getEvents(personId)) {
                        R = transformedData.getWeight(personId, eventId);

                        P = dotProduct(personId, eventId) + eventAvg.get(eventId) + personAvg.get(personId) + glAverage;
                        err = R - P;

                        sq += err * err;

                        cf = U[f].get(personId);
                        mf = V[f].get(eventId);

                        U[f].put(personId, cf + ALPHA * (err * mf - BETA * cf));
                        V[f].put(eventId, mf + ALPHA * (err * cf - BETA * mf));

                        bi = eventAvg.get(eventId);
                        bu = personAvg.get(personId);
                        eventAvg.put(eventId, bi + ALPHA * (err - BETA * bi));

                        personAvg.put(personId, bu + ALPHA * (err - BETA * bu));
                    }
                }
                rmse = Math.sqrt(sq / numTotalRatings);
            }
        }
    }

    public Map<Event, Double> predict(Long personId) {
        Map<Event, Double> predictions = new HashMap<>();

        for (Event event : eventList) {
            if (!transformedData.getEvents(personId).contains(event.getId())) {
                predictions.put(event, predict(personId, event.getId()));
            }
        }

        return predictions;
    }

    private double predict(Long personId,
                           Long eventId) {
        if (!U[0].containsKey(personId) || !V[0].containsKey(eventId) || !eventAvg.containsKey(eventId) || !personAvg.containsKey(personId)) {
            return ceilPrediction(dotProduct(personId, eventId));
        }
        return ceilPrediction(glAverage + eventAvg.get(eventId) + personAvg.get(personId) + dotProduct(personId, eventId));
    }

    private void computeAvgs() {
        personAvg = new HashMap<>();
        glAverage = 0.0;
        for (Long personId : transformedData.getPersons()) {
            int ratingCount = transformedData.numTrainRatingsForUser(personId);
            double ratingSum = transformedData.getPersonMeanRating(personId) * ratingCount;
            glAverage += ratingSum;
            personAvg.put(personId, transformedData.getPersonMeanRating(personId));
        }
        glAverage /= transformedData.numTrainRatings();

        // Find PseudoAvg for each item
        eventAvg = new HashMap<>();
        for (Long eventId : transformedData.getAllEvents()) {
            eventAvg.put(eventId, transformedData.getEventMeanRating(eventId));
        }
    }

    private double dotProduct(Long user,
                              Long item) {
        double sum = 0.0;

        for (int f = 0; f < NUM_FEATURES; f++) {
            sum += U[f].get(user) * V[f].get(item);
        }
        return sum;
    }

    private double ceilPrediction(double prediction) {
        if (prediction > 1.d) {
            prediction = 1.d;
        } else if (prediction < 0.d) {
            prediction = 0.d;
        }
        return prediction;
    }
}
