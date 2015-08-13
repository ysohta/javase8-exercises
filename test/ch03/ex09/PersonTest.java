package ch03.ex09;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

public class PersonTest {

	@Test
	public void testLexicographicComparator1() {
		Comparator<Person> comparator = Person.lexicographicComparator("lastname", "firstname");

		Person dw = new Person("Daniel", "Woods", 25);
		Person cs = new Person("Chris", "Sharma", 33);
		Person dd = new Person("Daniel", "Dulac", 44);
		Person tw = new Person("Tiger", "Woods", 39);
		Person[] persons = { dw, cs, dd, tw, };
		Arrays.sort(persons, comparator);

		assertArrayEquals(new Person[] { dd, cs, dw, tw }, persons);
	}

	@Test
	public void testLexicographicComparator2() {
		Comparator<Person> comparator = Person.lexicographicComparator("age", "firstname", "lastname");

		Person dw = new Person("Daniel", "Woods", 25);
		Person cs = new Person("Chris", "Sharma", 33);
		Person dd = new Person("Daniel", "Dulac", 44);
		Person tw = new Person("Tiger", "Woods", 39);
		Person[] persons = { dw, cs, dd, tw, };
		Arrays.sort(persons, comparator);

		assertArrayEquals(new Person[] { dw, cs, tw, dd }, persons);
	}

	@Test(expected = RuntimeException.class)
	public void testLexicographicComparatorInvalidFieldName() {
		Comparator<Person> comparator = Person.lexicographicComparator("INVALID");

		Person aj = new Person("Alex", "Johnson", 25);
		Person ap = new Person("Alex", "Puccio", 25);
		
		comparator.compare(aj, ap);
	}

	@Test
	public void testLexicographicComparatorSame() {
		Comparator<Person> comparator = Person.lexicographicComparator("age", "firstname");

		Person aj = new Person("Alex", "Johnson", 25);
		Person ap = new Person("Alex", "Puccio", 25);

		assertThat(comparator.compare(aj, aj), is(0));
		assertThat(comparator.compare(aj, ap), is(0));
		assertThat(comparator.compare(ap, aj), is(0));
	}
}
