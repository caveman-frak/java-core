/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model;


import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import uk.co.bluegecko.core.model.Messages.Severity;
import uk.co.bluegecko.core.model.base.MessagesBase;
import uk.co.bluegecko.core.model.base.SimpleMessage;
import uk.co.bluegecko.core.test.harness.TestHarness;


@SuppressWarnings( "javadoc" )
public class MessagesTest extends TestHarness
{

	private static final String KEY_1 = "foo1";
	private static final String KEY_2 = "foo2";
	private static final Message MESSAGE_1 = new SimpleMessage( "bar1" );
	private static final Message MESSAGE_2 = new SimpleMessage( "bar2" );
	private Messages messages;

	@Before
	public final void setUp()
	{
		messages = new MessagesBase();
	}

	@Test
	public final void testSeverityEmpty()
	{
		assertThat( messages.severity(), is( Severity.NONE ) );
	}

	@Test
	public final void testSeverityError()
	{
		messages.add( Severity.ERROR, KEY_1, MESSAGE_1 );
		assertThat( messages.severity(), is( Severity.ERROR ) );
	}

	@Test
	public final void testSeverityWarning()
	{
		messages.add( Severity.WARN, KEY_1, MESSAGE_1 );
		assertThat( messages.severity(), is( Severity.WARN ) );
	}

	@Test
	public final void testSeverityErrorAndWarning()
	{
		messages.add( Severity.ERROR, KEY_1, MESSAGE_1 );
		messages.add( Severity.WARN, KEY_2, MESSAGE_2 );
		assertThat( messages.severity(), is( Severity.ERROR ) );
	}

	@Test
	public final void testExceedsWarn()
	{
		messages.add( Severity.ERROR, KEY_1, MESSAGE_1 );
		assertThat( messages.exceeds( Severity.WARN ), is( true ) );
	}

	@Test
	public final void testExceedsError()
	{
		messages.add( Severity.ERROR, KEY_1, MESSAGE_1 );
		assertThat( messages.exceeds( Severity.ERROR ), is( false ) );
	}

	@Test
	public final void testHasSeverityEmpty()
	{
		assertThat( messages.has( Severity.ERROR ), is( false ) );
	}

	@Test
	public final void testPopulateSingleMessages()
	{
		messages.add( Severity.ERROR, KEY_1, MESSAGE_1 );
		assertThat( messages.has( Severity.ERROR ), is( true ) );
		assertThat( messages.has( Severity.ERROR, KEY_1 ), is( true ) );
		assertThat( messages.messages( Severity.ERROR, KEY_1 ), hasSize( 1 ) );
		assertThat( messages.has( Severity.ERROR, KEY_1 ), is( true ) );
		assertThat( messages.messages( Severity.ERROR, KEY_1 ), containsInAnyOrder( MESSAGE_1 ) );
	}

	@Test
	public final void testPopulateMultipleMessages()
	{
		messages.add( Severity.ERROR, KEY_1, MESSAGE_1, MESSAGE_2 );
		assertThat( messages.has( Severity.ERROR ), is( true ) );
		assertThat( messages.has( Severity.ERROR, KEY_1 ), is( true ) );
		assertThat( messages.messages( Severity.ERROR, KEY_1 ), hasSize( 2 ) );
		assertThat( messages.messages( Severity.ERROR, KEY_1 ), containsInAnyOrder( MESSAGE_1, MESSAGE_2 ) );
	}

	@Test
	public final void testPopulateMultipleKeys()
	{
		messages.add( Severity.ERROR, KEY_1, MESSAGE_1 );
		messages.add( Severity.ERROR, KEY_2, MESSAGE_2 );
		assertThat( messages.has( Severity.ERROR ), is( true ) );
		assertThat( messages.has( Severity.ERROR, KEY_1 ), is( true ) );
		assertThat( messages.messages( Severity.ERROR, KEY_1 ), hasSize( 1 ) );
		assertThat( messages.messages( Severity.ERROR, KEY_1 ), containsInAnyOrder( MESSAGE_1 ) );
		assertThat( messages.has( Severity.ERROR, KEY_2 ), is( true ) );
		assertThat( messages.messages( Severity.ERROR, KEY_2 ), hasSize( 1 ) );
		assertThat( messages.messages( Severity.ERROR, KEY_2 ), containsInAnyOrder( MESSAGE_2 ) );
	}

