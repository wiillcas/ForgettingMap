package org.example;

import java.util.*;
import java.util.Map.Entry;

public class ForgettingMap {

    private final int numberOfAssociations;
    private final HashMap<String, Object> associations;
    private final HashMap<String, Integer> findsPerAssociations;

    public ForgettingMap(int numberOfAssociations) {
        this.numberOfAssociations = numberOfAssociations;
        this.associations = new HashMap<>();
        this.findsPerAssociations = new HashMap<>();
    }

    public synchronized void Add(String key, Object value) {
        if (this.associations.containsKey(key)) {
            System.out.printf("%s key already exists\n", key);
            return;
        }

        if (!findsPerAssociations.isEmpty() && findsPerAssociations.size() >= this.numberOfAssociations) {
            Entry<String, Integer> min = null;
            for (Map.Entry<String, Integer> entry : findsPerAssociations.entrySet()) {
                if (min == null || min.getValue() > entry.getValue()) {
                    min = entry;
                }
            }
            String oldKey = min.getKey();
            this.findsPerAssociations.remove(oldKey);
            this.associations.remove(oldKey);
            System.out.println("Removed from ForgettingMap: key=" + oldKey + ", finds=" + min.getValue());
        }

        this.associations.put(key, value);
        this.findsPerAssociations.put(key, 1);
        System.out.println("Added to ForgettingMap: key=" + key + ", finds=" + 1);
    }

    public Optional<Object> Find(String key) {
        if (!this.associations.containsKey(key)) {
            return Optional.empty();
        }

        Set<String> keySet = this.findsPerAssociations.keySet();
        for (String _key : keySet) {
            if (_key.compareTo(key) == 0) {
                int newFindCount = this.findsPerAssociations.get(_key) + 1;
                this.findsPerAssociations.put(_key, newFindCount);
                return Optional.ofNullable(this.associations.get(key));
            }
        }

        return Optional.empty();
    }

    public Set<String> keySet() {
        return this.associations.keySet();
    }

    @Override
    public String toString() {
        StringBuilder state = new StringBuilder("{\n");
        Iterator<String> associationKeys = associations.keySet().iterator();
        while (associationKeys.hasNext()) {
            String key = associationKeys.next();
            Integer finds = findsPerAssociations.get(key);
            String value = associations.get(key).toString();
            String associationState = "finds: " + finds + ", key: " + key + ", value: " + value;
            state.append(associationState).append("\n");
        }
        state.append("}");
        return state.toString();
    }
}
