package com.smallworldfs;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Slf4j
public class AnagramGenerator {

  private static final Integer INVALID_INDEX = -1;

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

    Map<Integer, Integer> indexMapping = createIndexMapping(indexes);

    String anagram = indexMapping.values().stream()
        .map(originalChars::get)
        .collect(Collectors.joining());

    log.trace("Generated anagram: {}", anagram);

    return anagram;
  }

  private Map<Integer, Integer> createIndexMapping(List<Integer> originalStringIndexes) {
    List<Integer> shuffledIndexes = new ArrayList<>(originalStringIndexes);
    Collections.shuffle(shuffledIndexes);

    Map<Integer, Integer> mapping = originalStringIndexes.stream()
        .collect(Collectors.toMap(Function.identity(), index -> getShuffledIndex(index, shuffledIndexes)));

    boolean invalidIndexFound = mapping.values().stream()
        .anyMatch(INVALID_INDEX::equals);

    if (invalidIndexFound) {
      return createIndexMapping(originalStringIndexes);
    } else {
      return mapping;
    }
  }

  private Integer getShuffledIndex(Integer originalIndex, List<Integer> shuffledIndexes) {
      Optional<Integer> candidateIndex = shuffledIndexes.stream()
          .filter(index -> !originalIndex.equals(index))
          .findAny();

      if (candidateIndex.isEmpty()) {
        return INVALID_INDEX;
      } else {
        shuffledIndexes.remove(candidateIndex.get());
        return candidateIndex.get();
      }
  }


}
