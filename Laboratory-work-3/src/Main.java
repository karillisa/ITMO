import objects.FlowerCity;
import objects.Grabenberg;
import objects.Hotel;
import objects.Restaurant;
import params.Job;
import params.Quality;
import params.Size;
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
        Crabs crabs = new Crabs("Crabs", grabenberg, Job.MANUFACTURE);
        Sproots sproots = new Sproots("Sproots", grabenberg, Job.UNKNOWN);
        Citizens citizens = new Citizens("citizens", grabenberg, Job.UNKNOWN, new Quality[] {Quality.SMARTEST, Quality.KINDEST, Quality.HONEST});

        Hotel hotel = new Hotel("hotel", Size.HUGE, grabenberg);
        Restaurant restaurant = new Restaurant("restaurant", Size.SMALL, grabenberg);


        Work work = new Work("work at office ", Status.START, Job.OFFICE);
        Meeting meeting = new Meeting("meeting", Status.PLAN, crabs, hotel);

        tell(crabs.invite(new Person[]{migi, julio}, hotel));
        tell(migi.agree());
        tell(julio.agree());
        tell(migi.interested("what the famous manufacturer wanted from her"));
        tell(julio.interested("what the famous manufacturer wanted from him"));
        tell(meeting.toString());

        work.finishWork();

        tell("when " + work + "had " + work.getStatus() + "..");
        tell(migi.go(hotel));
        tell(julio.go(hotel));
        tell(crabs.tell("let's have dinner together"));

        System.out.println("\nIn a minute...\n");

        tell(migi.sit(restaurant, " at a table "));
        tell(julio.sit(restaurant, " at a table "));
        tell(crabs.sit(restaurant, " at a table "));

        tell(crabs.startConversation("with distant objects"));
        tell(crabs.inquire(" were in " + grabenberg + " earlier", new Person[]{migi, julio})); //todo: check
        tell(migi.tell("Yes, we were"));
        tell(crabs.praise(grabenberg));
        tell(crabs.praise(citizens));
        tell(crabs.tell(citizens + " are " + citizens.stringQualities()));
        tell(crabs.tell("senior " + sproots + ", who is a native " + grabenberg + "er " + "is also " + citizens.stringQualities() + " and " + Quality.RICHEST));
    }

    public static void tell(String msg) {
        System.out.println(msg.trim().replaceAll(" {2,}", " ") + ".");
    }
}