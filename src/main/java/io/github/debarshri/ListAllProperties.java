package io.github.debarshri;

import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ListAllProperties implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        File file = new File(Resource.LOCATION);
        List<String> strings = Arrays.asList(file.list());
        return strings;
    }
}
