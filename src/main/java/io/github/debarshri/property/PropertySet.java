package io.github.debarshri.property;

import io.github.debarshri.Resource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.NameFileFilter;

import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.io.FilenameFilter;

public class PropertySet implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String as = request.queryParams("as");
        String propertySetName = request.params("propertySetName");

        File file = new File(Resource.LOCATION);

        String configuration = FileUtils.readFileToString(new File(String.format("%s/%s.yaml",
                Resource.LOCATION, propertySetName)));

        if(as != null)
        {

            File[] files = file.listFiles();

            File prop;

            for(File file1 : files)
            {
                //todo
            }

            if(files.length == 0)
            {
                response.status(500);
                return "Cannot find property";
            }

            String extension = FilenameUtils.getExtension(files[0].getAbsolutePath());

            return ConversionFactory.fromTo(configuration,extension, as).toString();
        }

        return configuration;
    }
}
