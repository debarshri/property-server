package io.github.debarshri;

import com.lexicalscope.jewel.cli.Option;

public interface Cli {

    @Option
    int getPort();

    @Option
    String getResourceLocation();
}
