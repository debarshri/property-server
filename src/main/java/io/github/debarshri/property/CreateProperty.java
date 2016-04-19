package io.github.debarshri.property;

import io.github.debarshri.Resource;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;

/**
 * Created by dbasak on 4/19/16.
 */
public class CreateProperty implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {

        File file = new File(Resource.LOCATION + "/" + request.params("propertyName"));
        if(file.exists())
        {
            response.status(500);
            return "property exists";
        }
        else
        {
            if(file.createNewFile())
            {
                return "ok";
            }

            return "Something went wrong";
        }
    }
}
