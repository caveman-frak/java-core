package uk.co.bluegecko.core.service.base.version;


/**
 * Fully qualified version information.
 */
public class FullVersion extends VersionBase
{

	private static final long serialVersionUID = 7417096210238930581L;

	/**
	 * Starting version.
	 */
	public static final Version FIRST = new FullVersion( 0, 0, 0 );

	/**
	 * Construct a fully qualified version.
	 *
	 * @param major
	 *            major version
	 * @param minor
	 *            minor version
	 * @param point
	 *            point version
	 */
	public FullVersion( final int major, final int minor, final int point )
	{
		super( major, minor, point );
	}

	/**
	 * Construct a fully qualified version from the {@link VersionInfo}
	 *
	 * @param versionInfo
	 *            source of version details
	 */
	public FullVersion( final VersionInfo versionInfo )
	{
		this( versionInfo.major(), versionInfo.minor(), versionInfo.point() );
	}

}
