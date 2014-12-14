package cz.muni.fi.pa165.methanolmanager.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import org.springframework.web.client.RestTemplate;

public class Cli {

    public static final String STORE = "store";
    public static final String PRODUCER = "producer";

    private final JCommander commander;

    private final MainCommand mainCommand;
    private final StoreCommand storeCommand;
    private final ProducerCommand producerCommand;

    private final RestTemplate restTemplate = new RestTemplate();

    public Cli() {
        mainCommand = new MainCommand();
        commander = new JCommander(mainCommand);
        commander.setProgramName("Methanol Manager CLI");

        storeCommand = new StoreCommand();
        commander.addCommand(STORE, storeCommand);

        producerCommand = new ProducerCommand();
        commander.addCommand(PRODUCER, producerCommand);
    }

    public void doMain(String[] args) {
        try {
            commander.parse(args);

            if (Boolean.TRUE.equals(mainCommand.getHelp())) {
                commander.usage();
            } else if (commander.getParsedCommand().equals(STORE)) {
                if (Boolean.TRUE.equals(storeCommand.getList())) {
                    StoreDto[] stores = restTemplate.getForObject(mainCommand.getUrl() + "/stores", StoreDto[].class);
                    for (StoreDto store : stores) {
                        System.out.println(store.getName());
                        System.out.println(store.getAddress());
                    }
                } else if (Boolean.TRUE.equals(storeCommand.getAdd())) {
                    StoreDto store = new StoreDto();
                    store.setName(storeCommand.getName());
                    store.setAddress(storeCommand.getAddress());
                    restTemplate.postForObject(mainCommand.getUrl() + "/stores", store, StoreDto.class);
                } else if (Boolean.TRUE.equals(storeCommand.getUpdate())) {
                    requireIdParam(storeCommand.getId());

                    StoreDto store = new StoreDto();
                    store.setId(storeCommand.getId());
                    store.setName(storeCommand.getName());
                    store.setAddress(storeCommand.getAddress());

                    restTemplate.put(mainCommand.getUrl() + "/stores/" + storeCommand.getId().toString(), store);
                } else if (Boolean.TRUE.equals(storeCommand.getDelete())) {
                    requireIdParam(storeCommand.getId());

                    restTemplate.delete(mainCommand.getUrl() + "/stores/" + storeCommand.getId());
                }
            } else if (commander.getParsedCommand().equals(PRODUCER)) {
                // handle producer
            }
        } catch (ParameterException e) {
            System.err.println(e.getMessage());
            commander.usage();
        }

    }

    private void requireIdParam(Integer id) {
        if (id == null) {
            System.err.println("The `id` parameter must be specified for `update` and `delete` operations");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        new Cli().doMain(args);
    }
}
