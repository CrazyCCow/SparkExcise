package config;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import service.ActiveKeyValueStore;

/**
 * Created by daozhang on 2016/11/24.
 */
public class ConfigUpdater {
    public static final String PATH = "/config";
    private ActiveKeyValueStore _store;
    private Random _random = new Random();
    public ConfigUpdater(String hosts) throws IOException, InterruptedException {
        _store = new ActiveKeyValueStore();
        _store.connect(hosts);
    }
    public void run() throws InterruptedException {

        while (true) {
            String value = _random.nextInt(100) + "";
            _store.write(PATH, value);
            System.out.printf("Set %s to %s\n", PATH, value);
            TimeUnit.SECONDS.sleep(_random.nextInt(10));
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        ConfigUpdater updater = new ConfigUpdater("127.0.0.1:2183");
        updater.run();
    }
}
