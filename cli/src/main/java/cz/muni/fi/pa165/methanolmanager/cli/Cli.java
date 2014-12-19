package cz.muni.fi.pa165.methanolmanager.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import org.springframework.web.client.RestTemplate;

public class Cli {

    public static final String STORE = "store";
    public static final String PRODUCER = "producer";

    private final JCommander commander;

    private final MainCommand mainCommand;
    private final StoreCommand storeCommand;
    private final ProducerCommand producerCommand;

    private final String output_format = "| %-5d | %-20s | %-20s |%n";

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

    private void print_store_table(StoreDto[] stores){
        System.out.format("+-------+----------------------+----------------------+%n");
        System.out.printf("| ID    | Store name           | Address              |%n");
        System.out.format("+-------+----------------------+----------------------+%n");

        for (StoreDto store : stores) {
            System.out.format(output_format, store.getId(), store.getName(), store.getAddress());
        }
        System.out.format("+-------+----------------------+----------------------+%n");
    }

    public void doMain(String[] args) {
        try {
            commander.parse(args);

            if (Boolean.TRUE.equals(mainCommand.getHelp()) || commander.getParsedCommand() == null) {
                commander.usage();
            } else if (commander.getParsedCommand().equals(STORE)) {
                if (Boolean.TRUE.equals(storeCommand.getList())) {
                    StoreDto[] stores = restTemplate.getForObject(mainCommand.getUrl() + "/stores", StoreDto[].class);
                    print_store_table(stores);
//                    for (StoreDto store : stores) {
//                        System.out.println(store.getName());
//                        System.out.println(store.getAddress());
//                    }
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
                if (Boolean.TRUE.equals(producerCommand.getList())) {
                    ProducerDto[] producers = restTemplate.getForObject(mainCommand.getUrl() + "/producers", ProducerDto[].class);
                    for (ProducerDto producer : producers) {
                        System.out.println(producer.getName());
                    }
                } else if (Boolean.TRUE.equals(producerCommand.getAdd())) {
                    ProducerDto producer = new ProducerDto();
                    producer.setName(producerCommand.getName());
                    restTemplate.postForObject(mainCommand.getUrl() + "/producers", producer, ProducerDto.class);
                } else if (Boolean.TRUE.equals(producerCommand.getUpdate())) {
                    requireIdParam(producerCommand.getId());

                    ProducerDto producer = new ProducerDto();
                    producer.setId(producerCommand.getId());
                    producer.setName(producerCommand.getName());

                    restTemplate.put(mainCommand.getUrl() + "/producers/" + producerCommand.getId().toString(), producer);
                } else if (Boolean.TRUE.equals(producerCommand.getDelete())) {
                    requireIdParam(producerCommand.getId());

                    restTemplate.delete(mainCommand.getUrl() + "/producers/" + producerCommand.getId());
                }
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
