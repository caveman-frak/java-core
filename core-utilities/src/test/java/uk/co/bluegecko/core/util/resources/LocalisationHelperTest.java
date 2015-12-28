package uk.co.bluegecko.core.util.resources;


import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

import org.junit.Before;
import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class LocalisationHelperTest
{

	private final String key = "ship.asset.save@text";
	private ResourceHandler handler;

	@Before
	public final void setup()
	{
		handler = new ResourceHandler( null, "Test", Locale.UK );
	}

	@Test
	public final void testGetGenericKey()
	{
		String result = LocalisationHelper.getGenericKey( key );
		assertThat( "1st", result, is( "asset.save@text" ) );
		result = LocalisationHelper.getGenericKey( result );
		assertThat( "2nd", result, is( "save@text" ) );
		result = LocalisationHelper.getGenericKey( result );
		assertThat( "3rd", result, is( nullValue() ) );
	}

	@Test
	public final void testGetTypeFromKey()
	{
		final String result = LocalisationHelper.getTypeFromKey( key );
		assertThat( result, is( "text" ) );
	}

	@Test
	public final void testGetIdentifierFromKey()
	{
		final String result = LocalisationHelper.getIdentifierFromKey( key );
		assertThat( result, is( "ship.asset.save" ) );
	}

	@Test
	public final void testCreateKey()
	{
		final String result = LocalisationHelper.createKey( "ship.asset.save", "text" );
		assertThat( result, is( "ship.asset.save@text" ) );
	}

	@Test
	public final void testAccessorText()
	{
		final LocalisationHelper.Accessor< String > result = LocalisationHelper.getText( handler );
		assertThat( result.getLocalValue( "test@text" ), is( "UK text" ) );
	}

	@Test
	public final void testAccessorAccelerator()
	{
		final LocalisationHelper.Accessor< KeyStroke > result = LocalisationHelper.getAccelerator( handler );
		assertThat( result.getLocalValue( "test@keystroke" ), is( KeyStroke.getKeyStroke( 'T' ) ) );
	}

	@SuppressWarnings( "boxing" )
	@Test
	public final void testAccessorMnemonic()
	{
		final LocalisationHelper.Accessor< Integer > result = LocalisationHelper.getMnemonic( handler );
		assertThat( result.getLocalValue( "test@integer" ), is( 101 ) );
	}

	@Test
	public final void testAccessorIcon()
	{
		final LocalisationHelper.Accessor< ImageIcon > result = LocalisationHelper.getIcon( handler );
		final ImageIcon icon = new ImageIcon( getClass().getClassLoader().getResource( "test-image.png" ) );
		assertThat( result.getLocalValue( "test@icon" ).getImage(), is( icon.getImage() ) );
	}

	@Test
	public final void testGetValue()
	{
		final LocalisationHelper.Accessor< String > accessor = LocalisationHelper.getText( handler );
		final String result = LocalisationHelper.getValue( accessor, "test", "text" );
		assertThat( result, is( "UK text" ) );
	}

	@Test
	public final void testGetValueWithKey()
	{
		final LocalisationHelper.Accessor< String > accessor = LocalisationHelper.getText( handler );
		final String result = LocalisationHelper.getValue( accessor, "test@text" );
		assertThat( result, is( "UK text" ) );
	}

	@Test
	public final void testGetValueRequiringGenerification()
	{
		final LocalisationHelper.Accessor< String > accessor = LocalisationHelper.getText( handler );
		assertThat( accessor.getLocalValue( "specific.test@text" ), is( nullValue() ) );
		final String result = LocalisationHelper.getValue( accessor, "very.specific.test", "text" );
		assertThat( result, is( "UK text" ) );
	}

	@Test
	public final void testGetLocalisedName()
	{
		final LocalisationHelper.Accessor< String > accessor = LocalisationHelper.getText( handler );

		String result = LocalisationHelper.getLocalisedName( accessor, "test" );
		assertThat( "with Name", result, is( "Component Name" ) );
		result = LocalisationHelper.getLocalisedName( accessor, "testName" );
		assertThat( "with Default", result, is( "Fallback Name" ) );
		result = LocalisationHelper.getLocalisedName( accessor, "testname" );
		assertThat( "with None", result, is( "--testname--" ) );
	}

	@SuppressWarnings( "boxing" )
	@Test
	public final void testLocaliseLabel()
	{
		final JLabel label = new JLabel();
		LocalisationHelper.localise( label, handler, "label" );
		assertThat( label.getName(), is( "label" ) );
		assertThat( label.getText(), is( "Text for label" ) );
		assertThat( label.getToolTipText(), is( "Tooltip for label" ) );
		final ImageIcon icon = new ImageIcon( getClass().getClassLoader().getResource( "test-image.png" ) );
		assertThat( ( ( ImageIcon ) label.getIcon() ).getImage(), is( icon.getImage() ) );
		assertThat( label.getDisplayedMnemonic(), is( ( int ) 'a' ) );
		assertThat( label.getDisplayedMnemonicIndex(), is( 1 ) );
	}

	@SuppressWarnings( "boxing" )
	@Test
	public final void testLocaliseAction()
	{
		@SuppressWarnings( "serial" )
		final Action action = new AbstractAction()
		{

			@Override
			public void actionPerformed( final ActionEvent e )
			{ /* do nothing */}
		};
		LocalisationHelper.localise( action, handler, "action" );
		assertThat( ( String ) action.getValue( Action.ACTION_COMMAND_KEY ), is( "action" ) );
		assertThat( ( String ) action.getValue( Action.DEFAULT ), is( "action" ) );
		assertThat( ( String ) action.getValue( Action.NAME ), is( "Name for action" ) );
		assertThat( ( String ) action.getValue( Action.SHORT_DESCRIPTION ), is( "ShortDescription for action" ) );
		assertThat( ( String ) action.getValue( Action.LONG_DESCRIPTION ), is( "LongDescription for action" ) );
		final ImageIcon icon = new ImageIcon( getClass().getClassLoader().getResource( "test-image.png" ) );
		assertThat( ( ( ImageIcon ) action.getValue( Action.SMALL_ICON ) ).getImage(), is( icon.getImage() ) );
		assertThat( ( ( ImageIcon ) action.getValue( Action.LARGE_ICON_KEY ) ).getImage(), is( icon.getImage() ) );
		assertThat( ( Integer ) action.getValue( Action.MNEMONIC_KEY ), is( ( int ) 't' ) );
		assertThat( ( Integer ) action.getValue( Action.DISPLAYED_MNEMONIC_INDEX_KEY ), is( 0 ) );
		assertThat( ( KeyStroke ) action.getValue( Action.ACCELERATOR_KEY ),
				is( KeyStroke.getKeyStroke( KeyEvent.VK_T, InputEvent.CTRL_MASK ) ) );
	}

}
