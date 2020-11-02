package zzuli.zw.weather.service.yiyan;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class DayWordTask extends Service<String> {

    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                DayWordService service = DayWordService.getInstance();
                return service.getWord();
            }
        };
    }
}
