package FTablo.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class TimerService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    private Timer timer;
    private int seconds;

    public TimerService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void start() {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                increase();
            }
        };
        timer = new Timer("Timer");
        timer.scheduleAtFixedRate(repeatedTask, 1000L, 1000L);
    }

    public void stop() {
        timer.cancel();
    }

    public void increase() {
        seconds++;
        simpMessagingTemplate.convertAndSend("/topic/time", seconds);
    }

    public void decrease() {
        seconds--;
        simpMessagingTemplate.convertAndSend("/topic/time", seconds);
    }
}
