package kr.co.cfg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class PropertyUtil {

	public static Properties getProperties(String path) {
		Properties prop = new Properties();

		try {
			prop.load(getInputStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public static Document getDocument(String path) {
		return getDocument(getInputStream(path));
	}

	public static Document getDocument(InputStream is) {
		Document doc = null;
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();

			factory.setValidating(true);
			factory.setNamespaceAware(false);

			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(is);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return doc;
	}

	public static InputStream getInputStream(String path) {
		InputStream is = null;

		if (path.startsWith("http")) {
			try {
				is = new URL(path).openStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				is = new FileInputStream(new File(path));
			} catch (FileNotFoundException e) {
				is = PropertyUtil.class.getResourceAsStream("/" + path);
			}
		}

		return is;
	}

	public static File getFile(String path) {
		File file = new File(path);

		if (file.exists()) {
			return file;
		} else {
			return new File(PropertyUtil.class.getResource("/" + path)
					.getFile().replaceAll("%20", " "));
		}
	}

}