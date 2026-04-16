package objetosIAWar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static String header(String fillchar, int total_size, String string) {
        String result = string;
        for (int i = 0; i < (total_size - string.length()) / 2; i++) {
            result = fillchar + result + fillchar;
        }
        return result;
    }

    public static void main(String[] args) {

        // ---- Armes ----
        SubMachineGun smg1 = new SubMachineGun("Heckler & Koch MP5", 9f, 1300);
        SubMachineGun smg2 = new SubMachineGun("FN P90", 5.7f, 700);
        SubMachineGun smg3 = new SubMachineGun("CZ Scorpion EVO 3", 9f, 1450);
        SubMachineGun smg4 = new SubMachineGun("Steyr TMP", 9f, 1500);

        GrenadeLauncher gl1 = new GrenadeLauncher("M203", 40f, 1800);
        GrenadeLauncher gl2 = new GrenadeLauncher("MK 19", 40f, 1400);
        GrenadeLauncher gl3 = new GrenadeLauncher("GP-25", 50f, 1600);
        GrenadeLauncher gl4 = new GrenadeLauncher("M230", 40f, 1550);

        BulletproofVest bpv1 = new BulletproofVest("Stearns I590 Universal Type I", 550);
        BulletproofVest bpv2 = new BulletproofVest("Onyx MoveVent Dynamic Paddle", 430);
        BulletproofVest bpv3 = new BulletproofVest("Mustang Survival Elite 28", 625);

        HandGrenades hg1 = new HandGrenades("M67", 2, 40, 120);
        HandGrenades hg2 = new HandGrenades("F1", 2, 40, 140);
        HandGrenades hg3 = new HandGrenades("MK 2", 2, 50, 180);
        HandGrenades hg4 = new HandGrenades("RDG-5", 2, 40, 80);

        SubMachineGunBullet smgb1 = new SubMachineGunBullet("9x19 mm Parabellum (Luger)", 30, 9f, 80);
        SubMachineGunBullet smgb2 = new SubMachineGunBullet(".45 ACP (Automatic Colt Pistol)", 35, 5.7f, 95);
        SubMachineGunBullet smgb3 = new SubMachineGunBullet("5.7x28 mm FN", 28, 9f, 115);
        SubMachineGunBullet smgb4 = new SubMachineGunBullet(".40 S&W (Smith & Wesson)", 30, 5.7f, 89);

        SniperRifle sr1 = new SniperRifle("Barrett M82", 12.7f, 2300);
        SniperRifle sr2 = new SniperRifle("Accuracy International AXMC", 8.6f, 2050);
        SniperRifle sr3 = new SniperRifle("Dragunov SVD", 7.62f, 1870);
        SniperRifle sr4 = new SniperRifle("CheyTac M200 Intervention", 8.6f, 2870);

        SniperBullet sb1 = new SniperBullet("M33 Ball, M8 API", 35, 12.7f, 230);
        SniperBullet sb2 = new SniperBullet("Lapua Scenar, LockBase B408", 27, 8.6f, 138);
        SniperBullet sb3 = new SniperBullet("7N1-LPS", 33, 7.62f, 112);
        SniperBullet sb4 = new SniperBullet("M118LR (Long Range)", 35, 7.62f, 103);

        // ---- Soldats ----
        Infantry if1 = new Infantry("Liam O'Connor", 10, bpv1, 3000);
        Infantry if2 = new Infantry("Sofia Rossi", 4, bpv2, 2300);
        Infantry if3 = new Infantry("Hiroshi Tanaka", 7, bpv2, 2800);
        Infantry if4 = new Infantry("Amina El-Sayed", 6, bpv3, 2500);
        Infantry if5 = new Infantry("Carlos Mendez", 15, bpv1, 4800);

        if1.setGrenade_launcher(gl1);
        if1.addHandGrenades(hg1);
        if1.setSubMachineGun(smg1);
        if1.addSubMachineAmmunition(smgb1);
        if1.addSubMachineAmmunition(smgb1);

        if2.setGrenade_launcher(gl2);
        if2.addHandGrenades(hg2);
        if2.setSubMachineGun(smg2);
        if2.addSubMachineAmmunition(smgb2);

        if3.setGrenade_launcher(gl3);
        if3.addHandGrenades(hg3);
        if3.setSubMachineGun(smg3);
        if3.addSubMachineAmmunition(smgb3);

        if4.setGrenade_launcher(gl4);
        if4.addHandGrenades(hg4);
        if4.setSubMachineGun(smg4);
        if4.addSubMachineAmmunition(smgb1);

        if5.setGrenade_launcher(gl1);
        if5.addHandGrenades(hg1);
        if5.setSubMachineGun(smg1);
        if5.addSubMachineAmmunition(smgb1);
        if5.addSubMachineAmmunition(smgb1);
        if5.addSubMachineAmmunition(smgb1);

        Sniper sp1 = new Sniper("Fatima Zahra Benali", 16, bpv3, 5900);
        sp1.setSniper_rifle(sr4);
        sp1.addSniperBullets(sb2);
        sp1.addSniperBullets(sb2);
        sp1.setGrenade_launcher(gl1);
        sp1.addHandGrenades(hg1);

        // ---- ArrayList de mercenaris ----
        ArrayList<SoldierOfFortune> mercenaris = new ArrayList<SoldierOfFortune>();
        mercenaris.add(if1);
        mercenaris.add(if2);
        mercenaris.add(if3);
        mercenaris.add(if4);
        mercenaris.add(if5);
        mercenaris.add(sp1);

        // ---- Mostrar cada soldat ----
        for (SoldierOfFortune s : mercenaris) {
            System.out.println(s);
        }

        // ---- Ordenar per cost total (Comparable) ----
        System.out.println(header("=", 70, "Sorted by Total Cost"));
        System.out.println();
        Collections.sort(mercenaris);
        for (SoldierOfFortune s : mercenaris) {
            System.out.printf("%-35s Cost: %10d%n", s.getName(), s.getCost());
        }

        System.out.println();

        // ---- Ordenar per experiencia (Comparator) ----
        System.out.println(header("=", 70, "Sorted by Experience"));
        System.out.println();
        Collections.sort(mercenaris, new Comparator<SoldierOfFortune>() {
            @Override
            public int compare(SoldierOfFortune s1, SoldierOfFortune s2) {
                return Integer.compare(s1.getExperience(), s2.getExperience());
            }
        });
        for (SoldierOfFortune s : mercenaris) {
            System.out.printf("%-35s Experience: %10d%n", s.getName(), s.getExperience());
        }
    }
}
