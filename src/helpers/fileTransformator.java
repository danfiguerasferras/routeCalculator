/**
 * 
 */
package helpers;

import elements.routePoint;
import elements.routeTrack;

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

    public static void readXMLFile(String fileName){
        loadFile(fileName);
        XMLStreamReader sr = createXMLStreamReader();
        if(sr != null){
            createRouteTrackFromGPX(sr);
        }else{
            myLogger.record(myLogger.ERROR, "The XML file was not able to be opened or transformed, we abort the process");
        }
    }

    private static routeTrack createRouteTrackFromGPX(XMLStreamReader sr){
        try{
            while(sr.hasNext()) {
                if(sr.getEventType()==XMLStreamReader.START_ELEMENT) {
                    if (sr.getLocalName().equals("trkpt")) {
                        routePoint rp = new routePoint();
                        if (sr.getAttributeValue(null, "lat") != null && !sr.getAttributeValue(null, "lat").equals("")) {
                            rp.setLat(distanceCalculator.stringToDouble(sr.getAttributeValue(null, "lat")));
                            rp.setLat(distanceCalculator.stringToDouble(sr.getAttributeValue(null, "lon")));
                            // TODO save it in the class and store it
                        }
                    }
                }
                sr.next();
            }

        }catch (Exception e){
            myLogger.error(e);
        }
        return new routeTrack();
    }


}
