package kr.or.ddit.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocalDateReduceTest {
	@Test
	public void test() throws JsonProcessingException {
		LocalDate ld1 = LocalDate.of(2023, 3, 1);
		LocalDate ld2 = LocalDate.of(2023, 3, 1);
		
		Set<LocalDate> dateSet = new  LinkedHashSet<>();
		dateSet.add(ld1);
		dateSet.add(ld2);
		log.info("set : {}", new ObjectMapper()	
								.registerModule(new JavaTimeModule())
								.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
								.writeValueAsString(dateSet));
	}
	
}
