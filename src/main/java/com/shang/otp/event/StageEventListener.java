package com.shang.otp.event;

import com.shang.otp.view.MainView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by shangwei2009@hotmail.com on 2023/2/1 14:48
 */
@Component
public class StageEventListener {

    @Resource
    private FxWeaver fxWeaver;

    @EventListener
    public void stageReadyEventListener(StageReadyEvent stageReadyEvent) {
        Stage stage = stageReadyEvent.getStage();
        stage.setTitle("Authenticators(@WeiShang)");
        stage.setResizable(false);
        Scene scene = new Scene(fxWeaver.loadView(MainView.class), 400, 260);
        stage.setMinWidth(400);
        stage.setMinHeight(260);
        stage.setScene(scene);
        stage.show();
    }

}
