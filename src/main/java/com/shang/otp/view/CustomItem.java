package com.shang.otp.view;

import com.shang.otp.model.Item;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxControllerAndView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * Created by shangwei2009@hotmail.com on 2023/2/2 19:02
 */
@Component
@Scope(SCOPE_PROTOTYPE) // 必须是prototype
@Slf4j
public class CustomItem extends ListCell<Item> {

    @Resource
    private FxControllerAndView<CellView, HBox> fxControllerAndView;

    @Override
    protected void updateItem(Item item, boolean empty) {
        super.updateItem(item, empty);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else if (item == null) {
            setText("null");
            setGraphic(null);
        } else {
            setText(null);
            final CellView cellView = fxControllerAndView.getController();
            cellView.setItem(item);
            setGraphic(fxControllerAndView.getView().orElse(null));
        }
    }

}
