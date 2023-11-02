package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 전진_정지() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 이름에_대한_예외_처리() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }
    @Test
    void 이름의_길이가_5초과_일때() {
        String input ="Ming,Seo,Park,Bin,Kyeong";
        input.split(",");
        List<Car> participants= new ArrayList<>();
        participants.add(new Car(input,0));

        assertThatThrownBy(() -> RacingController.validateName(participants, input))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void participantsInput_테스트() {
        String input = "Ming,Seo,Park,Bin,Kyeong";
        List<Car> participants = RacingController.participantsInput(input);
//        assertEquals(5,participants.size());
        assertThatThrownBy(() -> RacingController.validateName(participants, input))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
