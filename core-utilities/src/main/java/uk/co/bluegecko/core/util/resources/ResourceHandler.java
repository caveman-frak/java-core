package uk.co.bluegecko.core.util.resources;


import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URL;
import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;


@SuppressWarnings( "javadoc" )
public class ResourceHandler implements PropertyChangeListener
{

	private static final String LOCALE = "locale";

	private static final Logger LOGGER = Logger.getLogger( "log.resources", "log.resources" );

	private final PropertyChangeSupport support;
	private final ResourceHandler parent;
	private final String bundleName;
	private Locale locale;
	private ResourceBundle bundle;

	public ResourceHandler( final ResourceHandler parent, final String bundleName, final Locale locale )
	{
		super();

		support = new PropertyChangeSupport( this );
		this.parent = parent;
		this.bundleName = bundleName;

		setLocale( locale );

		if ( parent != null )
		{
			parent.addLocaleListener( this );
		}
	}

	public synchronized void setLocale( final Locale locale )
	{
		this.locale = locale;
		final Locale oldValue = bundle != null ? bundle.getLocale() : null;

		bundle = ResourceBundle.getBundle( bundleName, locale );

		support.firePropertyChange( LOCALE, oldValue, locale );
	}

	public Locale getLocale()
	{
		return locale;
	}

	public ResourceBundle getBundle()
	{
		return bundle;
	}

	public Locale getMappedLocale()
	{
		return bundle.getLocale();
	}

	public final String getString( final String key )
	{
		return ( String ) getObject( key );
	}

	public final String[] getStringArray( final String key )
	{

		final Object result = getObject( key );
		if ( result instanceof String[] )
		{
			return ( String[] ) result;
		}
		else if ( result instanceof String )
		{
			return ( ( String ) result ).split( "\\W" );
		}
		return null;
	}

	@SuppressWarnings( "unused" )
	public final Object getObject( final String key )
	{
		try
		{
			synchronized ( this )
			{
				return bundle.getObject( key );
			}
		}
		catch ( final MissingResourceException ex )
		{
			if ( parent != null )
			{
				return parent.getObject( key );
			}
			return null;
		}
	}

	public String getMessage( final String key, final Object... params )
	{
		final String text = getString( key );
		if ( text != null )
		{
			return MessageFormat.format( text, params );
		}
		return null;
	}

	public String getChoice( final String key, final double value )
	{
		final String text = getString( key );
		if ( text != null )
		{
			final ChoiceFormat choice = new ChoiceFormat( text );
			return choice.format( value );
		}
		return null;
	}

	public KeyStroke getKeyStroke( final String key )
	{
		final String text = getString( key );
		if ( text != null )
		{
			final KeyStroke keyStroke = KeyStroke.getKeyStroke( text );
			if ( keyStroke == null && LOGGER.isLoggable( Level.FINE ) )
			{
				LOGGER.log( Level.FINE, "i18n.unable-to-parse-keystroke", new Object[]
					{ key, text } );
			}
			return keyStroke;
		}
		return null;
	}

	public Boolean getBoolean( final String key )
	{
		final String text = getString( key );
		if ( text != null )
		{
			if ( text.equalsIgnoreCase( "true" ) )
			{
				return Boolean.TRUE;
			}
			else if ( text.equalsIgnoreCase( "false" ) )
			{
				return Boolean.FALSE;
			}
			else
			{
				if ( LOGGER.isLoggable( Level.FINE ) )
				{
					LOGGER.log( Level.FINE, "i18n.unable-to-parse-boolean", new Object[]
						{ key, text } );
				}
			}
		}
		return null;
	}

	@SuppressWarnings(
		{ "boxing", "unused" } )
	public Integer getInteger( final String key )
	{
		final String text = getString( key );
		if ( text != null )
		{
			try
			{
				if ( Character.isAlphabetic( text.codePointAt( 0 ) ) )
				{
					return text.codePointAt( 0 );
				}
				else if ( text.startsWith( "0x" ) )
				{
					return Integer.parseInt( text.split( "x", 2 )[1], 16 );
				}
				return Integer.valueOf( text );
			}
			catch ( final NumberFormatException ex )
			{
				if ( LOGGER.isLoggable( Level.FINE ) )
				{
					LOGGER.log( Level.FINE, "i18n.unable-to-parse-integer", new Object[]
						{ key, text } );
				}
			}
		}
		return null;
	}

	@SuppressWarnings( "unused" )
	public Double getDouble( final String key )
	{
		final String text = getString( key );
		if ( text != null )
		{
			try
			{
				return Double.valueOf( text );
			}
			catch ( final NumberFormatException ex )
			{
				if ( LOGGER.isLoggable( Level.FINE ) )
				{
					LOGGER.log( Level.FINE, "i18n.unable-to-parse-double", new Object[]
						{ key, text } );
				}
			}
		}
		return null;
	}

	public URL getURL( final String key )
	{
		final String text = getString( key );
		URL url = null;
		if ( text != null )
		{
			url = getClass().getClassLoader().getResource( text );
			if ( url == null )
			{
				url = ClassLoader.getSystemResource( text );
			}
		}
		return url;
	}

	public ImageIcon getIcon( final String key )
	{
		final String text = getString( key );
		if ( text != null )
		{
			final URL url = getURL( key );
			if ( url != null )
			{
				return new ImageIcon( url );
			}
			if ( LOGGER.isLoggable( Level.FINE ) )
			{
				LOGGER.log( Level.FINE, "i18n.unable-to-access-image", new Object[]
					{ key, text } );
			}
		}
		return null;
	}

	public Image getImage( final String key )
	{
		final ImageIcon icon = getIcon( key );
		return icon != null ? icon.getImage() : null;
	}

	@SuppressWarnings( "unused" )
	public Color getColour( final String key )
	{
		final String text = getString( key );
		if ( text != null )
		{
			try
			{
				if ( text.startsWith( "0x" ) )
				{
					final int rgb = Integer.parseInt( text.split( "x", 2 )[1], 16 );
					return new Color( rgb );
				}
				return Color.decode( text );
			}
			catch ( final NumberFormatException ex )
			{
				if ( LOGGER.isLoggable( Level.FINE ) )
				{
					LOGGER.log( Level.FINE, "i18n.unable-to-parse-colour", new Object[]
						{ key, text } );
				}
			}
		}
		return null;
	}

	public ComponentOrientation getComponentOrientation()
	{
		return ComponentOrientation.getOrientation( locale );
	}

	@Override
	public void propertyChange( final PropertyChangeEvent e )
	{
		if ( LOCALE.equals( e.getPropertyName() ) )
		{
			setLocale( ( Locale ) e.getNewValue() );
		}
	}

	public void addLocaleListener( final PropertyChangeListener listener )
	{
		support.addPropertyChangeListener( LOCALE, listener );
	}

	public void removeListener( final PropertyChangeListener listener )
	{
		support.addPropertyChangeListener( LOCALE, listener );
	}

}
