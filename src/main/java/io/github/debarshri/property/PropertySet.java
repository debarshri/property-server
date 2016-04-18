package io.github.debarshri.property;

import io.github.debarshri.Resource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Map;

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
            FilenameFilter filter = new NameFileFilter(propertySetName);
            File[] files = file.listFiles(filter);

            if(files.length == 0)
            {
                response.status(500);
                return "Cannot find property";
            }

            String extension = FilenameUtils.getExtension(files[0].getAbsolutePath());

            if(as.toUpperCase().equals(extension))
            {
                return configuration;
            }
            else if(as.toUpperCase().equals("JSON"))
            {
                Yaml yaml = new Yaml();
                Map<String,Object> map= (Map<String, Object>) yaml.load(configuration);

                JSONObject jsonObject=new JSONObject(map);
                return jsonObject.toString();
            }
        }

        return configuration;
    }
}
