package hello.itemservice.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item itemA = new Item("itemA", 10000, 20);
        //when
        //저장된 값
        Item savedItem = itemRepository.save(itemA);
        //then
        //조회한 값
        Item findId = itemRepository.findById(itemA.getId());

        //저장, 조회한 값이 같은지 확인
        Assertions.assertThat(findId).isEqualTo(savedItem);
    }

@Test
    void findAll(){
        //given
    Item itemA = new Item("itemA", 10000, 20);
    Item itemB = new Item("itemB", 20000, 30);

    itemRepository.save(itemA);
    itemRepository.save(itemB);

        //when
    List<Item> result = itemRepository.findAll();
    //then

    Assertions.assertThat(result.size()).isEqualTo(2);
    Assertions.assertThat(result).contains(itemA, itemB);

    }

    @Test
    void updateItem(){
        //given
        Item itemA = new Item("itemA", 10000, 20);
        Item savedItem = itemRepository.save(itemA);

        Long itemId = savedItem.getId();

        //when
        Item updateParam = new Item("itemB", 20000, 30);
        itemRepository.update(itemId, updateParam);
        //then

        Item findItem = itemRepository.findById(itemId);

        Assertions.assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
    }



}