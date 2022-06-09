package hello.itemservice.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    //spring 컨테이너 안에서 쓰면 어차피 static이기때문에.. 사용은 없지만...생성 못하게??
    //동시에 여러 스레드에 접근해서 끄면안된다
    private static final Map<Long, Item> store = new HashMap<>();//static

    private static long sequence = 0L;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    //update용 parameter 용 dto 를 따로 만드는게 당연..
    //소규모 프로젝트라서 안만듬...
    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore(){
        store.clear();//hashmap 데이터 다 날리기

    }



}
