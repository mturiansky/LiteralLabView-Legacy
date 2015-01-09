import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class Config {
	public static final String conf_file = "./config.xml";
	public String secret_key;
	public String url;
	public String tempscreen;
	public String tempcamera;

	public Config() {
		try {
			File f = new File(this.conf_file);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document d = db.parse(f);

			d.getDocumentElement().normalize();
			Node temp = d.getDocumentElement();
			Element root = (Element)temp;

			this.secret_key = root.getElementsByTagName("secret_key").item(0).getTextContent();
			System.out.println("[+] Loaded secret_key: " + this.secret_key);
			this.url = root.getElementsByTagName("url").item(0).getTextContent();
			System.out.println("[+] Loaded url: " + this.url);
			this.tempscreen = root.getElementsByTagName("tempscreen").item(0).getTextContent();
			System.out.println("[+] Loaded tempscreen: " + this.tempscreen);
			this.tempcamera = root.getElementsByTagName("tempcamera").item(0).getTextContent();
			System.out.println("[+] Loaded tempcamera: " + this.tempcamera);
		} catch (Exception e) {
			System.out.println("[-] Error parsing config file");
		}
	}
}