	@Test
	public final void testPopulateMultipleSeverity()
	{
		messages.add( Severity.ERROR, KEY_1, MESSAGE_1 );
		messages.add( Severity.WARN, KEY_2, MESSAGE_2 );
		assertThat( messages.has( Severity.ERROR ), is( true ) );
		assertThat( messages.has( Severity.ERROR, KEY_1 ), is( true ) );
		assertThat( messages.messages( Severity.ERROR, KEY_1 ), hasSize( 1 ) );
		assertThat( messages.messages( Severity.ERROR, KEY_1 ), containsInAnyOrder( MESSAGE_1 ) );
		assertThat( messages.has( Severity.WARN, KEY_2 ), is( true ) );
		assertThat( messages.messages( Severity.WARN, KEY_2 ), hasSize( 1 ) );
		assertThat( messages.messages( Severity.WARN, KEY_2 ), containsInAnyOrder( MESSAGE_2 ) );
	}

	@Test
	public final void testClearAll()
	{
		messages.add( Severity.ERROR, KEY_1, MESSAGE_1 );
		assertThat( messages.has( Severity.ERROR, KEY_1 ), is( true ) );
		messages.clear();
		assertThat( messages.has( Severity.ERROR ), is( false ) );
	}

	@Test
	public final void testClearBySeverity()
	{
		messages.add( Severity.ERROR, KEY_1, MESSAGE_1 );
		assertThat( messages.has( Severity.ERROR, KEY_1 ), is( true ) );
		messages.clear( Severity.ERROR );
		assertThat( messages.has( Severity.ERROR ), is( false ) );
	}

	@Test
	public final void testClearBySeverityAndKey()
	{
		messages.add( Severity.ERROR, KEY_1, MESSAGE_1 );
		messages.add( Severity.ERROR, KEY_2, MESSAGE_2 );
		assertThat( messages.has( Severity.ERROR, KEY_1 ), is( true ) );
		assertThat( messages.has( Severity.ERROR, KEY_2 ), is( true ) );
		messages.clear( Severity.ERROR, KEY_1 );
		assertThat( messages.has( Severity.ERROR ), is( true ) );
		assertThat( messages.has( Severity.ERROR, KEY_1 ), is( false ) );
		assertThat( messages.has( Severity.ERROR, KEY_2 ), is( true ) );
	}

	@Test
	public final void testToStringEmpty()
	{
		assertThat( messages.toString(), is( "MessagesBase[]" ) );
	}

	@Test
	public final void testToStringOneKey()
	{
		messages.add( Severity.ERROR, KEY_1, MESSAGE_1 );
		assertThat( messages.toString(), is( "MessagesBase[\n\tERROR\tfoo1 : bar1\n]" ) );
	}

	@Test
	public final void testToStringOneKeyMultipleText()
	{
		messages.add( Severity.ERROR, KEY_1, MESSAGE_1, MESSAGE_2 );
		assertThat( messages.toString(), is( "MessagesBase[\n\tERROR\tfoo1 : bar1; bar2\n]" ) );
	}

	@Test
	public final void testToStringMultipleKeys()
	{
		messages.add( Severity.ERROR, KEY_1, MESSAGE_1 );
		messages.add( Severity.ERROR, KEY_2, MESSAGE_2 );
		assertThat( messages.toString(), is( "MessagesBase[\n\tERROR\n\t\tfoo1 : bar1\n\t\tfoo2 : bar2\n]" ) );
	}

	@Test
	public final void testToStringMultipleSeverity()
	{
		messages.add( Severity.ERROR, KEY_1, MESSAGE_1 );
		messages.add( Severity.WARN, KEY_2, MESSAGE_2 );
		assertThat( messages.toString(), is( "MessagesBase[\n\tERROR\tfoo1 : bar1\n\tWARN\tfoo2 : bar2\n]" ) );
	}

}
