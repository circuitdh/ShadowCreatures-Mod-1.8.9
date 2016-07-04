package upcraftlp.shadowcreatures.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import upcraftlp.shadowcreatures.Reference;

public class ModUpdate {
	
	private static final String UPDATE_URL = Reference.INTERNAL_UPDATE_URL;
	private static boolean newVersionAvailable = false;
	public static String latestVersion = Reference.VERSION;
	
	public static void checkUpdates()
	{
		new Thread("Update-Checker" + Reference.MOD_ID)
		{
			public void run() {
				try {
					URL url = new URL(UPDATE_URL);
					Scanner scanner = new Scanner(url.openStream());
					latestVersion = scanner.nextLine();
					scanner.close();
					
					if (!Reference.VERSION.equals(latestVersion))
					{
						setNewVersionAvailable();
					}
				} catch (MalformedURLException e)
			{
				System.err.println("URL ERROR: MALFORMED URL");
			} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
	
	
	private static synchronized void setNewVersionAvailable()
	{
		newVersionAvailable = true;
	}
	
	public static synchronized boolean isNewVersionAvailable()
	{
		return newVersionAvailable;
	}
}
