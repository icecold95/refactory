package at.igdc.naming;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomValuePool<T> {

  private List<T> values = new ArrayList<>();
  private List<T> usedValues = new ArrayList<>();

  public RandomValuePool(List<T> values) {
    this.values = values;
  }

  public RandomValuePool(T[] values) {
    this(new ArrayList<T>(Arrays.asList(values)));
  }

  public T nextValue() {
    if (values.isEmpty()) {
      values = usedValues;
      usedValues = new ArrayList<>();
    }
    final T value = values.remove(ThreadLocalRandom.current().nextInt(values.size()));
    usedValues.add(value);
    return value;
  }

  public T[] nextValues(Class<T> clazz, int count) {
    T[] values = (T[]) Array.newInstance(clazz, count);
    for (int i = 0; i < count; i++) {
      values[i] = nextValue();
    }
    return values;
  }
}
