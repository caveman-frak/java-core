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
import uk.co.bluegecko.core.test.harness.TestHarness;


@SuppressWarnings( "javadoc" )
public class MessagesTest extends TestHarness
{

	private static final String KEY_1 = "foo1";
	private static final String KEY_2 = "foo2";
	private static final String MESSAGE_1 = "bar1";
	private static final String MESSAGE_2 = "bar2";
	private Messages messages;

	@Before
	public final void setUp()
	{
		messages = new MessagesBase();
	}

	@Test
	public final void testSeverityEmpty()
	{
		assertThat( messages.getSeverity(), is( Severity.NONE ) );
	}

	@Test
	public final void testSeverityError()
	{
		messages.addMessages( Severity.ERROR, KEY_1, MESSAGE_1 );
		assertThat( messages.getSeverity(), is( Severity.ERROR ) );
	}

	@Test
	public final void testSeverityWarning()
	{
		messages.addMessages( Severity.WARN, KEY_1, MESSAGE_1 );
		assertThat( messages.getSeverity(), is( Severity.WARN ) );
	}

	@Test
	public final void testSeverityErrorAndWarning()
	{
		messages.addMessages( Severity.ERROR, KEY_1, MESSAGE_1 );
		messages.addMessages( Severity.WARN, KEY_2, MESSAGE_2 );
		assertThat( messages.getSeverity(), is( Severity.ERROR ) );
	}

	@Test
	public final void testHasSeverityEmpty()
	{
		assertThat( messages.hasMessages( Severity.ERROR ), is( false ) );
	}

	@Test
	public final void testPopulateSingleMessages()
	{
		messages.addMessages( Severity.ERROR, KEY_1, MESSAGE_1 );
		assertThat( messages.hasMessages( Severity.ERROR ), is( true ) );
		assertThat( messages.hasMessages( Severity.ERROR, KEY_1 ), is( true ) );
		assertThat( messages.getMessages( Severity.ERROR, KEY_1 ), hasSize( 1 ) );
		assertThat( messages.hasMessages( Severity.ERROR, KEY_1 ), is( true ) );
		assertThat( messages.getMessages( Severity.ERROR, KEY_1 ), containsInAnyOrder( MESSAGE_1 ) );
	}

	@Test
	public final void testPopulateMultipleMessages()
	{
		messages.addMessages( Severity.ERROR, KEY_1, MESSAGE_1, MESSAGE_2 );
		assertThat( messages.hasMessages( Severity.ERROR ), is( true ) );
		assertThat( messages.hasMessages( Severity.ERROR, KEY_1 ), is( true ) );
		assertThat( messages.getMessages( Severity.ERROR, KEY_1 ), hasSize( 2 ) );
		assertThat( messages.getMessages( Severity.ERROR, KEY_1 ), containsInAnyOrder( MESSAGE_1, MESSAGE_2 ) );
	}

	@Test
	public final void testPopulateMultipleKeys()
	{
		messages.addMessages( Severity.ERROR, KEY_1, MESSAGE_1 );
		messages.addMessages( Severity.ERROR, KEY_2, MESSAGE_2 );
		assertThat( messages.hasMessages( Severity.ERROR ), is( true ) );
		assertThat( messages.hasMessages( Severity.ERROR, KEY_1 ), is( true ) );
		assertThat( messages.getMessages( Severity.ERROR, KEY_1 ), hasSize( 1 ) );
		assertThat( messages.getMessages( Severity.ERROR, KEY_1 ), containsInAnyOrder( MESSAGE_1 ) );
		assertThat( messages.hasMessages( Severity.ERROR, KEY_2 ), is( true ) );
		assertThat( messages.getMessages( Severity.ERROR, KEY_2 ), hasSize( 1 ) );
		assertThat( messages.getMessages( Severity.ERROR, KEY_2 ), containsInAnyOrder( MESSAGE_2 ) );
	}

