package dev.inside_out;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class ProgramControllerTest {
   @Test
    public void testSortByMonth() {
        ProgramController controller = new ProgramController();
        controller.getMoments().add(new Moment("Vacation", LocalDate.of(2023, 1, 15), "Great time!", controller.getEmotions().get(0)));
        controller.getMoments().add(new Moment("Birthday", LocalDate.of(2023, 5, 10), "Celebration", controller.getEmotions().get(1)));
        controller.getMoments().add(new Moment("Concert", LocalDate.of(2023, 5, 22), "Amazing music!", controller.getEmotions().get(2)));
        controller.getMoments().add(new Moment("Workshop", LocalDate.of(2023, 7, 4), "Learned a lot", controller.getEmotions().get(3)));

        String simulatedInput = "5\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        controller.sortByMonth(scanner);

        String output = outputStream.toString();
        
        assertTrue(output.contains("Lista de momentos por mes 5"));
        assertTrue(output.contains("Birthday"));
        assertTrue(output.contains("Concert"));
        assertFalse(output.contains("Vacation"));
        assertFalse(output.contains("Workshop"));

        System.setOut(originalOut);
    }

    @Test
    public void testSortByEmotion(){
        ProgramController controller = new ProgramController();

        controller.getMoments().add(new Moment("Graduation", LocalDate.of(2022, 6, 15), "Unforgettable day", controller.getEmotions().get(0))); // Alegría
        controller.getMoments().add(new Moment("Breakup", LocalDate.of(2021, 3, 8), "Very sad day", controller.getEmotions().get(1))); // Tristeza
        controller.getMoments().add(new Moment("Argument", LocalDate.of(2023, 2, 11), "Got very angry", controller.getEmotions().get(2))); // Ira
        controller.getMoments().add(new Moment("Vacation", LocalDate.of(2023, 7, 19), "Relaxing and joyful", controller.getEmotions().get(0))); // Alegría


        String simulatedInput = "1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        controller.sortByEmotion(scanner);

        String output = outputStream.toString();

        assertTrue(output.contains("Lista de momentos por emoció: Alegría"));
        assertTrue(output.contains("Graduation"));
        assertTrue(output.contains("Vacation"));
        assertFalse(output.contains("Breakup"));
        assertFalse(output.contains("Argument"));

        System.setOut(originalOut);
    }

}