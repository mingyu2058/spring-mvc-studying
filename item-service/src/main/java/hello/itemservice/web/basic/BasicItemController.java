package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId,Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }


    // 같은 url인데 http method로 get이냐 post에 따라 add,save 다르게
    // 상품 등록 폼 : GET  ,  상품 등록 처리 : POST
    // 하나의 URL로 깔끔하게 처리
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    //@PostMapping("/add")
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


    //@ModelAttribute는 객체 생성하고 파라미터 값을 입력해준다.
    //그리고 객체를 자동으로 넣어준다.
    //모델에 데이터를 담을 때는 이름이 필요하다
    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item")Item item){

        itemRepository.save(item);

        //파라미터에 Model model 생략
        //model.addAttribute("item",item); // 자동 추가, 생략 가능

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){

        // 이름을 지정 안하면 클래스의 첫글자인 Item->item 소문자로 바꿔줌

        itemRepository.save(item);

        //파라미터에 Model model 생략
        //model.addAttribute("item",item); // 자동 추가, 생략 가능

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV4(Item item){
        // ModelAttribute 생략 가능, 단순 String 그런 거 말고 직정 생성한 클래스
        // 클래스의 첫글자 소문자가 들어감 so html 적을 때 참고

        itemRepository.save(item);
        // redirect를 쓰면 PostMapping이 아니라 다시 GetMapping으로 돌아감
        // 그러면 새로고침할 때 계속되는 저장을 막을 수 있음
        // 이게 PRG  Post/Redirect/Get
        return "basic/item";
    }
    //@PostMapping("/add")
    public String addItemV5(Item item){
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        //itemId는 치환이 되고 남은 속성인 status는 true로 넘어감
        // -> /baisc/items/3?status=true
        return "redirect:/basic/items/{itemId}";

    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId,@ModelAttribute Item item){
        itemRepository.update(itemId,item);
        // redirect해서
        return "redirect:/basic/items/{itemId}";

    }


    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",12000,10));
    }


}
