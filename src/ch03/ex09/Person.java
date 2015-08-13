package ch03.ex09;

import java.lang.reflect.Field;
import java.util.Comparator;

/**
 * This class has method to create a comparator to compare according to the
 * specified field names and their order.
 * 
 * @author yukiohta
 *
 */
public class Person implements Comparable<Person> {
	private String firstname;
	private String lastname;
	private int age;

	public Person(String firstname, String lastname, int age) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
	}

	/**
	 * Creates a comparator based on the field names and the order of Person
	 * class.
	 * 
	 * @param fieldNames
	 *            names of the field
	 * @return comparator
	 * @throws RuntimeException
	 *             if class does not have the name of the field
	 */
	@SuppressWarnings("unchecked")
	public static Comparator<Person> lexicographicComparator(String... fieldNames) {
		return (e1, e2) -> {
			for (String name : fieldNames) {
				try {
					Field f = Person.class.getDeclaredField(name);
					Comparable<Object> f1 = (Comparable<Object>) f.get(e1);
					Comparable<Object> f2 = (Comparable<Object>) f.get(e2);
					int val = f1.compareTo(f2);
					if (val != 0)
						return val;
				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}

			return 0;
		};
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public int getAge() {
		return age;
	}

	@Override
	public int compareTo(Person o) {
		int val = 0;
		val = firstname.compareTo(o.firstname);
		if (val != 0) {
			return val;
		}

		val = lastname.compareTo(o.lastname);
		if (val != 0) {
			return val;
		}

		return Integer.compare(age, o.age);
	}

	@Override
	public String toString() {
		return "Person [firstname=" + firstname + ", lastname=" + lastname + ", age=" + age + "]";
	}
}
