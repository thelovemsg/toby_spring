package tobyspring.hello_spring.api;

import java.io.IOException;
import java.net.URI;

public interface ApiExecutor {
    String execute(URI uri) throws IOException;
}
