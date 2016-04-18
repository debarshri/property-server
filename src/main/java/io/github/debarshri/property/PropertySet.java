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

        File file = new File(Resource.LOCATION);

        File[] files1 = file.listFiles();

        List<File> collect = Arrays.asList(files1).stream().filter(file2 -> file2.getName().contains(propertySetName)).collect(Collectors.toList());

        String configuration;

        if(collect.size() > 1)
        {
            File file1 = collect.stream().filter(filer -> FilenameUtils.getExtension(filer.getName()).equalsIgnoreCase("Properties")).findFirst().get();
            configuration = FileUtils.readFileToString(file1);
        }
        else
        {
            configuration = FileUtils.readFileToString(collect.get(0));
        }

        if(as != null)
        {

            File[] files = file.listFiles();

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
