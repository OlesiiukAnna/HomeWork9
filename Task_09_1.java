package home.Task_09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
/*
Есть документ со списком URL:
https://drive.google.com/open?id=1wVBKKxpTKvWwuCzqY1cVXCQZYCsdCXTl
Вывести топ 10 доменов которые встречаются чаще всего. В документе могут встречается пустые и недопустимые строки.
 */
public class Task_09_1 {
    private static final int NUMBER_OF_MAX_VALUES = 10;

    public static void main(String[] args) throws IOException {

        File file = new File("src/main/java/home/Task_09/urls.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        List<String> urls = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            String[] url = line.trim().split("([/])");
            urls.add(url[0]);
        }
        cutToURL(urls);

        System.out.println(topTenURLs(urls));

        reader.close();
    }

    public static List<String> topTenURLs(List<String> list) {
        Map<String, Integer> countedURLs = countDuplicates(list);


        List<Integer> topValues = countedURLs.values().stream()
                .sorted(Comparator.reverseOrder())
                .limit(NUMBER_OF_MAX_VALUES)
                .collect(Collectors.toList());

        List<String> result = new ArrayList<>();

        for (HashMap.Entry<String, Integer> pair : countedURLs.entrySet()) {
            if (topValues.contains(pair.getValue())) {
                result.add(pair.getKey());
            }
        }

        return result;
    }

    private static Map<String, Integer> countDuplicates(List<String> list) {
        Map<String, Integer> map = new HashMap<>();

        Integer defaultCounter = 1;
        for (String url : list) {
            if (map.containsKey(url)) {
                Integer mapValue = map.get(url);
                map.put(url, mapValue + 1);
            } else {
                map.put(url, defaultCounter);
            }
        }

        return map;
    }

    public static List<String> cutToURL(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).startsWith("m.")) {
                String temp = list.get(i).substring(2, list.get(i).length());
                list.set(i, temp);
            }
            if (list.get(i).startsWith("www.")) {
                String temp = list.get(i).substring(4, list.get(i).length());
                list.set(i, temp);
            }
            if (list.get(i).startsWith("docs.")) {
                String temp = list.get(i).substring(5, list.get(i).length());
                list.set(i, temp);
            }
            if (list.get(i).startsWith("search.")) {
                String temp = list.get(i).substring(7, list.get(i).length());
                list.set(i, temp);
            }
        }
        return list;
    }
}
