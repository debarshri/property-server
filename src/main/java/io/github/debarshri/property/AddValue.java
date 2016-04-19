package io.github.debarshri.property;

import io.github.debarshri.Resource;
import org.apache.commons.io.FileUtils;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

public class AddValue implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {

        String propertyName = request.params("propertyName");
        String fieldName = request.params("fieldName");
        String value = request.queryParams("value");

        String format = Resource.LOCATION + "/" + propertyName;
        System.out.println(request.host() + " added for property " + propertyName);

        File file = new File(format);

        if (file.exists()) {

            Map<String, String> parse = ProprUtils.parse(FileUtils.readLines(file));

            System.out.println(parse);
            parse.put(fieldName,value);

            System.out.println(parse);

            Properties properties = ProprUtils.toProperties(parse);

            OutputStream outstream = new FileOutputStream(new File(file.getAbsolutePath()));
            properties.store(outstream,null);

            return "Ok";

        }

        response.status(500);
        return "Something went wrong";
    }
}
