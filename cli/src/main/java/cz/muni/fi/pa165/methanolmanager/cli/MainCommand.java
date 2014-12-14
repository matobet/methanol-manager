package cz.muni.fi.pa165.methanolmanager.cli;

import com.beust.jcommander.Parameter;
import lombok.Getter;

@Getter
public class MainCommand {
    @Parameter(names = {"-u", "--url"}, description = "URL pointing to Methanol Manager REST service root.")
    private String url = "http://localhost:8080/api";

    @Parameter(names = {"-h", "--help"}, description = "Display this usage information", help = true)
    private Boolean help;
}
