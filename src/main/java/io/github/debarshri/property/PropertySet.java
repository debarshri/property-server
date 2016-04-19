package io.github.debarshri.property;

import io.github.debarshri.Resource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PropertySet implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String as = request.queryParams("as");
        String propertySetName = request.params("propertySetName");

        File file = new File(Resource.LOCATION+"/"+propertySetName);

        if(file.exists())
        {
            return FileUtils.readFileToString(file);
        }

        return "Property Does not exist";
    }
}
