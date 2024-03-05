package co.edu.uptc.model;
import java.util.ArrayList;
import java.util.List;

public class CombineNames {
    private List<String> names;
    private List<String> lastNames;

    public CombineNames() {

    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public void setLastNames(List<String> lastNames) {
        this.lastNames = lastNames;
    }

    public List<String> generateCombinations(int nameCount, int lastNameCount) {
        List<String> nameCombinations = new ArrayList<>();
        generateCombinations(nameCombinations, "", names, nameCount, 0);

        List<String> lastNameCombinations = new ArrayList<>();
        generateCombinations(lastNameCombinations, "", lastNames, lastNameCount, 0);

        List<String> combinations = new ArrayList<>();
        for (String nameComb : nameCombinations) {
            for (String lastNameComb : lastNameCombinations) {
                combinations.add(nameComb + " " + lastNameComb);
            }
        }

        return combinations;
    }

    private void generateCombinations(List<String> combinations, String current, List<String> elements, int count, int start) {
        if (count == 0) {
            combinations.add(current.trim());
            return;
        }

        for (int i = start; i <= elements.size() - count; i++) {
            generateCombinations(combinations, current + " " + elements.get(i), elements, count - 1, i + 1);
        }
    }
}


