package uk.co.bluegecko.core.server.config;


public interface ServerConstants
{

	public static String BASE_PATH = "/api";
	public static int PORT = 8080;

	public static String TEST_USER = "test";
	public static String TEST_PASSWORD = "test123";
	public static String TEST_REALM = "TestRealm";

	public static interface Roles
	{

		public static String USER = "USER";
		public static String ADMIN = "ADMIN";
		public static String SUPERUSER = "SUPERUSER";

	}

}
