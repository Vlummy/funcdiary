package app.server;

import java.io.*;

/**
 * Class SaveLoadObjectsToFile is an abstract class that can be used to write an object to a file (*.ser)
 * Author (Øyvind Johannessen)
 * Version (0.1)
 */
public abstract class SaveLoadObjectsToFile implements Serializable
{
    // Fields for this class
    private static FileOutputStream fos;
    private static ObjectOutputStream oos;
    private static FileInputStream fis;
    private static ObjectInputStream ois;
    // The object to be returned by loadObject() method
    private static Object object;

    /**
     * Method saveObject() - saves an ArrayList object to given path/filename (String filename)
     * If no extension filter is used for example from a filechooser, then a ".ser" extension is required at the end
     * of the filename string (filename.ser)
     * @param object
     * @param filename
     */
    public static void saveObject(Object object, String filename)
    {
        try
        {
            // Sets up the file to be written
            fos = new FileOutputStream(filename);
            // Defines what object to be written
            oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method loadObject() - loads an object file from a specified filename (path).
     * The method returns an object, which means you have to type cast this object to the desired type you are expecting
     * Example: ArrayList<Type> myList = (ArrayList<Type>) SaveLoadObjectsToFile.loadObject(filename);
     * @param filename
     * @return object
     * @throws Exception
     */
    public static Object loadObject(String filename) throws Exception
    {
        try
        {
            // input stream from file CreativeDB.ser
            fis = new FileInputStream(filename);
            // Reads the file and typecasts it to expected type
            ois = new ObjectInputStream(fis);
            object = ois.readObject();
            ois.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return object;
    }
}
