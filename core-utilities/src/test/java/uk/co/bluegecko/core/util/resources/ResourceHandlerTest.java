package uk.co.bluegecko.core.util.resources;


import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertThat;

import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import java.util.Locale;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import org.junit.Before;
import org.junit.Test;


public class ResourceHandlerTest
{

	private ResourceHandler handler;

	@Before
	public final void setup()
	{
		final Locale locale = Locale.UK;
		final ResourceHandler parent = new ResourceHandler( null, "TestParent", locale );
		handler = new ResourceHandler( parent, "Test", locale );
	}

	@Test
	public final void testString()
	{
		final String result = handler.getString( "test@text" );
		assertThat( result, is( "UK text" ) );
	}

	@SuppressWarnings( "boxing" )
	@Test
	public final void testStringArray()
	{
		final String[] result = handler.getStringArray( "test@array" );
		assertThat( result.length, is( 3 ) );
		assertThat( result[0], is( "One" ) );
		assertThat( result[1], is( "Two" ) );
		assertThat( result[2], is( "Three" ) );
	}

	@Test
	public final void testChoice()
	{
		assertThat( handler.getChoice( "test@choice", -1 ), is( "invalid" ) );
		assertThat( handler.getChoice( "test@choice", 0 ), is( "zero" ) );
		assertThat( handler.getChoice( "test@choice", 1 ), is( "single" ) );
		assertThat( handler.getChoice( "test@choice", 2.1 ), is( "many" ) );
		assertThat( handler.getChoice( "test@choice", 5 ), is( "lots" ) );
	}

	@SuppressWarnings( "boxing" )
	@Test
	public final void testMessage()
	{
		final String result = handler.getMessage( "test@message", 4, "World" );
		assertThat( result, is( "4 times around the World" ) );
	}

	@Test
	public final void testMissing()
	{
		final String result = handler.getString( "test@missing" );
		assertThat( result, is( nullValue() ) );
	}

	@Test
	public final void testKeystroke()
	{
		final KeyStroke result = handler.getKeyStroke( "test@keystroke" );
		assertThat( result, is( KeyStroke.getKeyStroke( 'T' ) ) );
	}

	@SuppressWarnings( "boxing" )
	@Test
	public final void testInteger()
	{
		final Integer result = handler.getInteger( "test@integer" );
		assertThat( result, is( 101 ) );
	}

	@SuppressWarnings( "boxing" )
	@Test
	public final void testIntegerAsHex()
	{
		assertThat( handler.getString( "test@colour" ), is( "0xff7700" ) );
		final Integer result = handler.getInteger( "test@colour" );
		assertThat( result, is( 16742144 ) );
	}

	@SuppressWarnings( "boxing" )
	@Test
	public final void testIntegerAsCharacter()
	{
		assertThat( handler.getString( "test@character" ), is( "t" ) );
		final Integer result = handler.getInteger( "test@character" );
		assertThat( result, is( ( int ) 't' ) );
	}

	@SuppressWarnings( "boxing" )
	@Test
	public final void testIntegerAsUnicode()
	{
		assertThat( handler.getString( "test@unicode" ), is( "\u0444" ) );
		final Integer result = handler.getInteger( "test@unicode" );
		assertThat( result, is( ( int ) '\u0444' ) );
	}

	@SuppressWarnings( "boxing" )
	@Test
	public final void testDouble()
	{
		final Double result = handler.getDouble( "test@double" );
		assertThat( result, is( 1.01 ) );
	}

	@Test
	public final void testColour()
	{
		final Color result = handler.getColour( "test@colour" );
		assertThat( result, is( new Color( 255, 119, 0 ) ) );
	}

	@Test
	public final void testUrl()
	{
		final URL result = handler.getURL( "test@icon" );
		assertThat( result, is( getClass().getClassLoader()
				.getResource( "test-image.png" ) ) );
	}

	@SuppressWarnings( "boxing" )
	@Test
	public final void testIcon()
	{
		final ImageIcon result = handler.getIcon( "test@icon" );
		final ImageIcon icon = new ImageIcon( getClass().getClassLoader()
				.getResource( "test-image.png" ) );
		assertThat( result.getIconHeight(), is( 16 ) );
		assertThat( result.getIconWidth(), is( 16 ) );
		assertThat( result.getDescription(), endsWith( "/test-image.png" ) );
		assertThat( result.getImage(), is( icon.getImage() ) );
	}

	@Test
	public final void testImage()
	{
		final Image result = handler.getImage( "test@icon" );
		assertThat( result, is( new ImageIcon( getClass().getClassLoader()
				.getResource( "test-image.png" ) ).getImage() ) );
	}

	@Test
	public final void testFailKeystroke()
	{
		final KeyStroke result = handler.getKeyStroke( "test-fail@keystoke" );
		assertThat( result, is( nullValue() ) );
	}

	@Test
	public final void testFailInteger()
	{
		final Integer result = handler.getInteger( "test-fail@integer" );
		assertThat( result, is( nullValue() ) );
	}

	@Test
	public final void testFailDouble()
	{
		final Double result = handler.getDouble( "test-fail@double" );
		assertThat( result, is( nullValue() ) );
	}

	@Test
	public final void testFailColour()
	{
		final Color result = handler.getColour( "test-fail@colour" );
		assertThat( result, is( nullValue() ) );
	}

	@Test
	public final void testFailIcon()
	{
		final Icon result = handler.getIcon( "test-fail@image" );
		assertThat( result, is( nullValue() ) );
	}

	@Test
	public final void testParent()
	{
		final String result = handler.getString( "test-parent@text" );
		assertThat( result, is( "Text from parent" ) );
	}

	@Test
	public final void testLocale()
	{
		final Locale result = handler.getLocale();
		assertThat( result, is( new Locale( "en", "GB" ) ) );
	}

	@Test
	public final void testMappedLocale()
	{
		final Locale result = handler.getMappedLocale();
		assertThat( result, is( new Locale( "en" ) ) );
	}

}
