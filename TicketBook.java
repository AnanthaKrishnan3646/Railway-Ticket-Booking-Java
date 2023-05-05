import java.util.*;
public class TicketBook{
    static int AvailableLowerBerths = 10;
    static int AvailableUpperBerths = 10;
    static int AvailableMiddleBerths = 10;
    static int AvailableRAC = 5;
    static int AvailableWaitingList = 3;
    static Queue<Integer> WaitingList = new LinkedList<>();
    static Queue<Integer> RACList = new LinkedList<>();
    static List<Integer> BookedTicketList = new ArrayList<>();
    static List<Integer> LowerBerthPos = new ArrayList<>(Arrays.asList(1,4,7,10,13,16,19,22,25,28));
    static List<Integer> MiddleBerthPos = new ArrayList<>(Arrays.asList(2,5,8,11,14,17,20,23,26,29));
    static List<Integer> UpperBerthPos = new ArrayList<>(Arrays.asList(3,6,9,12,15,18,21,24,27,30));
    static List<Integer> RACPos = new ArrayList<>(Arrays.asList(31,32,33,34,35));
    static List<Integer> WaitingListPos = new ArrayList<>(Arrays.asList(1,2,3));
    static Map<Integer,Passenger> passenger = new HashMap<>();
    public void BookTicket(Passenger p, int BerthInfo,String AllottedBerth)
    {
        p.seatNumber = BerthInfo;
        p.allotted = AllottedBerth;
        passenger.put(p.passengerId,p);
        BookedTicketList.add(p.passengerId);
        System.out.println(" You have successfully booked the ticket");
    }
    public void addToRac(Passenger p, int racInfo, String allottedRAC)
    {
        p.seatNumber = racInfo;
        p.allotted = allottedRAC;
        passenger.put(p.passengerId,p);
        RACList.add(p.passengerId);
        AvailableRAC--;
        RACPos.remove(0);

        System.out.println(" You have been allotted RAC ticket");
    }
    public void AddToWaitingList(Passenger p, int WaitingListInfo, String AllottedWaitingList)
    {

        List<Passenger> waitingList = new ArrayList<>();
        List<Passenger> racList = new ArrayList<>();



        p.seatNumber = WaitingListInfo;
        p.allotted = AllottedWaitingList;
        passenger.put(p.passengerId,p);
        WaitingList.add(p.passengerId);
        AvailableWaitingList--;
        WaitingListPos.remove(0);
        System.out.println(" You have been added to waiting list successfully");
    }

    public void cancelTicket(int passengerId)
    {

        Passenger p = passenger.get(passengerId);
        passenger.remove(Integer.valueOf(passengerId));
        BookedTicketList.remove(Integer.valueOf(passengerId));
        int positionBooked = p.seatNumber;

        System.out.println("Successfully Cancelled the Ticket");

        if(p.allotted.equals("L"))
        {
            AvailableLowerBerths++;
            LowerBerthPos.add(positionBooked);
        }
        else if(p.allotted.equals("M"))
        {
            AvailableMiddleBerths++;
            MiddleBerthPos.add(positionBooked);
        }
        else if(p.allotted.equals("U"))
        {
            AvailableUpperBerths++;
            UpperBerthPos.add(positionBooked);
        }

        if(RACList.size() > 0)
        {
            Passenger passengerFromRAC = passenger.get(RACList.poll());
            int positionRac = passengerFromRAC.seatNumber;
            RACPos.add(positionRac);
            RACList.remove(Integer.valueOf(passengerFromRAC.passengerId));
            AvailableRAC++;

            if(WaitingList.size()>0)
            {
                Passenger passengerFromWaitingList = passenger.get(WaitingList.poll());
                int positionWL = passengerFromWaitingList.seatNumber;
                WaitingListPos.add(positionWL);
                WaitingList.remove(Integer.valueOf(passengerFromWaitingList.passengerId));

                passengerFromWaitingList.seatNumber = RACPos.get(0);
                passengerFromWaitingList.allotted = "RAC";
                RACPos.remove(0);
                RACList.add(passengerFromWaitingList.passengerId);

                AvailableWaitingList++;
                AvailableRAC--;
            }
            Main.BookTicket(passengerFromRAC);
        }

    }
    public void printAvailable()
    {
        System.out.println("Available Lower Berths "  + AvailableLowerBerths);
        System.out.println("Available Middle Berths "  + AvailableMiddleBerths);
        System.out.println("Available Upper Berths "  + AvailableUpperBerths);
        System.out.println("Available RACs " + AvailableRAC);
        System.out.println("Available Waiting List " + AvailableWaitingList);
    }
    public void printPassengers()
    {
        if(passenger.size() == 0)
        {
            System.out.println("No details of passengers");
            return;
        }
        for(Passenger p : passenger.values())
        {
            System.out.println("PASSENGER ID " + p.passengerId );
            System.out.println(" Name " + p.name );
            System.out.println(" Age " + p.age );
            System.out.println(" Status " + p.seatNumber + p.allotted);
        }
    }
}