package ru.practicum.mediasoft;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CollectionSearchAndSort {

    public List<Human> findHumans(List<Human> humans, Map<String, Object> params, String sortBy) {
        if (params == null && sortBy == null) {
            return humans;
        }
        if (params == null) {
            return humans.stream().sorted(getComparator(sortBy)).toList();
        }
        return humans.stream()
                .filter(human -> params.get("name") == null ||
                        human.getName().toLowerCase()
                                .contains(params.get("name").toString().toLowerCase()))
                .filter(human -> params.get("birthday") == null
                        || human.getBirthday().equals(params.get("birthday")))
                .filter(human -> params.get("gender") == null
                        || human.getGender().equalsIgnoreCase(params.get("gender").toString()))
                .filter(human -> params.get("ageMore") == null
                        || human.getAge() > Integer.parseInt((String) params.get("ageMore")))
                .filter(human -> params.get("ageLess") == null
                        || human.getAge() < Integer.parseInt((String) params.get("ageLess")))
                .sorted(getComparator(sortBy))
                .toList();
    }

    public Comparator<Human> getComparator(String sortBy) {
        if ("name".equalsIgnoreCase(sortBy)) {
            return Comparator.comparing(Human::getName);
        } else if ("birthday".equalsIgnoreCase(sortBy)) {
            return Comparator.comparing(Human::getAge);
        }
        return Comparator.comparing(Human::getName);
    }

    public List<Human> findHumans(List<Human> humans, Map<String, Object> params) {
        if (params == null) {
            return humans;
        }
        return humans.stream()
                .filter(human -> params.get("name") == null ||
                        human.getName().toLowerCase()
                                .contains(params.get("name").toString().toLowerCase()))
                .filter(human -> params.get("birthday") == null
                        || human.getBirthday().equals(params.get("birthday")))
                .filter(human -> params.get("gender") == null
                        || human.getGender().equalsIgnoreCase(params.get("gender").toString()))
                .filter(human -> params.get("ageMore") == null
                        || human.getAge() > Integer.parseInt((String) params.get("ageMore")))
                .filter(human -> params.get("ageLess") == null
                        || human.getAge() < Integer.parseInt((String) params.get("ageLess")))
                .toList();
    }
}
