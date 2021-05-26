package efs.task.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pakiet.Pracownik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PracownikTest {

    static Pracownik pracownik = new Pracownik("Tomek");

    Pracownik tomek;

    @BeforeEach
    public void setup() {
        tomek = new Pracownik("tomek");
    }

    @Test
    public void jestBogaczem_wyplataPowyzejProgu_zwracaTrue() {
        // given
        Pracownik ania = new Pracownik("Ania", 5500);

        // when
        boolean actual = ania.jestBogaczem();

        // then
        assertTrue(actual);
    }

    @Test
    public void jestBogaczem_wyplataPonizejProgu_zwracaFalse() {
        // given
        Pracownik michalWSlabymMiesiacu = new Pracownik("Michal", 1000);

        // when
        boolean actual = michalWSlabymMiesiacu.jestBogaczem();

        // then
        assertFalse(actual);
    }

//    1.
    @Test
    public void zlyTest_zaleznoscOdSrodowiska() throws IOException {
        // given
        Path fileName = Path.of("C:/programy/task8_wprowadzenie/src/test/java/efs/task/unittests/test.txt");

        // when
        String text = Files.readString(fileName);

        // then
        assertThat(text).isEqualTo("Plik testowy");
    }

    @Test
    public void dobryTest_niezaleznoscOdSrodowiska() throws IOException {
        // given
        Path fileName = Path.of(System.getProperty("user.dir") + "/src/test/java/efs/task/unittests/test.txt");

        // when
        String text = Files.readString(fileName);

        // then
        assertThat(text).isEqualTo("Plik testowy");
    }

//    2.


    @Test
    public void shouldHaveCorrectDefaultSalary() {
        // given
        Pracownik pracownik = new Pracownik("Tomek");

        // when
        double wyplata = pracownik.getWyplata();

        // then
        assertThat(wyplata).isEqualTo(1000);
    }

    @Test
    public void shouldIncreaseSalary() {
        // given

        // when
        pracownik.zwiekszWyplate(100);

        // then
        assertThat(pracownik.getWyplata()).isEqualTo(1100);
    }



//    3.
    @Test
    public void zlyTest_zaleznoscOdZewnetrznychZasobow() throws IOException {
        // given
        String command = "curl https://www.onet.pl";
        Process process = Runtime.getRuntime().exec(command);
        InputStream inputStream = process.getInputStream();

        // when
        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.joining("\n"));

        // then
        assertThat(result).contains("Nie zapomnij się zapisać na interesujące Cię tematy!");
    }

//    5.
    @Test
    public void zlyTest_nicNieSprawdza() {
        // given
        pracownik = new Pracownik("Grzegorz");

        // when
        pracownik.zwiekszWyplate(100);

        // then
        assertThat(true).isEqualTo(true);
    }

//    6.
    @Test
    public void zlyTest_zaDuzoWTescie() {
        pracownik = new Pracownik("Grzegorz", 1000);

        assertThat(pracownik.jestBogaczem()).isEqualTo(false);

        pracownik.zwiekszWyplate(1000);

        assertThat(pracownik.jestBogaczem()).isEqualTo(true);
    }

    @Test
    public void jestBogaczem_wyplataPonizej1500_false() {
        // given
        pracownik = new Pracownik("Grzegorz", 1000);

        // when
        boolean actual = pracownik.jestBogaczem();

        // then
        assertThat(actual).isEqualTo(false);
    }

    @Test
    public void jestBogaczem_wyplataPowyzej1500_true() {
        // given
        pracownik = new Pracownik("Grzegorz", 2000);

        // when
        boolean actual = pracownik.jestBogaczem();

        // then
        assertThat(actual).isEqualTo(true);
    }

//    11. Testujemy wiele wartości
    @ParameterizedTest
    @ValueSource(doubles = {0.0, 100.0, 500, 1000.1, 1499.9, 1500})
    void jestBogaczem_wyplataPonizejProgu_zwracaFalse(double wyplata) {
        // given
        Pracownik pracownik = new Pracownik("Pracownik", wyplata);

        // when
        boolean jestBogaczem = pracownik.jestBogaczem();

        // then
        assertThat(jestBogaczem).isEqualTo(false);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1500.1, 1600.0, 1650, 2000.1})
    void jestBogaczem_wyplataPowyzejProgu_zwracaTrue(double wyplata) {
        // given
        Pracownik pracownik = new Pracownik("Pracownik", wyplata);

        // when
        boolean jestBogaczem = pracownik.jestBogaczem();

        // then
        assertThat(jestBogaczem).isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({"test,TEST", "tEst,TEST", "Java,JAVA"})
    void duzeImie_zwracaImiePisaneDuzymiLiterami(String actual, String expected) {
        // given
        Pracownik pracownik = new Pracownik(actual);

        // when
        String actualDuzeImie = pracownik.duzeImie();

        // then
        assertThat(actualDuzeImie).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void duzeImie_zwracaImiePisaneDuzymiLiterami_CSV(String actual, String expected) {
        // given
        Pracownik pracownik = new Pracownik(actual);

        // when
        String actualDuzeImie = pracownik.duzeImie();

        // then
        assertThat(actualDuzeImie).isEqualTo(expected);
    }
}
