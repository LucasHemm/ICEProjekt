public interface IDatabase
{
    void saveData(Person person);
    Person login(String username, String password);
}