	@Test
	public final void testPopulateMultipleSeverity()
	{
		messages.addMessages( Severity.ERROR, KEY_1, MESSAGE_1 );
		messages.addMessages( Severity.WARN, KEY_2, MESSAGE_2 );
		assertThat( messages.hasMessages( Severity.ERROR ), is( true ) );
		assertThat( messages.hasMessages( Severity.ERROR, KEY_1 ), is( true ) );
		assertThat( messages.getMessages( Severity.ERROR, KEY_1 ), hasSize( 1 ) );
		assertThat( messages.getMessages( Severity.ERROR, KEY_1 ), containsInAnyOrder( MESSAGE_1 ) );
		assertThat( messages.hasMessages( Severity.WARN, KEY_2 ), is( true ) );
		assertThat( messages.getMessages( Severity.WARN, KEY_2 ), hasSize( 1 ) );
		assertThat( messages.getMessages( Severity.WARN, KEY_2 ), containsInAnyOrder( MESSAGE_2 ) );
	}

	@Test
	public final void testClearAll()
	{
		messages.addMessages( Severity.ERROR, KEY_1, MESSAGE_1 );
		assertThat( messages.hasMessages( Severity.ERROR, KEY_1 ), is( true ) );
		messages.clear();
		assertThat( messages.hasMessages( Severity.ERROR ), is( false ) );
	}

	@Test
	public final void testClearBySeverity()
	{
		messages.addMessages( Severity.ERROR, KEY_1, MESSAGE_1 );
		assertThat( messages.hasMessages( Severity.ERROR, KEY_1 ), is( true ) );
		messages.clear( Severity.ERROR );
		assertThat( messages.hasMessages( Severity.ERROR ), is( false ) );
	}

	@Test
	public final void testClearBySeverityAndKey()
	{
		messages.addMessages( Severity.ERROR, KEY_1, MESSAGE_1 );
		messages.addMessages( Severity.ERROR, KEY_2, MESSAGE_2 );
		assertThat( messages.hasMessages( Severity.ERROR, KEY_1 ), is( true ) );
		assertThat( messages.hasMessages( Severity.ERROR, KEY_2 ), is( true ) );
		messages.clear( Severity.ERROR, KEY_1 );
		assertThat( messages.hasMessages( Severity.ERROR ), is( true ) );
		assertThat( messages.hasMessages( Severity.ERROR, KEY_1 ), is( false ) );
		assertThat( messages.hasMessages( Severity.ERROR, KEY_2 ), is( true ) );
	}

	@Test
	public final void testToStringEmpty()
	{
		assertThat( messages.toString(), is( "MessagesBase[]" ) );
	}

	@Test
	public final void testToStringOneKey()
	{
		messages.addMessages( Severity.ERROR, KEY_1, MESSAGE_1 );
		assertThat( messages.toString(), is( "MessagesBase[\n\tERROR\tfoo1 : bar1\n]" ) );
		System.out.println( messages );
	}

	@Test
	public final void testToStringOneKeyMultipleText()
	{
		messages.addMessages( Severity.ERROR, KEY_1, MESSAGE_1, MESSAGE_2 );
		assertThat( messages.toString(), is( "MessagesBase[\n\tERROR\tfoo1 : bar1; bar2\n]" ) );
	}

	@Test
	public final void testToStringMultipleKeys()
	{
		messages.addMessages( Severity.ERROR, KEY_1, MESSAGE_1 );
		messages.addMessages( Severity.ERROR, KEY_2, MESSAGE_2 );
		assertThat( messages.toString(), is( "MessagesBase[\n\tERROR\n\t\tfoo1 : bar1\n\t\tfoo2 : bar2\n]" ) );
	}

	@Test
	public final void testToStringMultipleSeverity()
	{
		messages.addMessages( Severity.ERROR, KEY_1, MESSAGE_1 );
		messages.addMessages( Severity.WARN, KEY_2, MESSAGE_2 );
		assertThat( messages.toString(), is( "MessagesBase[\n\tERROR\tfoo1 : bar1\n\tWARN\tfoo2 : bar2\n]" ) );
	}

}
