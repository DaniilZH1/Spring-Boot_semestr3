package MyFirstTestAppSringBoot.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    private List<String> arrayList;
    private Map<Integer, String> hashMap;

    @GetMapping("/hello")
    public String hello(
            @RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/update-array")
    public String updateArrayList(@RequestParam("s") String s) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        arrayList.add(s);
        return "Добавлено в список: " + s;
    }

    @GetMapping("/show-array")
    public List<String> showArrayList() {
        return arrayList == null ? List.of() : arrayList;
    }

    @GetMapping("/update-map")
    public String updateHashMap(@RequestParam("s") String s) {
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        hashMap.put(hashMap.size() + 1, s);
        return "Добавлено в карту: " + s;
    }

    @GetMapping("/show-map")
    public Map<Integer, String> showHashMap() {
        return hashMap == null ? Map.of() : hashMap;
    }

    @GetMapping("/show-all-lenght")
    public String showAllLenght() {
        int arraySize = arrayList == null ? 0 : arrayList.size();
        int mapSize = hashMap == null ? 0 : hashMap.size();

        return "Количество элементов в ArrayList: " + arraySize
                + ", в HashMap: " + mapSize;
    }
}
