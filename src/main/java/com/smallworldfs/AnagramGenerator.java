package com.smallworldfs;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Slf4j
public class AnagramGenerator {

  private final String originalString;

  public AnagramGenerator(String originalString) {
    this.originalString = originalString;
  }

  public String generate() {
    log.trace("Generating anagram from {}", originalString);

    List<String> originalChars = originalString.chars()
        .mapToObj(c -> String.valueOf((char) c))
        .toList();

    List<Integer> indexes = IntStream.range(0, originalString.length()).boxed().toList();
    List<Integer> shuffledIndexes = new ArrayList<>(indexes);
    Collections.shuffle(shuffledIndexes);

    Map<Integer, Integer> mapping = indexes.stream()
        .collect(Collectors.toMap(Function.identity(), index -> getShuffledIndex(index, shuffledIndexes)));

    String anagram = mapping.values().stream()
        .map(originalChars::get)
        .collect(Collectors.joining());

    log.trace("Generated anagram: {}", anagram);

    return anagram;
  }

  private Integer getShuffledIndex(Integer originalIndex, List<Integer> shuffledIndexes) {
      Integer candidate = shuffledIndexes.get(0);
      if (originalIndex.equals(candidate) && shuffledIndexes.size() > 1) {
        candidate = shuffledIndexes.get(1);
      }

      shuffledIndexes.remove(candidate);

      return candidate;
  }


}
