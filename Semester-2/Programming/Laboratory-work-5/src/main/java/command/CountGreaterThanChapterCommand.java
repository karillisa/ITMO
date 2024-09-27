package command;

import classes.SpaceMarine;

import java.util.TreeSet;

public class CountGreaterThanChapterCommand extends AbsCommand{
    public CountGreaterThanChapterCommand(String name) {
        super(name);
    }

    @Override
    public boolean doo(String args, TreeSet<SpaceMarine> mySet){
        String chapterName = args.split(" ")[1];

        if(mySet.isEmpty()){
            System.out.println("Collection is empty!");
            return false;
        }
        if(chapterName == null || chapterName.isEmpty()){
            System.out.println("Arg error!");
            return false;
        }

        int cnt = 0;
        for(SpaceMarine s: mySet){
            if(s.getChapter().getName().length() > chapterName.length()){
                cnt = cnt + 1;
            }
        }

        System.out.println("Count: " + cnt);
        return true;
    }

    @Override
    public String des(){
        return "count_greater_than_chapter chapter : вывести количество элементов, значение поля chapter которых больше заданного";
    }
}
