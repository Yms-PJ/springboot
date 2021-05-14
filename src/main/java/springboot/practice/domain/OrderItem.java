package springboot.practice.domain;

import lombok.Getter;
import lombok.Setter;
import springboot.practice.domain.item.Item;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 注文価格
    private int count; // 注文数量

}