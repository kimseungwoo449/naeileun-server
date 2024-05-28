package introduction.model;

public class IntroductionDao {
    private static IntroductionDao instance = new IntroductionDao();

    private IntroductionDao(){
    }

    public static IntroductionDao getInstance() {
        return instance;
    }


}
