package io.github.debarshri;

import org.apache.commons.io.FilenameUtils;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllProperties implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        File file = new File(Resource.LOCATION);
        List<String> strings = Arrays.asList(file.list());

        return strings.stream()
                .map(string -> "{"+string+","+FilenameUtils.getExtension(string)+"}")
                .collect(Collectors.toList());
    }
}
