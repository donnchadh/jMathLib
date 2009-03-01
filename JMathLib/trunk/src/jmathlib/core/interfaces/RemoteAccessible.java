package jmathlib.core.interfaces;

public interface RemoteAccessible
{
	/**Let the actual class call to the close method of the caller class.*/
	void close();

	/**Let the actual class call to the interpretLine method of the caller class.*/
	void interpretLine(String line);


}
