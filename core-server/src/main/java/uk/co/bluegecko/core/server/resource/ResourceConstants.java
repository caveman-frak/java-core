package uk.co.bluegecko.core.server.resource;


public interface ResourceConstants
{

	public static final String ROOT = "/";

	public static interface Health
	{

		public static final String TAG = "health";

		public static final String PATH = "/health";
		public static final String INFO = "/info";
		public static final String GC = "/gc";

	}

	public static interface Location
	{

		public static final String TAG = "i18n";
		public static final String LOCALE = "locale";
		public static final String MESSAGE_KEY = "messageKey";
		public static final String BUNDLE_NAME = "bundleName";
		public static final String PARAMETERS = "params";

		public static final String PATH = "/i18n";
		public static final String BUNDLE = "/{" + BUNDLE_NAME + "}";
		public static final String MESSAGE = BUNDLE + "/{" + MESSAGE_KEY + "}";

	}

}
