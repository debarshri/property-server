package io.github.debarshri;

import com.lexicalscope.jewel.cli.CliFactory;
import io.github.debarshri.property.NProperty;
import io.github.debarshri.property.PropertySet;
import spark.Spark;

public class ServerMain {
    public static void main(String[] args) {

        Cli cli = CliFactory.parseArguments(Cli.class, args);

        Spark.port(cli.getPort());
        Resource.LOCATION = cli.getResourceLocation();

        Spark.get("/status", (req, res) -> "ok");
        Spark.get("/", (req, res) -> "<pre>PropServe Doc\n\n" +
                "/get/{property-name}\n" +
                "/get/{property-name}?as={format}\n" +
                "/get/{property-name}/field/{fieldName}</pre>\n");

        Spark.get("/get/:propertySetName", new PropertySet());
        Spark.get("/get/:propertySetName/field/:propertyName", new NProperty());
    }
}
