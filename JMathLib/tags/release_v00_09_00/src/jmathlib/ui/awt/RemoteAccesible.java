package jmathlib.ui.awt;

public interface RemoteAccesible
{
        /**Let the actual class call to the close method of the caller class.*/
	void close();

	/**Let the actual class call to the interpretLine method of the caller class.*/
	void interpretLine(String line);

//	/**Unused. Let the actual class call to the init method of the caller class.*/
//	public void init();
}
