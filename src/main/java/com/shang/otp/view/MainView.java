package com.shang.otp.view;

import com.bastiaanjansen.otp.TOTP;
import com.shang.otp.common.CommonBeanFactory;
import com.shang.otp.config.AuthProperties;
import com.shang.otp.model.AuthToken;
import com.shang.otp.model.Item;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by shangwei2009@hotmail.com on 2023/2/1 14:55
 */
@Component
@FxmlView
@Slf4j
public class MainView {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @FXML
    private Label time;

    @FXML
    private Label timeLeft;

    @FXML
    private ListView<Item> otpList;

    @Resource
    private AuthProperties authProperties;

    @FXML
    public void initialize() throws URISyntaxException {
        final ObservableList<Item> items = FXCollections.observableArrayList();
        for (final AuthToken authToken : authProperties.getAuthTokens()) {
            final Item item = new Item();
            item.setName(authToken.getName());
            item.setTotp(TOTP.fromURI(new URI(authToken.getUri())));
            items.add(item);
        }
        otpList.setItems(items);
        otpList.setCellFactory(listView -> {
            final CustomItem customItem = CommonBeanFactory.getBean(CustomItem.class);
            log.info(String.valueOf(customItem));
            return customItem;
        });
    }

    @Scheduled(cron = "* * * * * ?")
    public void clock() {
        final LocalDateTime now = LocalDateTime.now();
        final int second = now.getSecond();
        Platform.runLater(() -> {
            time.setText(FORMATTER.format(now));
            final int left = (60 - second) % 30;
            timeLeft.setText(String.valueOf(left));
            if (left == 0) {
                otpList.refresh();
            }
        });
    }

}
