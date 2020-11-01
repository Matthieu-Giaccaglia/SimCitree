package sample;

import javafx.scene.layout.GridPane;

public class ScrollableGrid extends GridPane {

    final double SCALE_DELTA = 1.1;

    public ScrollableGrid() {

        setOnScroll(event -> {
            event.consume();

            if (event.getDeltaY() == 0) {
                return;
            }

            double scaleFactor =
                    (event.getDeltaY() > 0)
                            ? SCALE_DELTA
                            : 1/SCALE_DELTA;

            this.setScaleX(this.getScaleX() * scaleFactor);
            this.setScaleY(this.getScaleY() * scaleFactor);
        });
    }
}
