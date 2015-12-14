package uk.co.bluegecko.examples.models;


import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.TemporalAccessor;

import uk.co.bluegecko.core.builder.Builder;
import uk.co.bluegecko.core.lang.ToStringBuilder;


/**
 * Builder class for constructing {@link Person} object.
 */
public class PersonBuilder implements Builder< Person, PersonBuilder >
{

	private Long key;
	private Title title;
	private String firstName;
	private String lastName;
	private TemporalAccessor dateOfBirth;

	/**
	 * Default constructor.
	 */
	public PersonBuilder()
	{
		super();
	}

	/**
	 * Copy constructor.
	 *
	 * @param person
	 *            initial values for builder
	 */
	public PersonBuilder( final Person person )
	{
		this();

		from( person );
	}

	/**
	 * Set the key for the person.
	 *
	 * @param key
	 *            update with key
	 * @return updated PersonBuilder
	 */
	public PersonBuilder withKey( final Long key )
	{
		this.key = key;
		return this;
	}

	/**
	 * Set the title for the person.
	 *
	 * @param title
	 *            update with title
	 * @return updated PersonBuilder
	 */
	public PersonBuilder withTitle( final Title title )
	{
		this.title = title;
		return this;
	}

	/**
	 * Set the first name for the person.
	 *
	 * @param firstName
	 *            update with first name
	 * @return updated PersonBuilder
	 */
	public PersonBuilder withFirstName( final String firstName )
	{
		this.firstName = firstName;
		return this;
	}

	/**
	 * Set the last name for the person.
	 *
	 * @param lastName
	 *            update with last name
	 * @return updated PersonBuilder
	 */
	public PersonBuilder withLastName( final String lastName )
	{
		this.lastName = lastName;
		return this;
	}

	/**
	 * Set the date of birth, as a {@link LocalDate}.
	 *
	 * @param dateOfBirth
	 *            update with date of birth
	 * @return updated PersonBuilder
	 */
	public PersonBuilder withDateOfBirth( final LocalDate dateOfBirth )
	{
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	/**
	 * Set the date of birth, as a {@link MonthDay}.
	 *
	 * @param dateOfBirth
	 *            update with day and month of birth
	 * @return updated PersonBuilder
	 */
	public PersonBuilder withDateOfBirth( final MonthDay dateOfBirth )
	{
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.builder.Builder#build()
	 */
	@Override
	public Person build()
	{
		return new PersonBase( key, title, firstName, lastName, dateOfBirth );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.builder.Builder#copy()
	 */
	@Override
	public PersonBuilder copy()
	{
		final PersonBuilder copy = new PersonBuilder();
		copy.key = key;
		copy.title = title;
		copy.firstName = firstName;
		copy.lastName = lastName;
		copy.dateOfBirth = dateOfBirth;
		return copy;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.builder.Builder#reset()
	 */
	@Override
	public PersonBuilder reset()
	{
		key = null;
		title = Title.NONE;
		firstName = "";
		lastName = "";
		dateOfBirth = null;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.builder.Builder#from(java.lang.Object)
	 */
	@Override
	public PersonBuilder from( final Person person )
	{
		key = person.getKey();
		title = person.getTitle();
		firstName = person.getFirstName();
		lastName = person.getLastName();
		dateOfBirth = person.getDateOfBirth();
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return new ToStringBuilder( this ).appendNotNull( "key", key ).appendNotNull( "title", title )
				.appendNotBlank( "first name", firstName ).appendNotBlank( "last name", lastName )
				.appendNotNull( "date of birth", dateOfBirth ).build();
	}

}
