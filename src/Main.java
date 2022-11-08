import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jshell.execution.Util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException {
        BufferedWriter valid = new BufferedWriter(new FileWriter("Test"));
        Document document = new Document("test", List.of(
                new Student("Margo1"),
                new Student("Margo2"),
                new Student("Margo3"),
                new Student("Margo4")
        ));

        toXmlFile("doc.xml", document);
        Document document1 = parseToObj("doc.xml");
        System.out.println(document1);
        toJSON(document);
        Document document2 = toJavaObject();
        System.out.println(document2);
        Writer();

        String s = "";
        try {
            readLargeFile();
        } catch (IOException ignored) {
        }
        System.out.println(s);
    }


    public static Document parseToObj(String filePath) throws JAXBException, IOException {

        Document unmarshal = null;

        JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        unmarshal = (Document) jaxbUnmarshaller.unmarshal(new File(filePath));

        return unmarshal;

    }

    public static void toXmlFile(String filePath, Document doc) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(doc, new File(filePath));
    }

    private final static String baseFile = "user.json";

    public static void toJSON(Document docDoc) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(baseFile), docDoc);
        System.out.println();
        System.out.println("JSON created");
    }

    public static Document toJavaObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(baseFile), Document.class);
    }

    public static void readLargeFile() throws IOException {
        Path path = Paths.get("Text");
        BufferedReader reader = Files.newBufferedReader(path);
        StringBuilder resultStringBuilderTXT = new StringBuilder();
        String line;
        while (reader.ready()) {
            line = reader.readLine();
            resultStringBuilderTXT.append(line).append("\n");
        }

    }

    public static void Writer() throws IOException {
        BufferedWriter valid = new BufferedWriter(new FileWriter("Test"));
        valid.append("New Test Text").append("\n");
        valid.close();

    }
}
