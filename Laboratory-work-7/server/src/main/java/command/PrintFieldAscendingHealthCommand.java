package command;

import classes.SpaceMarine;
import objectResAns.ObjectResAns;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

public class PrintFieldAscendingHealthCommand extends AbsCommand{
    public PrintFieldAscendingHealthCommand(String name) {
        super(name);
    }

    @Override
    public ObjectResAns doo(String args, TreeSet<SpaceMarine> mySet, String user){
        Map<Long, Integer> sortWithHealth = new LinkedHashMap<>();

        if(mySet.isEmpty()) {
            return new ObjectResAns("Collection is empty!", true, user);
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

        String allRes = "Sorted Objects by Health:";
        for (Long ind : sortedMap.keySet()) {
            allRes = allRes + "\n" + "ID: " + ind + "; Health: " + sortedMap.get(ind);
        }

        return new ObjectResAns(allRes, true, user);
    }

    @Override
    public String des(){
        return "print_field_ascending_health : вывести значения поля health всех элементов в порядке возрастания";
    }
}
