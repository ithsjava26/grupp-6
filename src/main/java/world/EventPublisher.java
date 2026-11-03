package world;

import common.GameEventListener;

import java.util.ArrayList;
import java.util.List;

// Observer pattern: sends every game event to all registered listeners
public class EventPublisher implements GameEventListener {
    private final List<GameEventListener> listeners = new ArrayList<>();

    public void addListener(GameEventListener listener) {
        listeners.add(listener);
    }

    @Override
    public void onEvent(String message) {
        for (GameEventListener listener : listeners) {
            listener.onEvent(message);
        }
    }
}