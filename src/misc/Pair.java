package misc;

import java.util.Objects;

public class Pair<K, V> {
    private K key;
    private V value;
    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }
    @Override
    public boolean equals(Object o){
        if(this == o)return true;
        if(o == null || getClass() != o.getClass())return false;
        Pair<?, ?> pair = (Pair<?, ?>)o;
        if(key != null ? !key.equals(pair.key) : pair.key != null)return false;
        return value != null ? value.equals(pair.value) : pair.value == null;
//        if(!Objects.equals(key, pair.key))return false;
//        return Objects.equals(value, pair.value);
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }
    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
