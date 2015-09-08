package com.neemre.bitplexus.common.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.neemre.bitplexus.common.Errors;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CollectionUtils {

	public static <T, S> Map<T, S> shuffle(Map<T, S> map, Random random) {
		if (map == null) {
			throw new IllegalArgumentException(Errors.TODO.getDescription());
		}
		Map<T, S> shuffledMap = new HashMap<T, S>();
		List<T> keys = new ArrayList<T>(map.keySet());
		Collections.shuffle(keys, random);
		for (int i = 0; i < keys.size(); i++) {
			shuffledMap.put(keys.get(i), map.get(keys.get(i)));
		}
		return shuffledMap;
	}

	public static <T, S> Map<T, S> shuffle(Map<T, S> map) {
		return shuffle(map, new SecureRandom());
	}
}