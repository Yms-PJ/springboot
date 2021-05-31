package springboot.practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.practice.domain.item.Item;
import springboot.practice.repository.ItemRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final EntityManager em;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    @Transactional
    void update(Item itemParam) {
        Item mergeItem = em.merge(itemParam);
    }

    @Transactional
    public void updateItem(Long id, String name, int price) {
        Item item = itemRepository.findOne(id);
        item.setName(name);
        item.setPrice(price);
    }
}
