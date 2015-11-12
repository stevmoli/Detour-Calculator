import java.util.*;

// by Steven Molitor

public class Main {
	public static void main(String args[]) {

		// Creating points A, B, C, D
		Point A = generatePoint();
		Point B = generatePoint();
		Point C = generatePoint();
		Point D = generatePoint();

		System.out.println("A passenger at point A wants to get to point B, and a "
				+ "passenger at point C wants to get to point D.");
		System.out.println("Point A is: "+A);
		System.out.println("Point B is: "+B);
		System.out.println("Point C is: "+C);
		System.out.println("Point D is: "+D);

		// A car will either start at point A or C.  Point B must be visited after A,
		// and point D must be visited after point C.

		// this shortestDetour method gives us the solution faster than a brute force algorithm
		Double shortestDetour = (shortestDetour(A, B, C, D));

		System.out.println("The shortest detour has a distance of: "+shortestDetour);

	}

	public static Point generatePoint(){ // generates random points with coordinate values between 0 and 100
		Random r = new Random();
		Point temp = new Point(r.nextDouble()*100, r.nextDouble()*100);
		return temp;
	}

	public static Double shortestDetour(Point A, Point B, Point C, Point D) {
		// We can only travel to B after visiting A, and we can only travel to D after
		// visiting C.

		// Starting by finding the shortest detour if we start at Point A
		Double detourA = detourFromOnePoint(A, B, C, D);
		// And then finding the shortest detour if we start at point C
		Double detourC = detourFromOnePoint(C, D, A, B);

		if (detourA < detourC) {
			return detourA;
		} else {
			return detourC;
		}
	}

	public static Double detourFromOnePoint(Point A, Point B, Point C, Point D) {
		double detour = 0;
		boolean visitedB = false;
		// we cannot travel to D because we've not visited C.  Which is closer, C or B?
		if (A.calculateDistance(B) < A.calculateDistance(C)) {
			detour += A.calculateDistance(B);  // B is closer, so we go there first
			visitedB = true;
		} else if (A.calculateDistance(B) > A.calculateDistance(C)) {
			detour += A.calculateDistance(C);  // C is closer, so we go there first
		} else { // B and C are equally far away.  We'll want to go to the one farthest from D
			if (B.calculateDistance(D) > C.calculateDistance(D)) {
				// We travel from A to B to C to D
				return A.calculateDistance(B) + B.calculateDistance(C) + C.calculateDistance(D);
			} else if (B.calculateDistance(D) < C.calculateDistance(D)) {
				// We go to C first
				detour += A.calculateDistance(C);
				// Now we go to B or D, whichever is closer
				if (C.calculateDistance(B) < C.calculateDistance(D)) { // B is closer
					detour += C.calculateDistance(B);
					detour += B.calculateDistance(D);
					return detour;
				} else { // D is closer or distances are the same
					detour += C.calculateDistance(D);
					detour += D.calculateDistance(B);
					return detour;
				}
			} else { // D is same distance from B and C
				detour += 3*(A.calculateDistance(B)); // Segments of path A to C to D to B are all the same distance
			}
		}

		if (visitedB) {  // if we've already visited B, we must go to C next since we must visit C before D
			detour += B.calculateDistance(C) + C.calculateDistance(D); // travel to C, then D
			return detour;
		} else { // We have visited C, now we go to either B or D, whichever is closest
			if (C.calculateDistance(B) < C.calculateDistance(D)) { // B is closer
				detour += C.calculateDistance(B) + B.calculateDistance(D);
				return detour;
			} else {
				detour += C.calculateDistance(D) + D.calculateDistance(B);
				return detour;
			}
		}
	}
}
