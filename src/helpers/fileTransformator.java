/**
 * 
 */
package helpers;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Blad3r
 *
 */
public abstract class fileTransformator {

    private static String trackRoute;
    private static File fileToRead;

    private static void loadFile(String fileName){
        fileToRead = new File(itemValues.getTracksRoute()+fileName);
    }

    private static XMLStreamReader createXMLStreamReader(){
        try {
            InputStream is = new FileInputStream(fileToRead);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            return factory.createXMLStreamReader(is);
        } catch (Exception e){
            myLogger.error(e);
        }
        return null;
    }

    public static void readGPXFile(String fileName){
        loadFile(fileName);

    }


}
