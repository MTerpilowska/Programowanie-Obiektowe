package Rozwoj;
import java.util.Random;

public class GeneratorLiczbLosowych {
    public int losowanie(int z) {
        Random r = new Random();
        return r.nextInt(z);
    }
}
