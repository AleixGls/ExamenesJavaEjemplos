package objetosSpaceships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) {
        Missile m1 = new Missile("ML-3021 Long Range Missile",200,6000);
        Missile m2 = new Missile("ML-3022 Long Range Missile",250,7000);

        LaserGenerator lg1 = new LaserGenerator("LG3N-A02 Laser Battery",2000,5000);
        LaserGenerator lg2 = new LaserGenerator("LG3N-A03 Laser Battery",3000,6000);

        MissileLauncher ml1 = new MissileLauncher("Hellstorm Missil Launcher 1",2000,4000,m1);
        MissileLauncher ml2 = new MissileLauncher("Hellstorm Missil Launcher 2",3000,8000,m2);
        MissileLauncher ml3 = new MissileLauncher("Hellstorm Missil Launcher 3",4000,12000,m1);
        MissileLauncher ml4 = new MissileLauncher("Hellstorm Missil Launcher 4",5000,16000,m2);
        MissileLauncher ml5 = new MissileLauncher("Hellstorm Missil Launcher 5",6000,20000,m1);

        Laser l1 = new Laser("Laser LF-1",600,20000,lg1);
        Laser l2 = new Laser("Laser LF-2",800,25000,lg2);
        Laser l3 = new Laser("Laser LF-3",1000,30000,lg1);
        Laser l4 = new Laser("Laser LF-4",1200,35000,lg2);
        Laser l5 = new Laser("Laser LF-5",1400,40000,lg1);
        Laser l6 = new Laser("Laser LF-6",1800,50000,lg2);

        FusionPropellant p1 = new FusionPropellant("G3N-3250",10000, 800 );
        FusionPropellant p2 = new FusionPropellant("G3N-3251",12000, 1000 );
        IonPropellant p3 = new IonPropellant("ZTX-4500",8000,1500);
        IonPropellant p4 = new IonPropellant("ZTX-4500",8000,2000);


        Cruise c1 = new Cruise("Cruise Yamato Ronin",2000,200000,400,4000,p1);
        c1.addWeapon(ml1);

        Cruise c2 = new Cruise("Cruise Diminisher",2400,230000,450,4700,p2);
        c2.addWeapon(ml2);

        BattleShip b1 = new BattleShip("Battle Ship Tartarus",5000, 600000, 250,1500 , p2);
        b1.addWeapon(ml3);
        b1.addWeapon(ml4);
        b1.addWeapon(l1);

        BattleShip b2 = new BattleShip("Battle Ship Aegis Elite",5500, 750000, 250 ,1500 , p3);
        b2.addWeapon(l2);
        b2.addWeapon(l3);
        b2.addWeapon(ml5);

        ArmoredShip as1 = new ArmoredShip("Armored Ship Phoenix" ,9000, 1400000, 360,1250, p4);
        as1.addWeapon(l4);
        as1.addWeapon(ml5);

        ArmoredShip as2 = new ArmoredShip("Armored Ship Piranha" ,10400, 1600000, 380,1300, p3);
        as2.addWeapon(l5);
        as2.addWeapon(l6);

        ArrayList<Ship> ships = new ArrayList<Ship>();
        ships.add(c1);
        ships.add(c2);
        ships.add(b1);
        ships.add(b2);
        ships.add(as1);
        ships.add(as2);
        
        // Este ya usa el orden default que tiene la clase ship
        System.out.println("=== Naus ordenades per cost total ===");
        Collections.sort(ships);
        for(Ship ship : ships) {
            System.out.println(ship);
        }
        
        // Aqui hay que crear un nuevo comparador que sea por velocidad
        System.out.println("\n=== Naus ordenades per velocitat ===");
        Collections.sort(
            ships, new Comparator<Ship>() {
                @Override
                public int compare(Ship o1, Ship o2) {
                    return Integer.compare(o2.getSpeed(), o1.getSpeed());
                }
            }
        );
        for(Ship ship : ships) {
            System.out.println(ship);
        }
	}
}
