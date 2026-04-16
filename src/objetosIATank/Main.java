package objetosIATank;

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

        // ---- Crear instàncies ----
        LightTank lt1 = new LightTank("M24 Chaffee", 1000, 56, new Engine("Electric Engine X2", 600, 13000), 150000);
        LightTank lt2 = new LightTank("T-70", 1200, 45, new Engine("Gas Engine V6", 400, 8000), 160000);
        LightTank lt3 = new LightTank("Vickers Mk. VI", 1400, 40, new Engine("Gas Engine V6", 400, 8000), 130000);
        lt3.setCannon_launcher(new CannonLauncher("Cannon Royal Ordnance L7", 150f, 250000));
        lt3.setMachine_gun(new MachineGun("DShK", 12.7f, 37000));
        lt3.addCannonProjectile(new CannonProjectile("High Explosive 150mm", 20, 150, 50000));
        lt3.addMachineAmmunition(new MachineGunBullet("MG 12.7mm NATO BMG", 600, 12.7f, 2300));

        HeavyTank ht1 = new HeavyTank("Panzer VI Tiger", 5500, 30, new Engine("Combustion Engine SVC234", 560, 9600), 850000);
        HeavyTank ht2 = new HeavyTank("T-34/85", 4700, 35, new Engine("Combustion Engine SVC234", 560, 9600), 730000);
        HeavyTank ht3 = new HeavyTank("M26 Pershing", 4900, 38, new Engine("Combustion Engine SVC234", 560, 9600), 750000);
        ht3.setCannon_launcher(new CannonLauncher("Cannon 2A46", 140f, 175000));
        ht3.addMachine_gun(new MachineGun("DShK", 12.7f, 37000));
        ht3.addMachine_gun(new MachineGun("GRANDE .50", 21.3f, 57000));
        ht3.addCannonProjectile(new CannonProjectile("Armour Piercing Composite Rigid 140mm", 30, 140, 35000));
        ht3.addCannonProjectile(new CannonProjectile("Armour Piercing Composite Rigid 140mm", 30, 140, 35000));
        ht3.addMachineAmmunition(new MachineGunBullet("MG 12.7mm NATO BMG", 600, 12.7f, 2300));
        ht3.addMachineAmmunition(new MachineGunBullet("MG 12.7mm NATO BMG", 600, 12.7f, 2300));
        ht3.addMachineAmmunition(new MachineGunBullet("KPV 21.3mm NATO BMG", 200, 21.3f, 8400));
        ht3.addMachineAmmunition(new MachineGunBullet("KPV 21.3mm NATO BMG", 200, 21.3f, 8400));

        // ---- ArrayList de tanks ----
        ArrayList<Tank> tanks = new ArrayList<Tank>();
        tanks.add(lt1);
        tanks.add(lt2);
        tanks.add(lt3);
        tanks.add(ht1);
        tanks.add(ht2);
        tanks.add(ht3);

        // ---- Ordenar per cost total (Comparable) ----
        System.out.println(header("=", 70, "Sorted by Total Cost"));
        System.out.println();
        Collections.sort(tanks);
        for (Tank t : tanks) {
            System.out.print(t);
        }

        System.out.println();

        // ---- Ordenar per velocitat (Comparator) ----
        System.out.println(header("=", 70, "Sorted by Speed"));
        System.out.println();
        Collections.sort(tanks, new Comparator<Tank>() {
            @Override
            public int compare(Tank t1, Tank t2) {
                return Integer.compare(t1.getSpeed(), t2.getSpeed());
            }
        });
        for (Tank t : tanks) {
        	System.out.print(t);
        }
    }
}
