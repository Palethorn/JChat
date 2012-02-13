public class Singleton
{
    static Singleton instance;
    public Singleton Instance()
    {
        if(instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }
}
