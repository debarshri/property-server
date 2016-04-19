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

        get("/v1/status", (req, res) -> "ok");

        get("/v1", (req, res) -> "<pre>PropServe Doc\n\n" +
                "/get/{property-name}\n" +
                "/get/{property-name}?as={format}\n" +
                "/get/{property-name}/field/{fieldName}</pre>\n");

        get("/v1/list", new ListAllProperties());
        get("/v1/get/:propertySetName", new PropertySet());
        get("/v1//get/:propertySetName/field/:propertyName", new NProperty());
        post("/v1/add/:propertyName", new CreateProperty());
        post("/v1/add/:propertyName/field/:fieldName/value", new AddValue());
        get("/v1/property/:propertyName/search/:fieldName", new Search());

        //todo search
    }
}
