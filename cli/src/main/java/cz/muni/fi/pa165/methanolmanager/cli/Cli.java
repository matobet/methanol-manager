package cz.muni.fi.pa165.methanolmanager.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import cz.muni.fi.pa165.methanolmanager.service.dto.ProducerDto;
import cz.muni.fi.pa165.methanolmanager.service.dto.StoreDto;
import org.springframework.web.client.ResourceAccessException;
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

    private static String getDashes(int len){
        StringBuilder stringBuilder = new StringBuilder(len);
        for (int i = 0; i < len; i++){
            stringBuilder.append("-");
        }
        return stringBuilder.toString();
    }

    private static int[] getLongestStoreLengths(StoreDto[] stores){
        // minimal length
        int idLen = 5;
        int nameLen = 20;
        int addressLen = 20;

        for (StoreDto store : stores){
            if (String.valueOf(store.getId()).length() > idLen){
                idLen = String.valueOf(store.getId()).length();
            }
            if (store.getName().length() > nameLen){
                nameLen = store.getName().length();
            }
            if (store.getAddress().length() > addressLen){
                addressLen = store.getAddress().length();
            }
        }
        int[] lengths = {idLen, nameLen, addressLen};
        return lengths;
    }

    private static void printStoreTable(StoreDto[] stores){
        int[] lengths = getLongestStoreLengths(stores);

        String storeBodyFormat = "| %-" + lengths[0] +"s | %-" + lengths[1] + "s | %-" + lengths[2] + "s |%n";
        String storeDividerFormat = "+-%-" + lengths[0] + "s-+-%-" + lengths[1] + "s-+-%-" + lengths[2] + "s-+%n";

        System.out.format(storeDividerFormat, getDashes(lengths[0]), getDashes(lengths[1]), getDashes(lengths[2]));
        System.out.format(storeBodyFormat, "ID", "Store name", "Address");
        System.out.format(storeDividerFormat, getDashes(lengths[0]), getDashes(lengths[1]), getDashes(lengths[2]));

        for (StoreDto store : stores) {
            System.out.format(storeBodyFormat, store.getId(), store.getName(), store.getAddress());
        }
        System.out.format(storeDividerFormat, getDashes(lengths[0]), getDashes(lengths[1]), getDashes(lengths[2]));
    }

    private static int[] getLongestProducerLengths(ProducerDto[] producers){
        // minimal length
        int idLen = 5;
        int nameLen = 20;

        for (ProducerDto producer : producers){
            if (String.valueOf(producer.getId()).length() > idLen){
                idLen = String.valueOf(producer.getId()).length();
            }
            if (producer.getName().length() > nameLen){
                nameLen = producer.getName().length();
            }
        }
        int[] lengths = {idLen, nameLen};
        return lengths;
    }

    private static void printProducerTable(ProducerDto[] producers){
        int[] lengths = getLongestProducerLengths(producers);

        String storeBodyFormat = "| %-" + lengths[0] +"s | %-" + lengths[1] + "s |%n";
        String storeDividerFormat = "+-%-" + lengths[0] + "s-+-%-" + lengths[1] + "s-+%n";

        System.out.format(storeDividerFormat, getDashes(lengths[0]), getDashes(lengths[1]));
        System.out.format(storeBodyFormat, "ID", "Producer name");
        System.out.format(storeDividerFormat, getDashes(lengths[0]), getDashes(lengths[1]));

        for (ProducerDto producer : producers) {
            System.out.format(storeBodyFormat, producer.getId(), producer.getName());
        }
        System.out.format(storeDividerFormat, getDashes(lengths[0]), getDashes(lengths[1]));
    }

    public void doMain(String[] args) {
        try {
            commander.parse(args);

            if (Boolean.TRUE.equals(mainCommand.getHelp()) || commander.getParsedCommand() == null) {
                commander.usage();
            } else if (commander.getParsedCommand().equals(STORE)) {
                if (Boolean.TRUE.equals(storeCommand.getList())) {
                    StoreDto[] stores = restTemplate.getForObject(mainCommand.getUrl() + "/stores", StoreDto[].class);
                    printStoreTable(stores);
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
                    printProducerTable(producers);
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
        } catch (ResourceAccessException e) {
            System.err.println(e.getMessage());
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
