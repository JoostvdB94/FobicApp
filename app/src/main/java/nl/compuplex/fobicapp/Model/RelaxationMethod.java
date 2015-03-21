package nl.compuplex.fobicapp.Model;

/**
 * Created by joost on 21-3-15.
 */
public class RelaxationMethod {
    public Integer mNumber;
    public String mName;
    public String mPhobicID;
    public String mID;
    public String mEffectiveness;
    public String mTimesUsed;


    public RelaxationMethod(Integer number, String id, String name,String phobicID,String effectiveness,String timesUsed) {
        mNumber = number;
        mName = name;
        mPhobicID = phobicID;
        mID = id;
        mEffectiveness = effectiveness;
        mTimesUsed = timesUsed;
    }
}
