class Passenger {
    static int id = 10001;
    String name;
    int age;
    String berthPreference;
    int passengerId;
    String allotted;
    int seatNumber;
    public Passenger(String name, int age, String berthPreference)
    {
        this.name = name;
        this.age = age;
        this.berthPreference = berthPreference;
        this.passengerId = id++;
        allotted = "";
        seatNumber = -1;

    }
}
