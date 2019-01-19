package timeline.frontend;

public interface ControllerInterface {
    void next(FrontCardView frontCardView, BackCardView backCardView);

    void previous(FrontCardView frontCardView, BackCardView backCardView);

    void stepIn(FrontCardView frontCardView);

    void stepOut(FrontCardView frontCardView);
}
