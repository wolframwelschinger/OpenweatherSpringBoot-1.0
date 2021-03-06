package de.ww.openweather.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Wird fuer Tests benoetigt um die Paremeter fuer getForObject-Methode bereit zu stellen
 * @author Stefan Ruback
 *
 * @param <T> REST-Template
 */
public class RestPageImpl<T> extends PageImpl<T>{

	private static final long serialVersionUID = 1L;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RestPageImpl(@JsonProperty("content") List<T> content,
                        @JsonProperty("number") int page,
                        @JsonProperty("size") int size,
                        @JsonProperty("last") boolean last,
                        @JsonProperty("first") boolean first,
                        @JsonProperty("sort") @JsonDeserialize(using=CustomSortDeserializer.class) Sort sort,
                        @JsonProperty("numberOfElements") int numberOfElements,
                        @JsonProperty("totalPages") int totalPages,
                        @JsonProperty("totalElements") long total) {
        super(content, new PageRequest(page, size), total);
    }

    public RestPageImpl(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public RestPageImpl(List<T> content) {
        super(content);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public RestPageImpl() {
        super(new ArrayList());
    }
}