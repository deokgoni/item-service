package hello.itemservice.web.basic;

import hello.itemservice.domain.Item;
import hello.itemservice.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

//    @Autowired
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }


    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "/basic/items";
    }

     @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000,10));
        itemRepository.save(new Item("itemB", 20000,20));
     }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item",findItem);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model){

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item",item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model){
//set 자동 진행된다..
//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item",item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, Model model){
        //Item -> item
        //class명이 HelloData면 -> helloData가 modelAttribute에 담기게된다.
        itemRepository.save(item);

        //해당 아래 부분도 다 반영된다..
//        model.addAttribute("item",item);
        return "basic/item";
    }

    @PostMapping("/add")
    public String addItemV4(Item item, Model model){

        itemRepository.save(item);
        return "basic/item";
    }




}
