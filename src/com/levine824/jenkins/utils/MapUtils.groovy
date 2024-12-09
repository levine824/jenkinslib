package com.levine824.jenkins.utils

import java.util.stream.Collectors
import java.util.stream.Stream

class MapUtils {

    static Map merge(Map m1, Map m2) {
        Map mergedMap = m1.size() > m2.size() ? parseMap(m1, m2) : parseMap(m2, m1)
        return mergedMap
    }

    private static Map parseMap(Map large, Map small) {
        Map map = large
        if (!small.isEmpty()) {
            small.each { key, value ->
                if (!large.containsKey(key)) {
                    map.put(key, value)
                    return
                }
                if (value instanceof Map) {
                    map.put(key, parseMap((Map) large.get(key), value))
                } else if (value instanceof List) {
                    map.put(key, parseList((List) large.get(key), value))
                } else {
                    map.put(key, value)
                }
            }
        }
        return map
    }

    private static List parseList(List l1, List l2) {
        return Stream.concat(l1.stream(), l2.stream()).distinct().collect(Collectors.toList())
    }
}
