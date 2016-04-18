package io.github.debarshri;

import com.lexicalscope.jewel.cli.CliFactory;
import io.github.debarshri.property.NProperty;
import io.github.debarshri.property.PropertySet;

import static spark.Spark.*;

public class ServerMain {
    public static void main(String[] args) {

        Cli cli = CliFactory.parseArguments(Cli.class, args);

        port(cli.getPort());
        Resource.LOCATION = cli.getRepo();

        get("/status", (req, res) -> "ok");

        get("/", (req, res) -> "<pre>PropServe Doc\n\n" +
                "/get/{property-name}\n" +
                "/get/{property-name}?as={format}\n" +
                "/get/{property-name}/field/{fieldName}</pre>\n");

        get("/list", new ListAllProperties());
        get("/get/:propertySetName", new PropertySet());
        get("/get/:propertySetName/field/:propertyName", new NProperty());

        //todo
    }
}
