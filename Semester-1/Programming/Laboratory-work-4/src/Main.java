import exception.BuildingIsClosedException;
import objects.*;
import params.*;
import people.*;
import process.Meeting;
import process.Status;
import process.Work;

public class Main {
    public static void main(String[] args) {
        Grabenberg grabenberg = new Grabenberg("Grabenberg", 1439F, 7_121_049);
        FlowerCity flowerCity = new FlowerCity("Flower City", 331.2F, 642_152);

        Migi migi = new Migi("Migi", flowerCity, Job.OFFICE);
        Julio julio = new Julio("Julio", flowerCity, Job.OFFICE);
        Crabs crabs = new Crabs("Crabs", grabenberg, Job.MANUFACTURE, 15000);
        Sproots sproots = new Sproots("Sproots", grabenberg, Job.UNKNOWN);
        Citizens citizens = new Citizens("citizens", grabenberg, Job.UNKNOWN, new Quality[] {Quality.SMARTEST, Quality.KINDEST, Quality.HONEST});

        Hotel hotel = new Hotel("hotel", Size.HUGE, grabenberg, false);
        Restaurant restaurant = new Restaurant("restaurant", Size.SMALL, grabenberg, false);
        GiantPlantSociety giantPlantSociety = new GiantPlantSociety("Giant Plant Society", Size.HUGE, grabenberg, false);

        Work work = new Work("work at office ", Status.START, Job.OFFICE);
        Meeting meeting = new Meeting("meeting", Status.PLAN, crabs, hotel);
//        Conversation unknownConversation = new Conversation();
//        Conversation informalConversation = new Conversation();

        //Анонимный класс
        Purchasable shareOfGiantPlantSociety = new Purchasable() {
            Building company = giantPlantSociety;
            Integer cost = 150;
            @Override
            public void setCompany(Building company) {
                this.company = company;
            }

            @Override
            public String getCompany() {
                return this.company.getName();
            }

            @Override
            public void setCost(Integer cost) {
                this.cost = cost;
            }

            @Override
            public Integer getCost() {
                return cost;
            }

            @Override
            public String toString() {
                return "share";
            }
        };

        //4th lab--
        try {
            crabs.startConversation("", ConversationStyle.UNKNOWN, ConversationStatus.FINISH, "");
            tell("The result of this conversation was that the next morning " + Crabs.Status.name + crabs.go(giantPlantSociety) + " office");
        } catch (BuildingIsClosedException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        tell(Crabs.Status.name + crabs.buy(shareOfGiantPlantSociety, 6) + " of " + shareOfGiantPlantSociety.getCompany());
        tell(crabs.tell(""));
        //--4th lab

        System.out.println();

        tell(Crabs.Status.name + crabs.invite(new Person[]{migi, julio}, hotel));
        tell(migi.agree());
        tell(julio.agree());
        tell(migi.interested("what the famous manufacturer wanted from her"));
        tell(julio.interested("what the famous manufacturer wanted from him"));
        tell(meeting.toString());


        work.finishWork();

        tell("when " + work + "had " + work.getStatus() + "..");
        try {
            tell(migi.go(hotel));
            tell(julio.go(hotel));
        } catch (BuildingIsClosedException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        tell(Crabs.Status.name + crabs.tell("let's have dinner together"));

        System.out.println("\nIn a minute...\n");

        tell(migi.sit(restaurant, " at a table "));
        tell(julio.sit(restaurant, " at a table "));
        tell(Crabs.Status.name + crabs.sit(restaurant, " at a table "));

        tell(Crabs.Status.name + crabs.startConversation("Grabenberg", ConversationStyle.INFORMAL, ConversationStatus.START,"with distant objects"));
        tell(Crabs.Status.name + crabs.inquire(" were in " + grabenberg + " earlier", new Person[]{migi, julio}));
        tell(migi.tell("Yes, we were"));
        tell(Crabs.Status.name + crabs.praise(grabenberg));
        tell(Crabs.Status.name + crabs.praise(citizens));
        tell(Crabs.Status.name + crabs.tell(citizens + " are " + citizens.stringQualities()));
        tell(Crabs.Status.name + crabs.tell("senior " + sproots + ", who is a native " + grabenberg + "er " + "is also " + citizens.stringQualities() + " and " + Quality.RICHEST));
    }
//    class Msg {
        public static void tell(String msg) {
            System.out.println(msg.trim().replaceAll(" {2,}", " ") + ".");
        }
//    }
}