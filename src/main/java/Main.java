import java.io.*;
import java.util.Map;
import java.net.URI;

import org.yaml.snakeyaml.Yaml;
import microsoft.exchange.webservices.data.*;

public class Main {
    public static void main(String [] args) throws Exception {

        // The path of your YAML file.
        final String fileName = "config.yaml";

        Yaml yaml = new Yaml();
        Map<String,Object> config;

        try {
            InputStream ios = new FileInputStream(new File(fileName));

            // Parse the YAML file and return the output as a series of Maps and Lists
            config = (Map<String,Object>)yaml.load(ios);

            String server = config.get("server").toString();
            String user   = config.get("user").toString();
            String pass   = config.get("pass").toString();
            String domain = config.get("domain").toString();

            ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
            service.setTraceEnabled(true);
            ExchangeCredentials credentials = new WebCredentials(user, pass, domain);
            service.setCredentials(credentials);
            service.setUrl(new URI(server));

            ItemView view = new ItemView (10);
            FindItemsResults<Item> findResults = service.findItems(WellKnownFolderName.Inbox, view);

            for(Item item : findResults.getItems()){
                item.load(new PropertySet(BasePropertySet.FirstClassProperties, ItemSchema.MimeContent));
                System.out.println("id==========" + item.getId());
                System.out.println("sub==========" + item.getSubject());
                System.out.println("sub==========" + item.getMimeContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
