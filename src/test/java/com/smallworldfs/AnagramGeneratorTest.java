package com.smallworldfs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnagramGeneratorTest {

  private static final String ORIGINAL_STRING = "theorigin";

  private AnagramGenerator anagramaGenerator;

  @BeforeEach
  void setUp() {
    anagramaGenerator = new AnagramGenerator(ORIGINAL_STRING);
  }

  @Test
  @DisplayName("When anagram is generated, then it has the same length as original string")
  void same_length() {
    String anagram = anagramaGenerator.generate();

    assertThat(anagram).hasSameSizeAs(ORIGINAL_STRING);

  }

  @Test
  @DisplayName("When anagram is generated, then it has the same characters as original string")
  void same_chars() {
    String anagram = anagramaGenerator.generate();

    assertThat(anagram.toCharArray()).contains(ORIGINAL_STRING.toCharArray());
  }

  @Test
  @DisplayName("When anagram is generated, then it is different from original string")
  void different_strings() {
    String anagram = anagramaGenerator.generate();

    assertThat(anagram).isNotEqualTo(ORIGINAL_STRING);
  }
}
