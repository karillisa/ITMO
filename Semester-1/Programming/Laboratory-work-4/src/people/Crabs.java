package people;

import exception.NotEnoughMoneyException;
import objects.Building;
import objects.City;
import objects.Praisable;
import objects.Purchasable;
import params.ConversationStatus;
import params.ConversationStyle;
import params.Job;

import java.util.Arrays;
import java.util.Objects;

public class Crabs extends Person{
    private String name;
    private City from;
    private Job job;
    private Integer money;

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    //Вложенный static класс
    public static class Status {
        public static final String status = "businessman";
        public static final String name = "Mr. ";
    }

    public Crabs(String name, City from, Job job, Integer money) {
        super(name, from, job);
        this.name = name;
        this.from = from;
        this.job = job;
        this.money = money;
    }

    public String startConversation (String topic, ConversationStyle style, ConversationStatus status, String extra) {
        // Локальный класс
        class Conversation {
            private String topic;
            private ConversationStyle style;
            private ConversationStatus status;

            public Conversation(String topic, ConversationStyle style, ConversationStatus status) {
                this.topic = topic;
                this.style = style;
                this.status = status;
            }

            public String getTopic() {
                return topic;
            }

            public void setTopic(String topic) {
                this.topic = topic;
            }

            public ConversationStyle getStyle() {
                return style;
            }

            public void setStyle(ConversationStyle style) {
                this.style = style;
            }

            public ConversationStatus getStatus() {
                return status;
            }

            public void setStatus(ConversationStatus status) {
                this.status = status;
            }

            @Override
            public String toString() {
                String topic = getTopic().isEmpty() ? "about something" : "about " + getTopic();
                topic += " ";
                return style + " conversation " + topic;
            }
        }
        Conversation conversation = new Conversation(topic, style, status);
        return name + " started " + conversation.toString() + " " + extra;
    }

    public String praise (Praisable what) {
        return name + " was praising the " + what;
    }

    public String buy (Purchasable item, Integer count) throws NotEnoughMoneyException {
        String article = count>1 ? "some " : "a ";
        String s = article.equals("a ") ? "" : "s";
        int totalCost = item.getCost() * count;
        if (totalCost <= money) {
            money -= totalCost;
            return name + " bough " + article + item + s;
        } else {
            throw new NotEnoughMoneyException("NotEnoughMoneyException: " + name + " is short " + (totalCost - money) +  " money to buy");
        }
    }

    @Override
    public String tell(String speech) {
        return name + " said: \"" + speech + "\"";
    }

    @Override
    public String invite(Person[] who, Building where) {
        return name + " invited " + Arrays.toString(who).replace("[", "").replace("]", "").replace(",", " and") + " to " + where.toString();
    }

    @Override
    public String agree() {
        return name + " agreed";
    }

    @Override
    public String interested(String thing) {
        return name + " had interested " + thing;
    }

    @Override
    public String inquire(String thing, Person[] who) {
        return name + " had inquired if " + Arrays.toString(who).replace("[", "").replace("]", "").replace(",", " and") + thing;
    }

    public City getFrom() {
        return from;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return name + " from " + from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crabs crabs = (Crabs) o;
        return Objects.equals(name, crabs.name) && Objects.equals(from, crabs.from) && job == crabs.job;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, from, job);
    }
}
