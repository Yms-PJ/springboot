package springboot.practice.form;

import lombok.Getter;
import lombok.Setter;
import springboot.practice.domain.OrderStatus;

@Getter
@Setter
public class OrderSearch {
    private String memberName; //회원 이름
    private OrderStatus orderStatus;//주문 상태[ORDER, CANCEL]
    //Getter, Setter
}
