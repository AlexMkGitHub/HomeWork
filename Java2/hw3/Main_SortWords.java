package hw3;

import java.util.*;

public class Main_SortWords {

    public static void main(String[] args) {

        List<String> list = new ArrayList(Arrays.asList("Есть", "в", "каждой", "нравственной", "системе",
                "идея", "общая", "для", "всех",
                "нельзя", "и", "с", "теми", "быть", "и", "с", "теми",
                "не", "предавая", "тех", "и", "тех"));

        HashSet<String> uniqueValues = new HashSet(list);
        HashMap<String, Integer> map = new HashMap();

        int i = 0;
        for (String value : uniqueValues) i = i + 1;
        System.out.println("Уникальных слов :" + i);
        System.out.println(uniqueValues);
        System.out.println();

        list.forEach(index -> map.put(index, map.getOrDefault(index, 0) + 1));
        System.out.println("Каждое слово встречается: " + map);

    }
}


