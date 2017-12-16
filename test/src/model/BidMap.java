/**
 * hashmap을 상속하여 양방향성 기능을 추가
 * 작성일 : 17.12.16
 * 작성자 : 정은진
 */
package model;

import java.util.HashMap;
import java.util.Map;

public class BidMap<K, V> extends HashMap<K, V>{
	Map<V, K> reverseMap = new HashMap<V, K>();
	
	@Override
	public V put (K key, V value) {
		reverseMap.put(value,  key);
		return super.put(key,  value);
	}
	@Override
	public V remove(Object key) {
		reverseMap.remove(key);
		return super.remove(key);
	}
	public K getKey(V value) {
		return reverseMap.get(value);
	}
}
