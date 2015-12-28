package uk.co.bluegecko.examples.models;


import java.time.temporal.TemporalAccessor;

import uk.co.bluegecko.core.lang.ToStringBuilder;
import uk.co.bluegecko.core.model.base.AbstractData;


/**
 * Concrete implementation of {@link Person}.
 */
public class PersonBase extends AbstractData< Long >implements Person
{

	private static final long serialVersionUID = -1337004405653035928L;

	private final Title title;
	private final String firstName;
	private final String lastName;
	private final TemporalAccessor dateOfBirth;

	protected PersonBase( final Long id, final Title title, final String firstName, final String lastName,
			final TemporalAccessor dateOfBirth )
	{
		super( id );

		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Named#getName()
	 */
	@Override
	public String name()
	{
		final StringBuilder buffer = new StringBuilder();
		if ( title != null && title != Title.NONE )
		{
			buffer.append( title );
		}
		buffer.append( firstName );
		buffer.append( lastName );
		return buffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.examples.models.Person#getTitle()
	 */
	@Override
	public Title getTitle()
	{
		return title;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.examples.models.Person#getFirstName()
	 */
	@Override
	public String getFirstName()
	{
		return firstName;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.examples.models.Person#getLastName()
	 */
	@Override
	public String getLastName()
	{
		return lastName;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.examples.models.Person#getDateOfBirth()
	 */
	@Override
	public TemporalAccessor getDateOfBirth()
	{
		return dateOfBirth;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return new ToStringBuilder( this ).append( "key", id() ).append( "title", title )
				.append( "first name", firstName ).append( "last name", lastName )
				.append( "date of birth", dateOfBirth ).build();
	}

}
