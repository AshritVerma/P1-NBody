

/**
 * Celestial Body class for NBody
 * @author ola
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		// TODO: complete constructor
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}

	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public CelestialBody(CelestialBody b){
		// TODO: complete constructor
		myXPos=b.myXPos;
		myYPos=b.myYPos;
		myXVel=b.myXVel;
		myYVel=b.myYVel;
		myMass=b.myMass;
		myFileName=b.myFileName;
	}

	/**
	 * Return x position of this Body.
	 * @return value of x position.
	 */
	public double getX() {
		// TODO: complete method
		return myXPos;
	}

	/**
	 * Retrun y position of this Body.
	 * @return value of y position.
	 */
	public double getY() {
		// TODO: complete method
		return myYPos;
	}

	/**
	 * Return x-velocity of this Body.
	 * @return value of x-velocity.
	 */
	public double getXVel() {
		// TODO: complete method
		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		// TODO: complete method
		return myYVel;
	}

	/**
	 * Return mass of this Body.
	 * @return value of mass.
	 */
	public double getMass() {
		// TODO: complete method
		return myMass;
	}

	/**
	 * Return file name of file.
	 * @return String of file name.
	 */
	public String getName() {
		// TODO: complete method
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		// TODO: complete method
		return Math.abs(Math.sqrt(Math.pow(this.myXPos - b.myXPos, 2) + Math.pow(this.myYPos - b.myYPos, 2)));
	}

	/**
	 * Return the force exerted on this body by the body b
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcForceExertedBy(CelestialBody b) {
		// TODO: complete method
		return 6.67 * Math.pow(10,-11) * this.myMass * b.myMass / Math.pow(calcDistance(b), 2);
	}

	/**
	 * Return the x-component of the force exerted on this body by b
	 * @param b the other body to which the x force is calculated
	 * @return force between this body and b in the x-direction
	 */
	public double calcForceExertedByX(CelestialBody b) {
		// TODO: complete method
		return calcForceExertedBy(b) * (b.myXPos - this.myXPos) / calcDistance(b);
	}

	/**
	 * Return the y-component of the force exerted on this body by b
	 * @param b the other body to which the y force is calculated
	 * @return force between this body and b in the y-direction
	 */
	public double calcForceExertedByY(CelestialBody b) {
		// TODO: complete method
		return calcForceExertedBy(b) * (b.myYPos - this.myYPos) / calcDistance(b);
	}

	/**
	 * Return the x-component of the total/net force exerted on this body by all bodies in the bodies array
	 * @param bodies array containing all bodies exerting force on this body
	 * @return x-component of the total/net force
	 */
	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		// TODO: complete method
		double sum = 0.0;
		for(CelestialBody b : bodies)
		{
			if(! b.equals(this))
				sum += calcForceExertedByX(b);
		}
		return sum;
	}

	/**
	 * Return the y-component of the total/net force exerted on this body by all bodies in the bodies array
	 * @param bodies array containing all bodies exerting force on this body
	 * @return y-component of the total/net force
	 */
	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for(CelestialBody b : bodies)
		{
			if(! b.equals(this))
				sum += calcForceExertedByY(b);
		}
		return sum;
	}

	/**
	 * Mutator method that updates the state/instance variables of the CelestialBody object in "real-time"
	 * @param deltaT small time steps
	 * @param xforce x-component of the net forces exerted on this body by all other bodies
	 * @param yforce x-component of the net forces exerted on this body by all other bodies
	 */
	public void update(double deltaT, double xforce, double yforce) {
		// TODO: complete method
		double myXAcc = xforce / myMass;
		double myYAcc = yforce / myMass;

		double nvx = myXVel + deltaT*myXAcc;
		double nvy = myYVel + deltaT*myYAcc;
		myXVel = nvx;
		myYVel = nvy;

		double nx = myXPos + deltaT*nvx;
		double ny = myYPos + deltaT*nvy;
		myXPos = nx;
		myYPos = ny;
	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}