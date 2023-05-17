//package pl.psi.WarMachines;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BallistaMachineTestArtillerySkill {
//    BallistaMachine ballistaMachine = new BallistaMachine();
//    @Test
//    void calculateDamageFromBallista() {
//        int attack = 5;
//        int damage = ballistaMachine.calculateDamageFromBallista(attack);
//        assertTrue(2 * (attack + 1) <= damage && damage <= 3 * (attack + 1));
//        assertFalse(damage < 2 * (attack + 1));
//        assertFalse(damage > 3 * (attack + 1));
//        assertFalse(damage > 100 * attack);
//    }
//
//    @Test
//    void calculateAverageDamageFromBallista(){
//        int attack = 25;
//        int expectedDamage = (2 * (attack + 1) + 3 * (attack + 1)) / 2;
//        int averageDamage = 0;
//        int iterations = 10000000;
//        for (int i = 0; i < iterations; i++) {
//            averageDamage += ballistaMachine.calculateDamageFromBallista(attack);
//        }
//        averageDamage /= iterations;
//        averageDamage = Math.round(averageDamage);
//
//        assertEquals(expectedDamage, averageDamage);
//        assertEquals(65,averageDamage);
//    }
//
//    @Test
//    void calculateDamageFromBallistaWithArtillerySkillLvlOne(){
//        int heroAttackPoints = 10;
//        int heroArtillerySkill = 1;
//        BallistaMachine ballistaMachine2 = new BallistaMachine(heroAttackPoints,0,heroArtillerySkill,0);
//        int damage = ballistaMachine2.getBallistaDamage();
//        System.out.println("Damage: " + damage);
//        assertTrue(damage >= Math.round(2*(heroAttackPoints + 1)) && damage <= Math.round(3*(heroAttackPoints + 1)) ||
//                damage >= Math.round(2*2*(heroAttackPoints + 1)) && damage <= Math.round(3*2*(heroAttackPoints + 1)));
//    }
//    @Test
//    void calculateDamageFromBallistaWithArtillerySkillLvlTwo(){
//        int heroAttackPoints = 10;
//        int heroArtillerySkill = 2;
//        BallistaMachine ballistaMachine2 = new BallistaMachine(heroAttackPoints,0,heroArtillerySkill,0);
//        int damage = ballistaMachine2.getBallistaDamage();
//        System.out.println("Damage: " + damage);
//        System.out.println("Lower: " + 2 * 2 * (heroAttackPoints + 1));
//        System.out.println("Upper: " + 4 * 3 * (heroAttackPoints + 1));
//        assertTrue(damage >= Math.round(4*2*(heroAttackPoints + 1)) && damage <= Math.round(4*3*(heroAttackPoints + 1)) ||
//                damage >= Math.round(2*2*(heroAttackPoints + 1)) && damage <= Math.round(2*3*(heroAttackPoints + 1)));
//    }
//    @Test
//    void calculateDamageFromBallistaWithArtillerySkillLvlThree(){
//        int heroAttackPoints = 10;
//        int heroArtillerySkill = 3;
//        BallistaMachine ballistaMachine2 = new BallistaMachine(heroAttackPoints,0,heroArtillerySkill,0);
//        int damage = ballistaMachine2.getBallistaDamage();
//        System.out.println("Damage: " + damage);
//        System.out.println("Lower: " + 4 * 2 * (heroAttackPoints + 1));
//        System.out.println("Upper: " + 4 * 3 * (heroAttackPoints + 1));
//        assertTrue(4 * 2 * (heroAttackPoints + 1) <= damage &&
//                damage <= 4 * 3 * (heroAttackPoints + 1));
//    }
//
//    @Test
//    void calculateAverageDamageFromBallistaWithArtillerySkills(){
//        int heroAttackPoints = 15;
//        int heroArtillerySkill = 2;
//        int expectedDamage = 140;
//
//        float averageDamage = 0;
//        int iterations = 10000;
//        for (int i = 0; i < iterations; i++) {
//            BallistaMachine ballistaMachine2 = new BallistaMachine(heroAttackPoints,0,heroArtillerySkill,0);
//            averageDamage += ballistaMachine2.getBallistaDamage();
//        }
//        averageDamage /= iterations;
//        System.out.println("Average damage: " + averageDamage);
//        averageDamage = Math.round(averageDamage);
//        assertEquals(expectedDamage, averageDamage);
//    }
//
////    This is test for private method
////    @Test
////    void getRandomNumber() {
////        int lowerBoundary = 1;
////        int upperBoundary = 2;
////        double randomNumber = ballistaMachine.getRandomNumber(lowerBoundary,upperBoundary);
////        assertTrue((lowerBoundary <= randomNumber) && (randomNumber <= upperBoundary));
////        assertFalse(randomNumber < lowerBoundary);
////        assertFalse(randomNumber > upperBoundary);
////    }
//}