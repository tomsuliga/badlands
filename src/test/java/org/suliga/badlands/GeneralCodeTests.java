package org.suliga.badlands;

import static org.junit.Assert.*;
import static org.mockito.Matchers.intThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class GeneralCodeTests {

	@Test
	public void test1() {
		List<String> words = Arrays.asList("Apple", "Ping", "Sun", "Person");
		Predicate<String> firstLetterP = a -> a.startsWith("P");
		Set<String> set = words.stream()
				.filter(firstLetterP)
				.map(String::toUpperCase)
				.collect(Collectors.toSet());
		assertTrue(set.contains("PING"));
		assertTrue(set.contains("PERSON"));
		assertFalse(set.contains("APPLE"));
		assertFalse(set.contains("SUN"));
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void test2() {
		Set<String> words1 = new ConcurrentSkipListSet<String>(Arrays.asList("Amy", "Bob", "Charlie", "Barbara"));
		words1.add("Good");
		
		Set<String> words2 = Collections.unmodifiableSet(new TreeSet<String>(words1));
		assertEquals(5, words1.size());
		assertEquals(5, words2.size());
		words1.add("Bad1");
		assertEquals(6, words1.size());
		assertEquals(5, words2.size());
		
		// UnsupportedOperationException
		words2.add("Bad2");
	}
	
	@Test(expected = NoSuchElementException.class)
	public void test3() {
		Optional<String> color1 = Optional.of("Red");
		Optional<String> color2 = Optional.ofNullable(null);
		
		assertEquals("Red", color1.orElseGet(() -> "Blue"));
		assertEquals("Blue", color2.orElseGet(() -> "Blue"));
		
		assertNotNull(color1.get());
		assertFalse(color2.isPresent());
		
		// NoSuchElementException
		assertNull(color2.get());
	}
	
	@Test
	public void testFibonacciImperative() {
		int first = 0;
		int second = 1;
		int current = 1;
		while (current < 150) {
			System.out.println(current);
			current = first + second;
			first = second;
			second = current;
		}
	}
	
	//@Test
	public void testFibonacciFunctional() {
		Integer[] nums = new Integer[2];
		nums[0] = 0;
		nums[1] = 1;
		
		Supplier<Integer> supplier = () -> { int result = nums[0] + nums[1]; 
											nums[0] = nums[1]; 
											nums[1] = result; 
											return result;};
		Stream.generate(supplier)
				.limit(11)
				//.takeWhile(a -> a < 150)
				.forEach(System.out::println);
	}
	
	@Test
	public void testFibonaciiJava8Streams() {
		Map<Integer,Integer> fibs = new HashMap<>();
		fibs.put(0, 0);
		fibs.put(1, 1);
		
		Stream.iterate(0, counter -> counter+1)
			.map(key -> fibs.computeIfAbsent(key, k -> fibs.get(k-1) + fibs.get(k-2)))
			.limit(13)
			//.takeWhile(a -> a <= 144) // Java 9
			.forEach(System.out::println);
	}
}








