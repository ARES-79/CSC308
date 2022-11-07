package Asgn03;

import java.io.IOException;

public interface MyObserver {
    public abstract void update(MyObservable ob) throws IOException;
}
