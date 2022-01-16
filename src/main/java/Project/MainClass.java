package Project;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static java.util.Map.*;

public class MainClass {
    public static void main(String[] args) {
        try {
        Path path = FileSystems.getDefault().getPath("Poem");
            List<String> poem1 = Files.readAllLines(path, StandardCharsets.UTF_8);
            StringBuilder poem = new StringBuilder();
            for (String poems : poem1) {
                poem.append(poems + "\n");
            }

            String[] wordsArray = poem.toString().split("[\\s,.:!?]+");
            System.out.println("");
            System.out.println(poem.toString());
            System.out.println("-------------------------------");
            System.out.println("");


            poem1.stream().flatMap(line -> Arrays.stream(line.trim().split("\\s+")))
                    .map(word -> word.replaceAll("\\P{L}", "").trim())
                    .map(String::toLowerCase)
                    .filter(word -> word.length() > 0)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
