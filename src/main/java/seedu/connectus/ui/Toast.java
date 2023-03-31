package seedu.connectus.ui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * A toast is a pop-up message that appears on the screen for a few seconds.
 */
public final class Toast {
    /**
     * Creates a toast message.
     * @param ownerStage the owner stage of the toast message
     * @param toastMsg the message to be displayed
     * @param toastDelay how long the message will be displayed
     * @param fadeInDelay how long it takes for the message to fade in
     * @param fadeOutDelay how long it takes for the message to fade out
     */
    public static void makeText(Stage ownerStage, String toastMsg, int toastDelay, int fadeInDelay, int fadeOutDelay) {
        Stage toastStage = new Stage();
        // set toaststage to top right corner of the screen
        toastStage.initOwner(ownerStage);
        toastStage.setResizable(false);
        toastStage.initStyle(StageStyle.TRANSPARENT);

        Text text = new Text(toastMsg);
        text.setFont(Font.font("Verdana", 14));
        text.setFill(Color.CRIMSON);

        StackPane root = new StackPane();
        root.setStyle("-fx-background-radius: 12; -fx-background-color: #fde68a;"
                + " -fx-padding: 14px 18px;-fx-width: 80px;");
        root.setOpacity(0);
        root.setMaxWidth(30);
        root.getChildren().add(text);

        root.setTranslateX(ownerStage.getX() + ownerStage.getWidth() - 600);
        root.setTranslateY(ownerStage.getY() + 370);
        root.toFront();

        HBox.setHgrow(text, javafx.scene.layout.Priority.ALWAYS);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        toastStage.setScene(scene);
        toastStage.show();

        Timeline fadeInTimeline = new Timeline();
        KeyFrame fadeInKey1 = new KeyFrame(Duration.millis(fadeInDelay),
                new KeyValue(toastStage.getScene().getRoot().opacityProperty(), 1));
        fadeInTimeline.getKeyFrames().add(fadeInKey1);
        fadeInTimeline.setOnFinished((ae) -> {
            new Thread(() -> {
                try {
                    Thread.sleep(toastDelay);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Timeline fadeOutTimeline = new Timeline();
                KeyFrame fadeOutKey1 = new KeyFrame(Duration.millis(fadeOutDelay),
                        new KeyValue(toastStage.getScene().getRoot().opacityProperty(), 0));
                fadeOutTimeline.getKeyFrames().add(fadeOutKey1);
                fadeOutTimeline.setOnFinished((aeb) -> toastStage.close());
                fadeOutTimeline.play();
            }).start();
        });
        fadeInTimeline.play();
    }
}
