package uk.co.bluegecko.core.model.base;


import java.util.Locale;


/**
 * Message backed by a resource bundle.
 */
public class BundleMessage extends LocalisedMessage< String >
{

	private final String bundleName;

	/**
	 * Construct a new message using a resource bundle, key and set of arguments.
	 *
	 * @param bundleName
	 *            name of the resource bundle
	 * @param key
	 *            enum key for message
	 * @param args
	 *            message arguments
	 */
	public BundleMessage( final String bundleName, final String key, final Object... args )
	{
		super( key, args );

		this.bundleName = bundleName;
	}

	protected String getBundleName()
	{
		return bundleName;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Message#getText(java.util.Locale)
	 */
	@Override
	public String getText( final Locale locale )
	{
		return toString();
	}

}
