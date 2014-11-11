package ma.uca.fssm;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
 
public class Saxapi {
 
   public static void main(String argv[]) {
 
    try {
   
    
    
    // Creer l'analyseur du document XML a partir de l'usine des parseurs
	SAXParserFactory factory = SAXParserFactory.newInstance();
	
	//ou false si l'on veut un analyseur non validant
	factory.setValidating(true);
	
	// ou false si l'on ne veut pas prendre en compte les espaces de nom 
	factory.setNamespaceAware(false);
	
	SAXParser saxParser = factory.newSAXParser();
	
	// Construction de notre gestionnaire, classe anonyme
	DefaultHandler handler = new DefaultHandler() {
 
   
	// méthode appelée par l'analyseur SAX sur le début du document
		
	public void startDocument(){
		System.out.println("Debut d'analyse de document XML");
	}
	
	// méthode appelée par l'analyseur SAX sur le début d'un element XML
	public void startElement(String uri, String localName,String qName, 
                Attributes attributes) throws SAXException {
 
		System.out.println("Start Element :" + qName);
 
		for (int i = 0; i < attributes.getLength(); i++) {
			System.out.println("Attribut : "+  attributes.getQName(i)+
			 " / Valeur --> " + attributes.getValue(i));
		}
 
	}
	
	// méthode appelée a la fin de l'analyse d'un element 
	public void endElement(String uri, String localName, String qName) throws SAXException {
 
		System.out.println("End Element :" + qName );
 
	}
	
	// méthode appelée  si l'analyseur trouve du texte au sein de l'element XML
	public void characters(char ch[], int start, int length) throws SAXException {
		
		System.out.println(new String(ch, start, length));
		
	}
	
	public void endDocument(){
		System.out.println("La fin du document XML");
	}
 
	};
	
	
	
	// ouverture d'un fichier XML
	File fichierXML = new File("file.xml");
	saxParser.parse(fichierXML, handler);
 
    } catch (Exception e) {
      
       e.printStackTrace();
    }
 
}
 
}