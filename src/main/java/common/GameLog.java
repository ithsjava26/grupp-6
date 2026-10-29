package common;

import java.util.ArrayList;
import java.util.List;

public class GameLog implements GameEventListener {
    private final List<String> events = new ArrayList<>();

    @Override
    public void onEvent(String message) {
        events.add(message);
    }

    public List<String> getEvents() {
        return List.copyOf(events);
    }
}
