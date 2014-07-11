import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/*
 import com.jgoodies.forms.layout.FormLayout;
 import com.jgoodies.forms.layout.ColumnSpec;
 import com.jgoodies.forms.layout.RowSpec;*/

public class ApplicationGUI extends JPanel {
    private JMenuBar menuBar;
    private JPanel panel;
    private JPanel panel_1;
    private JPanel panel_2;
    private JLabel lblImageFile;
    private JTextField textField;
    private JButton btnBrowse;
    private JPanel panel_3;
    private JPanel panel_4;
    private JPanel panel_5;
    private JPanel panel_6;
    private JButton btnDetectFaces;
    private JLabel lblFaces;
    private JLabel lblFacesCount;
    private JLabel lblMale;
    private JLabel lblMaleCount;
    private JLabel lblFemale;
    private JLabel lblFemaleCount;
    private JPanel panel_7;
    private Application app = new Application();
    private JLabel lblimage;

    /**
     * Create the panel.
     */
    public ApplicationGUI() {
	super(new BorderLayout(0, 0));

	menuBar = new JMenuBar();
	add(menuBar, BorderLayout.NORTH);

	panel = new JPanel();
	add(panel, BorderLayout.CENTER);
	panel.setLayout(new BorderLayout(0, 0));

	panel_1 = new JPanel();
	panel.add(panel_1, BorderLayout.NORTH);
	panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

	panel_2 = new JPanel();
	panel_1.add(panel_2);

	lblImageFile = new JLabel("Image File:");
	panel_2.add(lblImageFile);

	textField = new JTextField();
	panel_2.add(textField);
	textField.setColumns(50);

	JButton btnBrowse = new JButton("Browse...");
	btnBrowse.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent arg0) {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Compatible Image Files", "bmp", "dib", "jpeg", "jpg",
			"jpe", "jp2", "png", "pbm", "pgm", "ppm", "sr", "ras",
			"tiff", "tif");
		fileChooser.setFileFilter(filter);
		int returnVal = fileChooser.showOpenDialog(getParent());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		    System.out.println("You chose to open this file: "
			    + fileChooser.getSelectedFile().getName());
		}
		textField.setText(fileChooser.getSelectedFile()
			.getAbsolutePath());
		app.inputFileShit(fileChooser.getSelectedFile()
			.getAbsolutePath());
		System.out.println(fileChooser.getSelectedFile()
			.getAbsolutePath());
		// BufferedImage myPicture;
		// try {
		// myPicture = ImageIO.read(new
		// File(fileChooser.getSelectedFile().getAbsolutePath()));
		// JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		// panel_7.add(picLabel);
		//
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		Image image = new ImageIcon(fileChooser.getSelectedFile()
			.getAbsolutePath()).getImage();
		lblimage = new JLabel();

		ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(
			panel_7.getWidth(), panel_7.getHeight(),
			Image.SCALE_SMOOTH));

		lblimage.setIcon(imageIcon);
		//
		panel_7.add(lblimage);

		revalidate();
		repaint();
		System.out.println("repainted");
	    }
	});
	panel_2.add(btnBrowse);

	JPanel panel_3 = new JPanel();
	panel.add(panel_3, BorderLayout.SOUTH);
	panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

	JPanel panel_6 = new JPanel();
	panel_3.add(panel_6);

	JButton btnDetectFaces = new JButton("Detect Faces");
	btnDetectFaces.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent arg0) {

		app.run();
		lblFacesCount.setText(Integer.toString(app.numPeople));
		lblMaleCount.setText(Integer.toString(app.numMale));
		lblFemaleCount.setText(Integer.toString(app.numFemale));
		
		Image image = new ImageIcon(app.file).getImage();
		//lblimage = new JLabel();

		ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(
			panel_7.getWidth(), panel_7.getHeight(),
			Image.SCALE_SMOOTH));

		lblimage.setIcon(imageIcon);
//		panel_7.add(lblimage);
//		add(panel_7);
//		revalidate();
//		lblimage.repaint();

	    }
	});
	panel_6.add(btnDetectFaces);

	panel_4 = new JPanel();
	panel_3.add(panel_4);

	lblFaces = new JLabel("Faces: ");
	panel_4.add(lblFaces);

	lblFacesCount = new JLabel("0");
	panel_4.add(lblFacesCount);

	panel_5 = new JPanel();
	panel_3.add(panel_5);

	lblMale = new JLabel("Male: ");
	panel_5.add(lblMale);

	lblMaleCount = new JLabel("0");
	panel_5.add(lblMaleCount);

	lblFemale = new JLabel("Female: ");
	panel_5.add(lblFemale);

	lblFemaleCount = new JLabel("0");
	panel_5.add(lblFemaleCount);

	panel_7 = new JPanel();
	panel.add(panel_7, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
	JFrame main = new JFrame("Image Face Detector");
	try {
	    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	} catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InstantiationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (UnsupportedLookAndFeelException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	ApplicationGUI gui = new ApplicationGUI();
	main.getContentPane().add(gui);
	main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	main.setMinimumSize(new Dimension(800, 600));
	main.setVisible(true);

    }

}
