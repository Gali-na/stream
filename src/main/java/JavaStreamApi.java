import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaStreamApi {

    /**
     * Учитывая список целых чисел,
     * вычесть 1 из каждого элемента в нечетной позиции
     * (имеющей нечетный индекс).
     * Затем верните среднее значение всех нечетных чисел
     * или выбросите исключение NoSuchElementException.
     */
    public Integer getOddNumsSum(List<Integer> numbers) {
        Integer sum = numbers.stream()
                .filter(x -> x % 2 != 0)
                .mapToInt(x -> x)
                .sum();
        return sum;
    }

    /**
     * учитывая список строк, * возвращает количество раз,
     * когда строка `element` встречается в списке.
     */
    public Long calculateOccurrences(List<String> elements, String element) {
        return elements.stream()
                .filter(x -> x.equals(element))
                .count();

    }

    /**
     * Учитывая список строк, верните Optional его первого элемента.
     */
    public Optional<String> getFirstElement(List<String> elements) {
        return elements.stream().findFirst();

    }

    /**
     * Учитывая массивы int, вернуть три наименьших числа в виде списка в отсортированном порядке.
     * * Например, для входа {4, 1, 10, 20, 11, 3} выход будет {1, 3, 4};
     */
    public List<Integer> getThreeSmallestNumbers(int[] numbers) {
        return Arrays.stream(numbers)
                .sorted().limit(3)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    /**
     * Учитывая список строк,
     * найти строку, равную переданному элементу, или выбросить исключение NoSuchElementException.
     */
    public String findElement(List<String> elements, String element) {
        Optional<String> optional = elements.stream().filter(x -> x.equals(element)).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        }
        optional.orElseThrow(NoSuchElementException::new);
        return null;
    }

    public Double getOddNumsAverage(List<Integer> numbers) {
        return IntStream.range(0, numbers.size())
                .map(x -> x % 2 == 1 ? numbers.get(x) - 1 : numbers.get(x))
                .filter(x -> x % 2 == 1)
                .average()
                .orElseThrow();
    }

    /**
     * Учитывая список экземпляров «People» (с полями «name», «age» и «sex»),
     * например, `Arrays.asList (новые люди (« Виктор », 16, Sex.MAN),
     * новые люди («Елена», 42, Sex.WOMEN)) `,
     * выбирать из Списка только мужчин, возраст которых от «fromAge» до «toAge» включительно.
     * <p>
     * Пример: выберите мужчин, которых можно призвать в армию (от 18 до 27 лет включительно).
     */
    public List<People> selectMenByAge(List<People> peopleList, int fromAge, int toAge) {
        List<People> rezult = new ArrayList<>();
        peopleList.stream().map(p -> {
            if ((p.getSex() == People.Sex.MAN)
                    && (p.getAge() >= fromAge
                    && p.getAge() <= toAge)) {
                rezult.add(p);
            }
            return rezult;
        }).collect(Collectors.toList());
        return rezult;
    }

    /**
     * Учитывая список экземпляров `People` (с полями` name`, `age` и` sex`),
     * например, `Arrays.asList (новые люди (« Виктор », 16, Sex.MAN),
     * новые люди («Елена», 42, Sex.WOMEN)) `,
     * выбирать из Списка только людей возрастом от «fromAge» до «maleToAge» (для мужчин)
     * или до 'femaleToAge` (для женщин) включительно.
     * <p>
     * Пример: выберите людей трудоспособного возраста
     * (от 18 до 60 лет для мужчин и до 55 лет для женщин включительно).
     */
    public List<People> getWorkablePeople(int fromAge, int femaleToAge,
                                          int maleToAge, List<People> peopleList) {
        List<People> rezult = new ArrayList<>();
        peopleList.stream().map(p -> {
            if ((p.getSex() == People.Sex.WOMEN)
                    && (p.getAge() >= fromAge
                    && p.getAge() <= femaleToAge)) {
                rezult.add(p);
            }
            if ((p.getSex() == People.Sex.MAN)
                    && (p.getAge() >= fromAge
                    && p.getAge() <= maleToAge)) {
                rezult.add(p);
            }
            return rezult;
        }).collect(Collectors.toList());
        return rezult;
    }

    /**
     * Учитывая список экземпляров `People` (с полями` name`, `age`,`
     * sex` и `List Cat cats`,
     * и у каждого `Cat` есть` имя` и `возраст`),
     * вернуть имена всех кошек, владельцы которых женщины от
     * «женского возраста» лет включительно.
     */
    public List<String> getCatsNames(List<People> peopleList, int femaleAge) {
        return peopleList.stream()
                .filter(x -> x.getAge() >= femaleAge && x.getSex()
                .equals(People.Sex.WOMEN))
                .map(female -> female.getCatList())
                .flatMap(cats -> cats.stream())
                .map(Cat::getName)
                .collect(Collectors.toList());
    }
}

