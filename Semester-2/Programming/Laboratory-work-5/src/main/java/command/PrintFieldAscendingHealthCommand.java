package command;

import classes.SpaceMarine;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

public class PrintFieldAscendingHealthCommand extends AbsCommand{
    public PrintFieldAscendingHealthCommand(String name) {
        super(name);
    }

    @Override
    public boolean doo(String args, TreeSet<SpaceMarine> mySet){
        Map<Long, Integer> sortWithHealth = new LinkedHashMap<>();

        if(mySet.isEmpty()) {
            System.out.println("Collection is empty!");
            return true;
        }

        for (SpaceMarine s : mySet) {
            sortWithHealth.put(s.getId(), s.getHealth());
        }

        Map<Long, Integer> sortedMap = sortWithHealth.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(
                        LinkedHashMap::new,
                        (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll
                );

        System.out.println("Sorted Objects by Health:");
        for (Long ind : sortedMap.keySet()) {
            System.out.println("ID: " + ind + "; Health: " + sortedMap.get(ind));
        }

        return true;
    }

    @Override
    public String des(){
        return "print_field_ascending_health : вывести значения поля health всех элементов в порядке возрастания";
    }
}
