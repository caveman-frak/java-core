package uk.co.bluegecko.core.service.base.version;


import uk.co.bluegecko.core.lang.CompareToBuilder;
import uk.co.bluegecko.core.lang.EqualsBuilder;
import uk.co.bluegecko.core.lang.HashCodeBuilder;
import uk.co.bluegecko.core.lang.ToStringBuilder;


/**
 *
 */
public abstract class VersionBase implements Version
{

	private static final long serialVersionUID = 204234984620801108L;
	private final Integer major;
	private final Integer minor;
	private final Integer point;
	private final int hashCode;
	private final String toString;

	protected VersionBase( final Integer major, final Integer minor, final Integer point )
	{
		super();

		this.major = major;
		this.minor = minor;
		this.point = point;

		hashCode = new HashCodeBuilder().append( major ).append( minor ).append( point ).hashCode();
		toString = new ToStringBuilder( this ).appendNotNull( major ).appendNotNull( minor ).appendNotNull( point )
				.toString();
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.spring.ioc.version.Version#getMajor()
	 */
	@Override
	public int major()
	{
		return hasMajor() ? major : Integer.MAX_VALUE;
	}

	/**
	 * Has major version been set.
	 *
	 * @return true if major version has been set
	 */
	public boolean hasMajor()
	{
		return major != null;
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.spring.ioc.version.Version#getMajor()
	 */
	@Override
	public int minor()
	{
		return hasMinor() ? minor : Integer.MAX_VALUE;
	}

	/**
	 * Has minor version been set.
	 *
	 * @return true if minor version has been set
	 */
	public boolean hasMinor()
	{
		return minor != null;
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.spring.ioc.version.Version#getMajor()
	 */
	@Override
	public int point()
	{
		return hasPoint() ? point : Integer.MAX_VALUE;
	}

	/**
	 * Has point version been set.
	 *
	 * @return true if point version has been set
	 */
	public boolean hasPoint()
	{
		return point != null;
	}

	@Override
	public final int compareTo( final Version other )
	{
		return new CompareToBuilder().append( major(), other.major() ).append( minor(), other.minor() )
				.append( point(), other.point() ).toComparison();
	}

	@Override
	public final String toString()
	{
		return toString;
	}

	@Override
	public final int hashCode()
	{
		return hashCode;
	}

	@Override
	public final boolean equals( final Object obj )
	{
		final EqualsBuilder< ? extends Version > builder = new EqualsBuilder<>( this, obj );
		if ( !builder.isResolved() )
		{
			final VersionBase other = ( VersionBase ) obj;
			builder.append( major(), other.major() ).append( minor(), other.minor() )
					.append( point(), other.point() );
		}
		return builder.isEquals();
	}

	/**
	 * Convert a string to a version.
	 *
	 * @param version
	 *            the string representation
	 * @return the version
	 */
	public static final Version fromString( final String version )
	{
		if ( version.equalsIgnoreCase( "FIRST" ) )
		{
			return FullVersion.FIRST;
		}
		if ( version.equalsIgnoreCase( "LATEST" ) || version.isEmpty() )
		{
			return PartialVersion.LATEST;
		}
		final String[] parts = version.split( "\\." );
		if ( parts.length == 1 )
		{
			return new PartialVersion( Integer.valueOf( parts[0] ) );
		}
		else if ( parts.length == 2 )
		{
			return new PartialVersion( Integer.valueOf( parts[0] ), Integer.valueOf( parts[1] ) );
		}
		else
		{
			return new FullVersion( Integer.valueOf( parts[0] ), Integer.valueOf( parts[1] ),
					Integer.valueOf( parts[2] ) );
		}
	}
}
