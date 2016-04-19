package io.github.debarshri;

import com.lexicalscope.jewel.cli.CliFactory;
import io.github.debarshri.property.*;

import static spark.Spark.*;

public class ServerMain {
    public static void main(String[] args) {

        if(args.length != 0 )
        {
            Cli cli = CliFactory.parseArguments(Cli.class, args);
            port(cli.getPort());
            Resource.LOCATION = cli.getRepo();
        }
        else {
            Resource.LOCATION = "repo";
        }

        get("/status", (req, res) -> "ok");

        get("/", (req, res) -> "<pre>PropServe Doc\n\n" +
                "/get/{property-name}\n" +
                "/get/{property-name}?as={format}\n" +
                "/get/{property-name}/field/{fieldName}</pre>\n");

        get("/list", new ListAllProperties());
        get("/get/:propertySetName", new PropertySet());
        get("/get/:propertySetName/field/:propertyName", new NProperty());
        post("/add/:propertyName", new CreateProperty());
        post("/add/:propertyName/field/:fieldName/value", new AddValue());
        get("/property/:propertyName/search/:fieldName", new Search());

        //todo search
    }
}
