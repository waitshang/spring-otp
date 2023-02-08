package com.shang.otp.view;

import com.shang.otp.model.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * Created by shangwei2009@hotmail.com on 2023/2/3 13:49
 */
@Component
@Scope(SCOPE_PROTOTYPE)
@FxmlView
@Slf4j
public class CellView {


    @FXML
    private Label name;

    @FXML
    private Label token;

    public void setItem(Item item) {
        name.setText(item.getName());
        token.setText(item.getToken());
    }

}
