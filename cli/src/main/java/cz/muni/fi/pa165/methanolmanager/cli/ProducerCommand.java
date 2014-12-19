package cz.muni.fi.pa165.methanolmanager.cli;

import com.beust.jcommander.*;
import lombok.Getter;

@Parameters(commandDescription = "Manipulate the Producer entity")
@Getter
public class ProducerCommand {

    @Parameter(names = {"-l", "--list"}, description = "List all producers")
    private Boolean list;

    @Parameter(names = {"-c", "--create"}, description = "Create new producer")
    private Boolean add;

    @Parameter(names = {"-d", "--delete"}, description = "Delete producer")
    private Boolean delete;

    @Parameter(names = {"-u", "--update"}, description = "Update producer")
    private Boolean update;

    @Parameter(names = {"-i", "--id"}, description = "Producer id (used for update and delete)")
    private Integer id;

    @Parameter(names = {"-n", "--name"}, description = "Producer name (used for update)")
    private String name;
}
