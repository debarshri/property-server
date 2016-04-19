package io.github.debarshri.property;

import io.github.debarshri.Resource;
import org.apache.commons.io.FileUtils;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by dbasak on 4/19/16.
 */
public class Search implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        File file = new File(Resource.LOCATION+"/"+request.params("propertyName"));
        if(file.exists())
        {
            List<String> strings = FileUtils.readLines(file);

            Map<String, String> fieldName = ProprUtils.search(strings, request.params("fieldName"));

            return fieldName;
        }

        return "Property not found";
    }
}
