package utils;

import java.util.concurrent.Future;

public interface Listener {

    public void onsuccess(Future future);

    public void onException(Exception exception);
}
