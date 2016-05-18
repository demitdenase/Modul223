package controller;


import java.util.Random;

public class DividendenRechner {

	static Random r = new Random();

	public static final int SCHWACHE_STREUUNG = 1;
	public static final int MITTLERE_STREUUNG = 3;
	public static final int STARKE_STREUUNG = 5;

	public static int neueDividende(int alteDividende, int streuung, int min,
			int max) {
		double neueDividende, dividendenAenderung;

		dividendenAenderung = Math.abs(alteDividende * streuung
				* r.nextGaussian() / 10);

		if (r.nextDouble() < 0.5) {
			dividendenAenderung = -1;
		}

		neueDividende = alteDividende + dividendenAenderung;

		if (neueDividende < min) {
			neueDividende = min + 3.5 * Math.abs(dividendenAenderung);
		} else if (neueDividende > max) {
			neueDividende = 0.9 * max;
		}
		return (int) Math.ceil(neueDividende);
	}
}

