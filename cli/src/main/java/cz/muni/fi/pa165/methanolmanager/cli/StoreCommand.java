package cz.muni.fi.pa165.methanolmanager.cli;

import com.beust.jcommander.*;
import lombok.Getter;

@Parameters(commandDescription = "Manipulate the Store entity")
@Getter
public class StoreCommand {

    @Parameter(names = {"-l", "--list"}, description = "List all stores")
    private Boolean list;

    @Parameter(names = {"-c", "--create"}, description = "Create new store")
    private Boolean add;

    @Parameter(names = {"-d", "--delete"}, description = "Delete store")
    private Boolean delete;

    @Parameter(names = {"-u", "--update"}, description = "Update store")
    private Boolean update;

    @Parameter(names = {"-i", "--id"}, description = "Store id (used for update and delete)")
    private Integer id;

    @Parameter(names = {"-n", "--name"}, description = "Store name (used for update)")
    private String name;

    @Parameter(names = {"-a", "--address"}, description = "Store address (used for update)")
    private String address;
}
