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
 */
public abstract class fileTransformator {

    private static File fileToRead;

    private static void loadFile(String fileName) {
        fileToRead = new File(itemValues.getTracksRoute() + fileName);
    }

    /**
     * Internal method meant to create the XML stream reader that will help reading the file
     * @return an XMLStreamReader item
     */
    private static XMLStreamReader createXMLStreamReader() {
        try {
            InputStream is = new FileInputStream(fileToRead);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            return factory.createXMLStreamReader(is);
        } catch (Exception e) {
            myLogger.error(e);
        }
        return null;
    }

    /**
     *
     * @param fileName Name of the file that the user wants to load
     * @return the object routeTrack with all the information of the road the user loaded
     */
    public static routeTrack readXMLFile(String fileName) {
        loadFile(fileName);
        XMLStreamReader sr = createXMLStreamReader();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (sr != null) {
            switch (extension){
                case "gpx":
                    return createRouteTrackFromGPX(sr);

                default:
                    myLogger.error("We can't recognize filetype "+extension);
            }
        } else {
            myLogger.record(myLogger.ERROR, "The XML file was not able to be opened or transformed, we abort the process");
        }
        return null;
    }

    private static routeTrack createRouteTrackFromGPX(XMLStreamReader sr) {
        routeTrack rt = new routeTrack();
        routePoint rp = null;
        rp = new routePoint();
        try {
            // For every item in the file
            while (sr.hasNext()) {
                // If it's the beginning of a tag we read the content
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
                // If it's the end of a trackPoint element
                if (sr.getEventType() == XMLStreamReader.END_ELEMENT && sr.getLocalName().equals("trkpt")) {
                    // We add the complete point to the array of points.
                    if (!rt.addPoint(rp)) {
                        myLogger.error("The point " + rp.toString() + " was not able to be added to the route");
                    } else {
                        myLogger.debug("We have successfully added the item " + rp.toString());
                        myLogger.debug("The size of the array is now " + rt.getTotalPoints());
                    }
                }
                sr.next();
            }

        } catch (Exception e) {
            myLogger.error(e);
        }
        return rt;
    }


}
