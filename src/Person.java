public class Person
{
    //Fields
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private Beer beer = new Beer("default","default",0,"default","default");
    private Wine wine = new Wine("default","default",0,"default","default");
    private Spirit spirit = new Spirit("default","default",0,"default","default");


    //Constructor used for existing users
    public Person(String email, String password, String firstName, String lastName, int age, Beer beer, Wine wine, Spirit spirit)
    {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.beer = beer;
        this.wine = wine;
        this.spirit = spirit;
    }

    //Constructor used when crating a new user

    public Person(String email, String password, String firstName, String lastName, int age)
    {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    //Getter- and setter methods

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public Beer getBeer()
    {
        return beer;
    }

    public void setBeer(Beer beer)
    {
        this.beer = beer;
    }

    public Wine getWine()
    {
        return wine;
    }

    public void setWine(Wine wine)
    {
        this.wine = wine;
    }

    public Spirit getSpirit()
    {
        return spirit;
    }

    public void setSpirit(Spirit spirit)
    {
        this.spirit = spirit;
    }
}
