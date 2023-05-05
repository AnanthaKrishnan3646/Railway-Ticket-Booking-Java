import java.util.*;
public class Main {
    public static void BookTicket(Passenger P)
    {
        TicketBook booker = new TicketBook();
        if(TicketBook.AvailableWaitingList == 0)
        {
            System.out.println("No Tickets Available");
            return;
        }

        if((P.berthPreference.equals("L") && TicketBook.AvailableLowerBerths > 0 )||
                (P.berthPreference.equals("M") && TicketBook.AvailableMiddleBerths > 0) ||
                (P.berthPreference.equals("U") && TicketBook.AvailableUpperBerths > 0))
        {
            System.out.println("Your Preferred Berth is Available");
            if(P.berthPreference.equals("L"))
            {
                System.out.println("Lower Berth is booked");
                booker.BookTicket(P,(TicketBook.LowerBerthPos.get(0)),"L");
                TicketBook.LowerBerthPos.remove(0);
                TicketBook.AvailableLowerBerths--;



            }
            else if(P.berthPreference.equals("M"))
            {
                System.out.println("Middle Berth is booked");
                booker.BookTicket(P,(TicketBook.MiddleBerthPos.get(0)),"M");
                TicketBook.MiddleBerthPos.remove(0);
                TicketBook.AvailableMiddleBerths--;

            }
            else if(P.berthPreference.equals("U"))
            {
                System.out.println("Upper Berth is booked");
                booker.BookTicket(P,(TicketBook.UpperBerthPos.get(0)),"U");
                TicketBook.UpperBerthPos.remove(0);
                TicketBook.AvailableUpperBerths--;
            }

        }
        else if(TicketBook.AvailableLowerBerths > 0)
        {
            System.out.println("Sorry, your preferred berth is not available. Lower Berth is Given");
            booker.BookTicket(P,(TicketBook.LowerBerthPos.get(0)),"L");
            TicketBook.LowerBerthPos.remove(0);
            TicketBook.AvailableLowerBerths--;


        }
        else if(TicketBook.AvailableMiddleBerths > 0)
        {
            System.out.println("Sorry, your preferred berth is not available. Middle Berth is Given");
            booker.BookTicket(P,(TicketBook.MiddleBerthPos.get(0)),"M");
            TicketBook.MiddleBerthPos.remove(0);
            TicketBook.AvailableMiddleBerths--;

        }
        else if(TicketBook.AvailableUpperBerths > 0)
        {
            System.out.println("Sorry, your preferred berth is not available. Upper Berth is Given");
            booker.BookTicket(P,(TicketBook.UpperBerthPos.get(0)),"U");
            TicketBook.UpperBerthPos.remove(0);
            TicketBook.AvailableUpperBerths--;

        }
        else if(TicketBook.AvailableRAC> 0)
        {
            System.out.println("Sorry,berth is not available right now. RAC is given");
            booker.addToRac(P,(TicketBook.RACPos.get(0)),"RAC" );
        }
        else if(TicketBook.AvailableWaitingList > 0)
        {
            System.out.println("Sorry,berth is not available right now. Added to Waiting List");
            booker.AddToWaitingList(P,(TicketBook.WaitingListPos.get(0)),"WL");

        }

    }
    public static void cancelTicket(int id)
    {
        TicketBook booker = new TicketBook();
        if(!booker.passenger.containsKey(id))
        {
            System.out.println("Passenger details are unknown");
        }
        else
            booker.cancelTicket(id);
    }
    {

    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.println("\nWelcome TO IRCTC \n");
            System.out.println("1. Book tickets \n2. Cancel tickets \n3. Available tickets \n4. Show Booked tickets \n5. Exit \n");
            int choice = s.nextInt();
            switch(choice)
            {
                case 1:
                {
                    System.out.println("Enter Passenger name,age and berth preference (L,M or U)");
                    String name = s.next();
                    int age = s.nextInt();
                    String berthPreference = s.next();
                    Passenger p = new Passenger(name,age,berthPreference);
                    BookTicket(p);
                }
                break;
                case 2:
                {
                    System.out.println("Enter passenger Id to cancel");
                    int id = s.nextInt();
                    cancelTicket(id);
                }
                break;
                case 3:
                {
                    TicketBook booker = new TicketBook();
                    booker.printAvailable();
                }
                break;
                case 4:
                {
                    TicketBook booker = new TicketBook();
                    booker.printPassengers();
                }
                break;
                case 5:
                {
                    loop = false;
                }
                break;
                default:
                    break;
            }
        }
    }
}

