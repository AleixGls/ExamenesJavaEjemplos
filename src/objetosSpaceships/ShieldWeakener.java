package objetosSpaceships;

public interface ShieldWeakener {
	default void weakenShield(Ship s) {
		int weakenedShield = s.getShield() / 2;
		
		s.setShield(weakenedShield);
	}
}
