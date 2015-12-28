package uk.co.bluegecko.core.util.resources;


import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.KeyStroke;


@SuppressWarnings( "javadoc" )
public class LocalisationHelper
{

	private static final String DELIMITER = "@";

	private LocalisationHelper() throws IllegalAccessException
	{
		throw new IllegalAccessException( "static-class" );
	}

	protected static interface Accessor< T >
	{

		public T getLocalValue( String key );

	}

	public static < T > T getValue( final Accessor< T > localiser, final String identifier, final String type )
	{
		return getValue( localiser, createKey( identifier, type ) );
	}

	public static < T > T getValue( final Accessor< T > localiser, final String key )
	{
		T result = localiser.getLocalValue( key );
		if ( result == null )
		{
			final String subKey = getGenericKey( key );
			if ( subKey != null )
			{
				result = getValue( localiser, subKey );
			}
		}
		return result;
	}

	/**
	 * Try and create a more generic key from a specific key. Expected key format is "yyy.xxx.zzz@aaaa" where
	 * "yyy.xxx.zzz" is the key identifier, "xxx.zzz" and "zzz" are increasingly more generic versions of the identifier
	 * and "aaaa" represents a resource type (eg "icon").
	 *
	 * @param key
	 *            - the key.
	 * @return a more generic version of the key, or null if not possible.
	 */
	public static String getGenericKey( final String key )
	{
		final String[] split = key.split( "\\.", 2 );

		if ( split.length > 1 )
		{
			return split[1];
		}
		return null;
	}

	/**
	 * Get the resource type from the key. Expected key format is "yyy.xxx.zzz@aaaa" where "yyy.xxx.zzz" is the key
	 * identifier and "aaaa" represents a resource type (eg "icon").
	 *
	 * @param key
	 *            - the key.
	 * @return the resource type of the key, or null if none is specified.
	 */
	public static String getTypeFromKey( final String key )
	{
		final String[] split = key.split( DELIMITER, 2 );

		if ( split.length > 1 )
		{
			return split[1];
		}
		return null;
	}

	/**
	 * Get the identifier from the key. Expected key format is "yyy.xxx.zzz@aaaa" where "yyy.xxx.zzz" is the key
	 * identifier and "aaaa" represents a resource type (eg "icon").
	 *
	 * @param key
	 *            - the key.
	 * @return the identifier of the key.
	 */
	public static String getIdentifierFromKey( final String key )
	{
		final String[] split = key.split( DELIMITER, 2 );

		return split[0];
	}

	/**
	 * Create a key from the identifier and the type. Key format is "yyy.xxx.zzz@aaaa" where "yyy.xxx.zzz" is the
	 * identifier and "aaaa" is a resource type (eg "icon").
	 *
	 * @param identifier
	 *            - the identifier.
	 * @param type
	 *            - the resource type.
	 * @return the key.
	 */
	public static String createKey( final String identifier, final String type )
	{
		final StringBuilder buffer = new StringBuilder( identifier );
		buffer.append( DELIMITER );
		buffer.append( type );
		return buffer.toString();
	}

	public static Accessor< String > getText( final ResourceHandler handler )
	{
		return key -> handler.getString( key );
	}

	public static Accessor< ImageIcon > getIcon( final ResourceHandler handler )
	{
		return key -> handler.getIcon( key );
	}

	public static Accessor< KeyStroke > getAccelerator( final ResourceHandler handler )
	{
		return key -> handler.getKeyStroke( key );
	}

	public static Accessor< Integer > getMnemonic( final ResourceHandler handler )
	{
		return key -> handler.getInteger( key );
	}

	public static Accessor< Boolean > getSelected( final ResourceHandler handler )
	{
		return key -> handler.getBoolean( key );
	}

	public static void localise( final Action action, final ResourceHandler handler, final String identifier )
	{
		final Accessor< String > textLocaliser = getText( handler );
		final Accessor< ImageIcon > iconLocaliser = getIcon( handler );
		final Accessor< KeyStroke > acceleratorLocaliser = getAccelerator( handler );
		final Accessor< Integer > mnemonicLocaliser = getMnemonic( handler );
		final Accessor< Boolean > selectedLocaliser = getSelected( handler );

		action.putValue( Action.ACTION_COMMAND_KEY, identifier );
		action.putValue( Action.DEFAULT, identifier );
		action.putValue( Action.NAME, getLocalisedName( textLocaliser, identifier ) );
		action.putValue( Action.SHORT_DESCRIPTION, getValue( textLocaliser, identifier, Action.SHORT_DESCRIPTION ) );
		action.putValue( Action.LONG_DESCRIPTION, getValue( textLocaliser, identifier, Action.LONG_DESCRIPTION ) );
		action.putValue( Action.ACCELERATOR_KEY, getValue( acceleratorLocaliser, identifier, Action.ACCELERATOR_KEY ) );
		action.putValue( Action.MNEMONIC_KEY, getValue( mnemonicLocaliser, identifier, Action.MNEMONIC_KEY ) );
		action.putValue( Action.DISPLAYED_MNEMONIC_INDEX_KEY,
				getValue( mnemonicLocaliser, identifier, Action.DISPLAYED_MNEMONIC_INDEX_KEY ) );
		action.putValue( Action.SMALL_ICON, getValue( iconLocaliser, identifier, Action.SMALL_ICON ) );
		action.putValue( Action.LARGE_ICON_KEY, getValue( iconLocaliser, identifier, Action.LARGE_ICON_KEY ) );
		action.putValue( Action.SELECTED_KEY, getValue( selectedLocaliser, identifier, Action.SELECTED_KEY ) );
	}

	@SuppressWarnings( "boxing" )
	public static void localise( final JLabel label, final ResourceHandler handler, final String identifier )
	{
		final Accessor< String > textLocaliser = getText( handler );
		final Accessor< ImageIcon > iconLocaliser = getIcon( handler );
		final Accessor< Integer > mnemonicLocaliser = getMnemonic( handler );

		label.setName( identifier );
		label.setText( getLocalisedName( textLocaliser, identifier ) );
		label.setToolTipText( getValue( textLocaliser, identifier, Action.SHORT_DESCRIPTION ) );
		label.setIcon( getValue( iconLocaliser, identifier, Action.SMALL_ICON ) );
		final Integer mnemonic = getValue( mnemonicLocaliser, identifier, Action.MNEMONIC_KEY );
		if ( mnemonic != null )
		{
			label.setDisplayedMnemonic( mnemonic );
			final Integer index = getValue( mnemonicLocaliser, identifier, Action.DISPLAYED_MNEMONIC_INDEX_KEY );
			if ( index != null )
			{
				// only call this if we have an explicit value (the previous call sets the default up)
				label.setDisplayedMnemonicIndex( index.intValue() );
			}
		}
		else
		{
			label.setDisplayedMnemonic( 0 );
		}
	}

	public static String getLocalisedName( final Accessor< String > textLocaliser, final String identifier )
	{
		String name = getValue( textLocaliser, identifier, Action.NAME );
		if ( name == null )
		{
			name = getValue( textLocaliser, identifier );
		}
		if ( name == null )
		{
			name = "--" + identifier + "--";
		}
		return name;
	}

	public static String getLocalisedName( final ResourceHandler handler, final String identifier )
	{
		return getLocalisedName( getText( handler ), identifier );
	}

}
