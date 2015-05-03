package uk.co.bluegecko.core.service.base.version;


/**
 * Partial version details.
 *
 */
public class PartialVersion extends VersionBase
{

	private static final long serialVersionUID = 7417096210238930581L;

	/**
	 * Representation of latest unqualified version.
	 */
	public static final Version LATEST = new PartialVersion();

	/**
	 * Construct a partially qualified version.
	 * 
	 * @param major
	 *            major version
	 * @param minor
	 *            minor version
	 */
	public PartialVersion( final int major, final int minor )
	{
		super( major, minor, null );
	}

	/**
	 * Construct a partially qualified version.
	 * 
	 * @param major
	 *            major version
	 */
	public PartialVersion( final int major )
	{
		super( major, null, null );
	}

	private PartialVersion()
	{
		super( null, null, null );
	}

}
