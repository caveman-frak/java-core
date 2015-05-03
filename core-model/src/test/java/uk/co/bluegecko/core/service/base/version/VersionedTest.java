package uk.co.bluegecko.core.service.base.version;


import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static uk.co.bluegecko.core.builder.CollectionBuilder.set;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.Before;
import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class VersionedTest
{

	private Set< Service > set;

	@Before
	public final void setUp()
	{
		set = set( new ServiceV1(), new ServiceV1_0_1(), new ServiceV1_2_4(), new ServiceV2() );
	}

	@Test
	public void testVersioned()
	{
		final Versioned< Service > versioned = new Versioned<>( set );
		final Map< Version, Service > map = versioned.getMap();
		final Version key = new FullVersion( 1, 0, 0 );
		assertThat( map, hasKey( key ) );
		assertThat( map.get( key ), is( instanceOf( ServiceV1.class ) ) );
	}

	@Test
	public void testVersions()
	{
		final Versioned< Service > versioned = new Versioned<>( set );
		final SortedSet< Version > set = versioned.getVersions();
		assertThat(
				set,
				contains( ( Version ) new FullVersion( 1, 0, 0 ), new FullVersion( 1, 0, 1 ),
						new FullVersion( 1, 2, 4 ), new FullVersion( 2, 0, 0 ) ) );
	}

	@Test
	public void testGetFull()
	{
		final Versioned< Service > versioned = new Versioned<>( set );
		assertThat( "0.0.0", versioned.get( new FullVersion( 0, 0, 0 ) ), is( nullValue() ) );
		assertThat( "1.2.4", versioned.get( new FullVersion( 1, 2, 4 ) ), is( instanceOf( ServiceV1_2_4.class ) ) );
		assertThat( "1.5.0", versioned.get( new FullVersion( 1, 5, 0 ) ), is( instanceOf( ServiceV1_2_4.class ) ) );
		assertThat( "2.5.0", versioned.get( new FullVersion( 2, 5, 0 ) ), is( instanceOf( ServiceV2.class ) ) );
	}

	@Test
	public void testGetPartial()
	{
		final Versioned< Service > versioned = new Versioned<>( set );
		assertThat( "0.0", versioned.get( new PartialVersion( 0, 0 ) ), is( nullValue() ) );
		assertThat( "1.1", versioned.get( new PartialVersion( 1, 1 ) ), is( instanceOf( ServiceV1_0_1.class ) ) );
		assertThat( "1", versioned.get( new PartialVersion( 1 ) ), is( instanceOf( ServiceV1_2_4.class ) ) );
		assertThat( "latest", versioned.get( PartialVersion.LATEST ), is( instanceOf( ServiceV2.class ) ) );
	}

	@Test
	public void testGet()
	{
		final Versioned< Service > versioned = new Versioned<>( set );
		assertThat( versioned.get(), is( instanceOf( ServiceV2.class ) ) );
	}

	@SuppressWarnings( "unused" )
	@Test( expected = IllegalArgumentException.class )
	public final void testUnversioned()
	{
		new Versioned<>( set( new ServiceV1(), new ServiceV1_0_1(), new ServiceV1_2_4(), new ServiceV2(),
				new ServiceUnversioned() ) );
		fail();
	}

	@Test
	public final void testString()
	{
		assertThat( "first", VersionBase.fromString( "first" ), is( FullVersion.FIRST ) );
		assertThat( "latest", VersionBase.fromString( "latest" ), is( PartialVersion.LATEST ) );
		assertThat( "1.0.0", VersionBase.fromString( "1.0.0" ), is( ( Version ) new FullVersion( 1, 0, 0 ) ) );
		assertThat( "1.0.1", VersionBase.fromString( "1.0.1" ), is( ( Version ) new FullVersion( 1, 0, 1 ) ) );
		assertThat( "1.1.0", VersionBase.fromString( "1.1.0" ), is( ( Version ) new FullVersion( 1, 1, 0 ) ) );
		assertThat( "1.0", VersionBase.fromString( "1.0" ), is( ( Version ) new PartialVersion( 1, 0 ) ) );
		assertThat( "1", VersionBase.fromString( "1" ), is( ( Version ) new PartialVersion( 1 ) ) );
		assertThat( "1", VersionBase.fromString( "" ), is( PartialVersion.LATEST ) );
	}

	@Test
	public final void testToString()
	{
		assertThat( "", FullVersion.FIRST.toString(), is( "FullVersion[0,0,0]" ) );
		assertThat( "", PartialVersion.LATEST.toString(), is( "PartialVersion[]" ) );
		assertThat( "", new FullVersion( 1, 0, 1 ).toString(), is( "FullVersion[1,0,1]" ) );
		assertThat( "", new PartialVersion( 1, 0 ).toString(), is( "PartialVersion[1,0]" ) );

	}

	protected static abstract class Service
	{

		public int foo( final int value )
		{
			return value * value;
		}

	}

	@VersionInfo( major = 1, minor = 0, point = 0 )
	protected static final class ServiceV1 extends Service
	{ /*do nothing*/}

	@VersionInfo( major = 1, minor = 0, point = 1 )
	protected static final class ServiceV1_0_1 extends Service
	{ /*do nothing*/}

	@VersionInfo( major = 1, minor = 2, point = 4 )
	protected static final class ServiceV1_2_4 extends Service
	{ /*do nothing*/}

	@VersionInfo( major = 2, minor = 0, point = 0 )
	protected static final class ServiceV2 extends Service
	{ /*do nothing*/}

	protected static final class ServiceUnversioned extends Service
	{ /*do nothing*/}

}
