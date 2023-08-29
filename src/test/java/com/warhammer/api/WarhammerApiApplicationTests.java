package com.warhammer.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import com.warhammer.api.model.Army;
import com.warhammer.api.service.ArmyService;

@SpringBootTest
class WarhammerApiApplicationTests {
	@Autowired
	private ArmyService armyService;
	@Test
	void contextLoads() {
	}
	@Test
	public void testGetArmyList() {
		Iterable<Army> armyList = armyService.getArmy();
		ArrayList<Army> armyVerif = (ArrayList<Army>) armyList;
		Optional<Army> armyTest = armyService.getArmy(Long.valueOf(20));
		Army armyCheck = armyTest.get();
		int index = 0;
		boolean egal = false;
		while(!egal) {
			if(armyVerif.get(index).getArmyName().equals(armyTest.get().getArmyName()) && armyVerif.get(index).getIdUser() == armyTest.get().getIdUser()) {
				egal = true;
				System.out.println(index);
			} else {
				index++;
			}
		}
		assertEquals(armyCheck, armyVerif.get(index));
	}
}
