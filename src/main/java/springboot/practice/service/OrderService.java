package springboot.practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.practice.domain.Delivery;
import springboot.practice.domain.Member;
import springboot.practice.domain.Order;
import springboot.practice.domain.OrderItem;
import springboot.practice.domain.item.Item;
import springboot.practice.repository.ItemRepository;
import springboot.practice.repository.MemberRepository;
import springboot.practice.repository.OrderRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 注文
     */
    @Transactional(readOnly = false)
    public Long order(Long memberId, Long itemId, int count) {
        //エンティティ照会
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //配送情報生成
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //注文商品生成
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //注文生成
        Order order = Order.createOrder(member, delivery, orderItem);

        //注文格納
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 注文取消
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //注文エンティティ照会
        Order order = orderRepository.findOne(orderId);
        //注文取消
        order.cancel();
    }

    //検索
//    public List<Order> findOrders(OrderSearch orderSearch) {
//        return orderRepository.findAll(orderSearch);
//    }
}
