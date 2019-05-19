package ru.nsu.service.integration.meetup.dto.group;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import lombok.Value;
import ru.nsu.entity.enums.EventType;

@Value
public class GetGroupResponseDto {
    private static final Map<String, EventType> EVENT_TYPES = ImmutableMap.<String, EventType>builder()
        .put("ARTS", EventType.ARTS)
        .put("BELIEFS", EventType.BELIEFS)
        .put("BOOK CLUBS", EventType.BOOK_CLUBS)
        .put("CAREER BUSINESS", EventType.CAREER_BUSINESS)
        .put("DANCE", EventType.DANCE)
        .put("FAMILY", EventType.FAMILY)
        .put("FASHION & BEAUTY", EventType.FASHION_AND_BEAUTY)
        .put("FILM", EventType.FILM)
        .put("FOOD & DRINK", EventType.FOOD_AND_DRINK)
        .put("HEALTH & WELLNESS", EventType.HEALTH_AND_WELLNESS)
        .put("HOBBIES & CRAFTS", EventType.HOBBIES_AND_CRAFTS)
        .put("LGBTQ", EventType.LGBTQ)
        .put("LANGUAGE & CULTURE", EventType.LANGUAGE_AND_CULTURE)
        .put("LEARNING", EventType.LEARNING)
        .put("MOVEMENTS", EventType.MOVEMENTS)
        .put("MUSIC", EventType.MUSIC)
        .put("OUTDOORS & ADVENTURE", EventType.OUTDOORS_AND_ADVENTURE)
        .put("PETS", EventType.PETS)
        .put("PROGRAMMING", EventType.PROGRAMMING)
        .put("PHOTOGRAPHY", EventType.PHOTOGRAPHY)
        .put("SCI_FI & GAMES", EventType.SCI_FI_AND_GAMES)
        .put("SOCIAL", EventType.SOCIAL)
        .put("SPORTS & FITNESS", EventType.SPORTS_AND_FITNESS)
        .put("TECH", EventType.TECH)
        .put("WRITING", EventType.WRITING)
        .build();

    private final OrganizerDto organizer;

    private final EventType type;

    private final String imageUrl;

    @JsonCreator
    public GetGroupResponseDto(
        @JsonProperty("organizer") OrganizerDto organizerDto,
        @JsonProperty("category") CategoryDto categoryDto,
        @JsonProperty("key_photo") PhotoDto photoDto
    ) {
        this.organizer = organizerDto;
        this.type = categoryDto == null ? null : EVENT_TYPES.get(categoryDto.getEventType().toUpperCase());
        this.imageUrl = photoDto.getUrl();
    }
}
