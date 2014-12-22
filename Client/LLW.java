public class LLW {
	public static void main(String args[]) {
		System.out.println("[+] Program booted up.");
		PostCreator a = new PostCreator("http://127.0.0.1:5000/upload","/home/mark/Downloads/test.jpg","/home/mark/Downloads/test.jpg");
		a.send();
	}
}
