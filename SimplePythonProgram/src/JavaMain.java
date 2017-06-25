
import org.python.core.PyInstance;
import org.python.util.PythonInterpreter;

public class JavaMain {
	
	private static PythonInterpreter interpreter;
	
	public static void main(String[] args) {
		// Set up Python interpreter
		PythonInterpreter.initialize(System.getProperties(), System.getProperties(), new String[0]);

		interpreter = new PythonInterpreter();
		
		//InterpreterExample ie = new InterpreterExample();

		execfile("simplePython.py");

		PyInstance func = createClass("SimplePython", "");

		func.invoke("run");
	}
	
	
	public static 
	// Helper Python Functions---------
	void execfile(final String fileName) {
		interpreter.execfile(fileName);
	}

	static PyInstance createClass(final String className, final String opts) {
		return (PyInstance) interpreter.eval(className + "(" + opts + ")");
	}
}