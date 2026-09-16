package com.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TestDemo {

	public static void main(String[] args) {

		String data="Name:JohnDoe;Age:29;Skills:Java,Python,Go;Location:NYC";
		String[] mapContetnt = data.split(";");
		Map<String, String> map=new HashMap<>();
		for(int i=0; i< data.split(";").length; i++) {
			String s= mapContetnt[i];
			map.put(s.substring(0, s.indexOf(":")), s.substring(s.indexOf(":")+1));
		}
		System.out.println(map);
		LinkedHashMap m= map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(
						Map.Entry::getKey,Map.Entry::getValue,
						(a,b)->a ,LinkedHashMap::new));
		System.out.println(m);
	}

}
