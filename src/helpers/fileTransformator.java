/**
 * 
 */
package helpers;

import com.sun.xml.internal.bind.v2.TODO;
import com.sun.xml.internal.fastinfoset.sax.SystemIdResolver;
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

    public static routeTrack readXMLFile(String fileName){
        loadFile(fileName);
        XMLStreamReader sr = createXMLStreamReader();
        if(sr != null){
            return createRouteTrackFromGPX(sr);
        }else{
            myLogger.record(myLogger.ERROR, "The XML file was not able to be opened or transformed, we abort the process");
        }
        return null;
    }

    private static routeTrack createRouteTrackFromGPX(XMLStreamReader sr) {
        routeTrack rt = new routeTrack();
        routePoint rp = null;
        rp = new routePoint();
        try {
            while (sr.hasNext()) {
                if (sr.getEventType() == XMLStreamReader.START_ELEMENT) {
                    switch (sr.getLocalName()) {
                        case "trkpt":
                            rp = new routePoint();
                            // Get Latitude
                            if (sr.getAttributeValue(null, "lat") != null && !sr.getAttributeValue(null, "lat").equals("")) {
                                rp.setLat(distanceCalculator.stringToDouble(sr.getAttributeValue(null, "lat")));
                            }
                            // Get Longitude
                            if (sr.getAttributeValue(null, "lon") != null && !sr.getAttributeValue(null, "lon").equals("")) {
                                rp.setLon(distanceCalculator.stringToDouble(sr.getAttributeValue(null, "lon")));
                            }
                            myLogger.debug("We have successfully created the item " + rp.toString());
                            break;
                        case "ele":
                                rp.setEle(distanceCalculator.stringToDouble(sr.getElementText()));
                            break;
                    }
                }
                if (sr.getEventType() == XMLStreamReader.END_ELEMENT && sr.getLocalName().equals("trkpt")) {
                    if (!rt.addPoint(rp)) {
                        myLogger.error("The point " + rp.toString() + " was not able to be added to the route");
                    } else {
                        myLogger.debug("We have successfully added the item " + rp.toString());
                        myLogger.debug("The size of the array is now " + rt.getTotalPoints());
                    }
                }
                sr.next();
            }

        }catch (Exception e){
            myLogger.error(e);
        }
        return rt;
    }


}
