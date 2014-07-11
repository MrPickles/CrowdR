import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

//
// Detects faces in an image, draws boxes around them, and writes the results
// to "faceDetection.png".
//
public class Application {

    public int numPeople;
    private Mat unfilledRect;
    public int numMale;
    public int numFemale;
    public String file;

    public void run() {
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	this.numMale = 0;
	this.numFemale = 0;

	String fileName = "rectangle.png";
	CascadeClassifier profileDetector = new CascadeClassifier(
		"C:\\Users\\Andrew\\Eclipse\\workspace\\GuiThing\\src\\haarcascade_profileface.xml");
	CascadeClassifier frontalDetector = new CascadeClassifier(
		"C:\\Users\\Andrew\\Eclipse\\workspace\\GuiThing\\src\\haarcascade_frontalface_alt.xml");
	Mat image = Highgui.imread(file);
	MatOfRect detections1 = new MatOfRect();
	MatOfRect detections2 = new MatOfRect();
	List<Rect> det = new ArrayList<Rect>();

	frontalDetector.detectMultiScale(image, detections1);

	List<Rect> det1 = detections1.toList();
	det.addAll(det1);

	for (Rect rect : det1) {
	    Core.circle(
		    image,
		    new Point(rect.x + (int) ((rect.width) / 2), rect.y
			    + (int) ((rect.height) / 2)),
		    (int) (rect.width - (int) ((rect.width - rect.height) / 2)) / 2,
		    new Scalar(0, 255, 0), Core.FILLED);
	    // Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x
	    // + rect.width, rect.y + rect.height), new Scalar(0, 255, 0),
	    // Core.FILLED);
	}
	Highgui.imwrite(fileName, image);
	image = Highgui.imread("rectangle.png");

	profileDetector.detectMultiScale(image, detections2);

	List<Rect> det2 = detections2.toList();
	det.addAll(det2);

	for (Rect rect : det2) {
	    Core.circle(
		    image,
		    new Point(rect.x + (int) ((rect.width) / 2), rect.y
			    + (int) ((rect.height) / 2)),
		    (int) (rect.width - (int) ((rect.width - rect.height) / 2)) / 2,
		    new Scalar(0, 255, 0), Core.FILLED);
	    // Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x
	    // + rect.width, rect.y + rect.height), new Scalar(0, 255, 215),
	    // Core.FILLED);
	}
	fileName = "finished.png";
	Highgui.imwrite(fileName, image);

	image = Highgui.imread(file);
	for (Rect rect : det) {
	    Core.circle(
		    image,
		    new Point(rect.x + (int) ((rect.width) / 2), rect.y
			    + (int) ((rect.height) / 2)),
		    (int) (rect.width - (int) ((rect.width - rect.height) / 2)) / 2,
		    new Scalar(0, 0, 204), 5);
	    // Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x
	    // + rect.width, rect.y + rect.height), new Scalar(0, 255, 215), 3);
	}

	//fileName = "detected-faces.png";
	fileName = "detected-faces.jpg";
	Highgui.imwrite(fileName, image);
	this.file = fileName;

	numPeople = det.size();
	
	
	this.unfilledRect = null;

	// System.out.println(numFaces);
	// profileDetector.detectMultiScale(image, faceDetections);

	// System.out.println(faceDetections.toArray().getClass());

	// For loop is designed to keep out repeats
    }

    public static void main(String[] args) {
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	new Application().run();
    }

    /**
     * returns an array of data. Index 0 is number of people, index 1 is number
     * of males index 2 is number of females
     * 
     * @return
     */
    public int[] getData() {
	int[] toReturn = new int[3];
	toReturn[0] = this.numPeople;
	toReturn[1] = this.numMale;
	toReturn[2] = this.numFemale;
	return toReturn;
    }

    public Mat getImage() {
	return this.unfilledRect;
    }

    /**
     * This is needed.
     * 
     * @param filePath
     *            is the file stuff passed in by the GUI
     */
    public void inputFileShit(String filePath) {
	this.file = filePath;
    }
}